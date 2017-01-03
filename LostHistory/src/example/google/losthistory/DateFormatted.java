package example.google.losthistory;

import java.io.Serializable;

public class DateFormatted implements Serializable{

	private int year;
	private boolean isCorrect;
	
	public DateFormatted(){}
	
	public DateFormatted(int year, boolean isCorrect){
		this.year = year;
		this.isCorrect = isCorrect;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	
}
