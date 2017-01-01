package example.google.losthistory;

import java.io.Serializable;

public class Score implements Comparable<Score>, Serializable{

	int score;
	String username;
	
	public Score(){}
	public Score(String username, int score){
		this.username = username;
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public int compareTo(Score o) {
		if(this.score < o.score) return 1;
		if(this.score > o.score) return -1;
		return 0;
	}
	
	
	
}
