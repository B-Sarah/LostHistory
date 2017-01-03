package example.google.losthistory;

import java.io.Serializable;

public class ParticpantFormatted implements Serializable{
	private String name;
	private boolean isCorrect;
	
	public ParticpantFormatted(){}
	
	public ParticpantFormatted(String name, boolean isCorrect){
		this.name = name;
		this.isCorrect = isCorrect;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	
}
