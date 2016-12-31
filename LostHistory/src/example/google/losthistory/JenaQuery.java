package example.google.losthistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;

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

public class JenaQuery {
	static void RequestRandomBattles(int nbBattles){
		String queryString=
					"prefix dbr: <http://fr.dbpedia.org/resource/>\n"+
					"prefix dbp: <http://fr.dbpedia.org/property/>\n"+
					"prefix dbo: <http://dbpedia.org/ontology/>\n"+
					"prefix foaf: <http://xmlns.com/foaf/0.1/>\n"+
					"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
					"prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n"+

					"select ?nom ?combattants ?lat ?long (year(?date) as ?annee)\n"+
					"where {\n"+
					  "?bataille rdf:type dbo:MilitaryConflict;\n"+
					     "foaf:name ?nom;\n"+
					     "dbp:combattants ?combattants;\n"+
					     "dbo:startDate ?date.\n"+
					     "?bataille dbo:place ?place.\n"+
					     "?place geo:lat ?lat;\n"+
					              "geo:long ?long.\n"+
					  "filter(langMatches(lang(?nom),\"fr\") &&\n"+
					         "regex(?combattants, \"resource\") &&\n"+
					         "!regex(?combattants, \"Fichier\"))\n"+
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
				    while(results.hasNext()){
				    	QuerySolution rs = results.nextSolution();
				    	if(IsNewBattle(rs, lastName)){
				    		lastName = GetName(rs);
				    		if(RandomAcceptance()){
				    			if(b != null)
				    				System.out.println(b);
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
				    	}
				    }
				}
				finally {
				   qexec.close();
				}
	}
	
	private static boolean RandomAcceptance(){
		Random rdm = new Random();
		//return rdm.nextInt(100) < 5;
		return true;
	}
	
	private static boolean IsNewBattle(QuerySolution rs, String prevName){
		if(rs == null || prevName == "") return true;
		String name = rs.get("nom").toString();
		return !prevName.equals(name);
	}
	
	private static boolean IsNewCoordinates(Battle battle, Coordinate coord){
		if(battle == null || coord == null) return true;
		return !battle.getPlaces().contains(coord);
	}
	
	private static boolean IsNewParticipant(Battle battle, String participant){
		if(battle == null || participant == null) return true;
		return !battle.getParticipants().contains(participant);
	}
	
	private static int GetYear(QuerySolution rs){
		if(rs == null || rs.getLiteral("annee") == null) return 0;
		return rs.getLiteral("annee").asLiteral().getInt();
	}
	
	private static Coordinate GetCoord(QuerySolution rs){
		if(rs == null || rs.getLiteral("lat") == null || rs.getLiteral("long") == null) return null;
		return new Coordinate(rs.getLiteral("lat").asLiteral().getDouble(), 
				rs.getLiteral("long").asLiteral().getDouble());
	}
	
	private static String GetName(QuerySolution rs){
		if(rs == null || rs.getLiteral("nom") == null) return null;
		return rs.getLiteral("nom").toString();
	}
	
	private static String GetParticipant(QuerySolution rs){
		if(rs == null || rs.getResource("combattants") == null) return null;
		return rs.getResource("combattants").toString();
	}
}
