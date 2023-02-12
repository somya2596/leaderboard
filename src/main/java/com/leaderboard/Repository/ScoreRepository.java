package com.leaderboard.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leaderboard.Entity.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {

	Score findByScore(Integer score);

	List<Score> findAllByOrderByScoreDesc();

}
