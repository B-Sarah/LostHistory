package example.google.losthistory;

import java.io.Serializable;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Battle implements Serializable {
	private List<Coordinate> places;
	private List<String> participants;
	private List<ParticpantFormatted> formattedParticipants;
	private List<DateFormatted> formattedDates;
	private int year;
	private String name;
	
	public Battle(String name){
		this.name = name;
		this.places = new ArrayList<Coordinate>();
		this.participants = new ArrayList<String>();
		this.formattedParticipants = new ArrayList<ParticpantFormatted>();
		this.formattedDates = new ArrayList<DateFormatted>();
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
			this.formattedParticipants.add(new ParticpantFormatted(participants.get(i), true));
		}
		for(int i = 0; i < 4-picked; i++){
			String rdmPart = "";
			do{
				rdmPart = pList.get(rdm.nextInt(pList.size()));
			} while(ListParticipantContains(rdmPart));
			this.formattedParticipants.add(new ParticpantFormatted(rdmPart, false));
		}
	}
	
	private boolean ListParticipantContains(String str){
		boolean contained = false;
		for(ParticpantFormatted e : this.formattedParticipants){
			if(e.getName().equals(str)){
				contained = true;
				break;
			}
		}
		return contained;
	}
	
	public void PickFormattedDates(){
		Random rdm = new Random();
		this.formattedDates.add(new DateFormatted(year, true));
		for(int i = 0; i < 3; i++){
			this.formattedDates.add(new DateFormatted(rdm.nextInt(2017+2000)-2000, false));
		}
		Collections.shuffle(this.formattedDates);
	}

	

	public List<ParticpantFormatted> getFormattedParticipants() {
		return formattedParticipants;
	}

	public void setFormattedParticipants(List<ParticpantFormatted> formattedParticipants) {
		this.formattedParticipants = formattedParticipants;
	}

	public List<DateFormatted> getFormattedDates() {
		return formattedDates;
	}

	public void setFormattedDates(List<DateFormatted> formattedDates) {
		this.formattedDates = formattedDates;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String str = "";
		
		str += "[ name         : " + this.name + "\n";
		str += "  year         : " + this.year + "\n";
		
		str += "  formatted year : [\n";
		for(DateFormatted e : this.formattedDates){
			str += "  \t" + e.getYear() + " : " + e.isCorrect() + "\n";
		}
		str += "                 ]\n";
		
		str += "  participants : [\n";
		for(String p : this.participants){
			str += "  \t" + p + "\n";
		}
		str += "                 ]\n";
		
		str += "  formatted participants : [\n";
		for(ParticpantFormatted e : this.formattedParticipants){
			str += "  \t" + e.getName() + " : " + e.isCorrect() + "\n";
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