package com.leaderboard.Service;

import java.util.List;

import com.leaderboard.Entity.Score;
import com.leaderboard.Model.ScoreModel;

public interface ScoreService {

	/* Save operation
	 * Saving data to database 
	 */
	
	Score saveScore(ScoreModel scoreModel) throws Exception;

	List<Score> fetchAllDetails();

}
