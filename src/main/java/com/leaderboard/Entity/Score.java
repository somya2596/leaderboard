package com.leaderboard.Entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "top-scores")
public class Score implements Comparable<Score> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	Integer id;

	@Column(name = "player_name")
	private String playerName;

	@Column(name = "score")
	@NonNull
	private Integer score;

	public Score() {

	}

	public Score(String playerName, Integer score) {
		this.playerName = playerName;
		this.score = score;
	}

	public int getId() {
		return id;
	}

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

	@Override
	public String toString() {
		return "Score [id=" + id + ", playerName=" + playerName + ", score=" + score + "]";
	}

	@Override
	public int compareTo(Score obj) {
		// we sort objects on the basis of score
		return (this.score - obj.score);
	}

}
