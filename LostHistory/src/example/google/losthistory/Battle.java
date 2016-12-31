package example.google.losthistory;

import java.util.ArrayList;
import java.util.List;

public class Battle {
	private List<Coordinate> places;
	private List<String> participants;
	private int year;
	private String name;
	
	public Battle(String name){
		this.name = name;
		this.places = new ArrayList<Coordinate>();
		this.participants = new ArrayList<String>();
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
		
		str += "  coordinates : [\n";
		for(Coordinate c : this.places){
			str += "  \t" + c + "\n";
		}
		str += "                 ]\n";
		
		str += "]\n";
		
		return str;
	}
	
}

class Coordinate{
	double latitude;
	double longitude;
	
	Coordinate(double latitude, double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "("+latitude+", " + longitude + ")";
	}
}