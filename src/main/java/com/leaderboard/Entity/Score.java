package com.leaderboard.Entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "top-scores")
@Data
@AllArgsConstructor
@ToString
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

	@Override
	public int compareTo(Score obj) {
		// we sort objects on the basis of score
		return (this.score - obj.score);
	}

}
