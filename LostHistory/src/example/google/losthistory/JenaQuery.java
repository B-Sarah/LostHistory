package example.google.losthistory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.jena.datatypes.DatatypeFormatException;
import org.apache.jena.query.*;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

/*
prefix dbr: <http://fr.dbpedia.org/resource/>
prefix dbp: <http://fr.dbpedia.org/property/>
prefix dbo: <http://dbpedia.org/ontology/>
prefix foaf: <http://xmlns.com/foaf/0.1/>
prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>

select ?nom ?combattants ?lat ?long (year(?date) as ?annee)
where {
  ?bataille rdf:type dbo:MilitaryConflict;
     foaf:name ?nom;
     dbp:combattants ?combattants;
     dbo:startDate ?date.
     ?bataille dbo:place ?place.
     ?place geo:lat ?lat;
              geo:long ?long.
  filter(langMatches(lang(?nom),"fr") &&
         regex(?combattants, "resource") &&
         !regex(?combattants, "Fichier"))
}
order by ?nom
*/

@Api(name="battleEndpoint", version="v1")
public class JenaQuery {
	
	@ApiMethod (name="battle.request", path="battle", httpMethod=ApiMethod.HttpMethod.GET)
	public List<Battle> RequestRandomBattles(@Named("nbBattles") int nbBattles){
		
		List<Battle> battles = null;
		List<String> participantPool = null;
		
		String queryString=
					"prefix dbr: <http://fr.dbpedia.org/resource/>\n"+
					"prefix dbp: <http://fr.dbpedia.org/property/>\n"+
					"prefix dbo: <http://dbpedia.org/ontology/>\n"+
					"prefix foaf: <http://xmlns.com/foaf/0.1/>\n"+
					"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
					"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
					"prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n"+

					"select ?nom ?combattant ?lat ?long (year(?date) as ?annee)\n"+
					"where {\n"+
					  "?bataille rdf:type dbo:MilitaryConflict;\n"+
					     "foaf:name ?nom;\n"+
					     "dbp:combattants ?combattants;\n"+
					     "dbo:startDate ?date.\n"+
					     "?bataille dbo:place ?place.\n"+
					     "?place geo:lat ?lat;\n"+
					              "geo:long ?long.\n"+
					  "?combattants rdfs:label ?combattant.\n"+
					  "filter(langMatches(lang(?nom),\"fr\") &&\n"+
					         "langMatches(lang(?combattant),\"fr\") &&\n"+
					         "regex(?combattants, \"resource\") &&\n"+
					         "!regex(?combattants, \"Fichier\") &&\n"+
					         "!regex(?long, \"NAN\") &&\n"+
					         "!regex(?lat, \"NAN\"))\n"+
					"}\n"+
					"order by ?nom";

		// now creating query object
		Query query = QueryFactory.create(queryString);
		// initializing queryExecution factory with remote service.
		// **this actually was the main problem I couldn't figure out.**
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://fr.dbpedia.org/sparql", query);

		//after it goes standard query execution and result processing which can
		// be found in almost any Jena/SPARQL tutorial.
		Battle b = null; 
		String lastName = "";
		try {
		    ResultSet results = qexec.execSelect();
		    int battleCount = 0;
		    
		    battles = new ArrayList<Battle>();
		    participantPool = new ArrayList<String>();
		    
		    while(battles.size() < nbBattles){
		    	if(!results.hasNext()){
		    		if(nbBattles > battleCount)
		    			break;
		    		battleCount = 0;
		    		results = qexec.execSelect();
		    	}
		    	QuerySolution rs = results.nextSolution();
		    	if(IsNewBattle(rs, lastName)){
		    		lastName = GetName(rs);
		    		battleCount++;
		    		if(RandomAcceptance()){
			    		b = new Battle(GetName(rs));
			    		b.setYear(GetYear(rs));
			    	}
		    		else
		    			b = null;
		    	}
		    	
		    	if(b != null){
			    	Coordinate c = GetCoord(rs);
			    	if(IsNewCoordinates(b, c))
			    		b.AddCoordinate(c);
			    	String p = GetParticipant(rs);
			    	if(IsNewParticipant(b, p))
			    		b.AddParticipant(p);
			    	if(!battles.contains(b) && !b.getPlaces().isEmpty() && !b.getParticipants().isEmpty())
			    		battles.add(b);
		    	}
		    	
		    	String p = GetParticipant(rs);
			    if(!participantPool.contains(p)){
			    	participantPool.add(p);
		    	}
		    }
		}
		finally {
		   qexec.close();
		}
				
		for(Battle battle : battles){
			battle.PickFormattedParticipants(participantPool);
			System.out.println(battle);
		}
		
		System.out.println("Number of battles : " + battles.size());
				
		return battles;
	}
	
	private boolean RandomAcceptance(){
		Random rdm = new Random();
		return rdm.nextInt(100) < 3;
		//return true;
	}
	
	private boolean IsNewBattle(QuerySolution rs, String prevName){
		if(rs == null || prevName.equals("")) return true;
		String name = GetName(rs);
		return !prevName.equals(name);
	}
	
	private boolean IsNewCoordinates(Battle battle, Coordinate coord){
		if(battle == null || coord == null) return true;
		return !battle.containsCoordinates(coord);
	}
	
	private boolean IsNewParticipant(Battle battle, String participant){
		if(battle == null || participant == null) return true;
		return !battle.getParticipants().contains(participant);
	}
	
	private int GetYear(QuerySolution rs){
		if(rs == null || rs.getLiteral("annee") == null) return 0;
		return rs.getLiteral("annee").asLiteral().getInt();
	}
	
	private Coordinate GetCoord(QuerySolution rs){
		if(rs == null || rs.getLiteral("lat") == null || rs.getLiteral("long") == null) return null;
		Coordinate coord = null;
		try{
			coord = new Coordinate(round(rs.getLiteral("lat").asLiteral().getDouble(), 4), 
					round(rs.getLiteral("long").asLiteral().getDouble(), 4));
		}
		catch(Exception e){}
		return coord;
	}
	
	private String GetName(QuerySolution rs){
		if(rs == null || rs.getLiteral("nom") == null) return null;
		String n = rs.getLiteral("nom").toString();
		return n.substring(0, n.length()-3);
	}
	
	private String GetParticipant(QuerySolution rs){
		if(rs == null || rs.getLiteral("combattant") == null) return null;
		String c = rs.getLiteral("combattant").toString(); 
		return c.substring(0, c.length()-3);
	}
	
	private double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

}
