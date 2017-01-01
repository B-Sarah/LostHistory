package example.google.losthistory;

public class Coordinate{
	double latitude;
	double longitude;
	
	public Coordinate(double latitude, double longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "("+latitude+", " + longitude + ")";
	}
}