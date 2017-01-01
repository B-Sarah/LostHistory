package example.google.losthistory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Battle implements Serializable {
	private List<Coordinate> places;
	private List<String> participants;
	private Map<String, Boolean> formattedParticipants;
	private int year;
	private String name;
	
	public Battle(String name){
		this.name = name;
		this.places = new ArrayList<Coordinate>();
		this.participants = new ArrayList<String>();
		this.formattedParticipants = new HashMap<String, Boolean>();
	}
	
	public void AddParticipant(String participant){
		this.participants.add(participant);
	}
	
	public void RemoveParticipant(String participant){
		this.participants.remove(participant);
	}
	
	public void AddCoordinate(Coordinate coordinate){
		this.places.add(coordinate);
	}
	
	public void RemoveCoordinate(Coordinate coordinate){
		this.places.remove(coordinate);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public List<Coordinate> getPlaces() {
		return places;
	}

	public void setPlaces(List<Coordinate> places) {
		this.places = places;
	}

	public List<String> getParticipants() {
		return participants;
	}

	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}
	
	public boolean containsCoordinates(Coordinate c){
		boolean contained = false;
		for(Coordinate coord : this.places){
			if (Double.compare(coord.latitude, c.latitude) == 0 && Double.compare(coord.longitude, c.longitude) == 0){
				contained = true;
				break;
			}
		}
		return contained;
	}
	
	public void PickFormattedParticipants(List<String> pList){
		Collections.shuffle(this.participants);
		Random rdm = new Random();
		int picked = rdm.nextInt(this.participants.size())+1;
		for(int i = 0; i < picked; i++){
			this.formattedParticipants.put(this.participants.get(i), true);
		}
		for(int i = 0; i < 4-picked; i++){
			String rdmPart = "";
			do{
				rdmPart = pList.get(rdm.nextInt(pList.size()));
			} while(this.formattedParticipants.containsKey(rdmPart));
			this.formattedParticipants.put(rdmPart, false);
		}
	}
	
	
	
	public Map<String, Boolean> getFormattedParticipants() {
		return formattedParticipants;
	}

	public void setFormattedParticipants(Map<String, Boolean> formattedParticipants) {
		this.formattedParticipants = formattedParticipants;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String str = "";
		
		str += "[ name         : " + this.name + "\n";
		str += "  year         : " + this.year + "\n";
		
		str += "  participants : [\n";
		for(String p : this.participants){
			str += "  \t" + p + "\n";
		}
		str += "                 ]\n";
		
		str += "  formatted participants : [\n";
		for(Map.Entry<String, Boolean> e : this.formattedParticipants.entrySet()){
			str += "  \t" + e.getKey() + " : " + e.getValue() + "\n";
		}
		str += "                 ]\n";
		
		str += "  coordinates : [\n";
		for(Coordinate c : this.places){
			str += "  \t" + c + "\n";
		}
		str += "                 ]\n";
		
		str += "]\n";
		
		return str;
	}
	
}