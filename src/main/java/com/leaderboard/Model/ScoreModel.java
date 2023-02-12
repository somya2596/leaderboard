package com.leaderboard.Model;

import java.io.Serializable;

public class ScoreModel implements Serializable{


	private static final long serialVersionUID = 1L;
	private String playerName;
	private Integer score;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public ScoreModel () {
		
	}
	public ScoreModel(String playerName, Integer score) {
		super();
		this.playerName = playerName;
		this.score = score;
	}

	@Override
	public String toString() {
		return "ScoreModel [playerName=" + playerName + ", score=" + score + "]";
	}

}
