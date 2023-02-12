package com.leaderboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.leaderboard.Entity.Score;
import com.leaderboard.Model.ScoreModel;
import com.leaderboard.Repository.ScoreRepository;
import com.leaderboard.ServiceImplementation.ScoreServiceImplementation;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class LeaderboardApplicationTests {

	  // Inject the ScoreRepository instance into the ScoreServiceImplementation instance
		@InjectMocks
		private ScoreServiceImplementation consumeScores;
		
		// Create a mock instance of ScoreRepository
		@Mock
		private ScoreRepository scoreRepository;

		// Test the saveScore method
		@Test
		public void testSaveScore() {
			// Create a ScoreModel instance
			ScoreModel scoreModel = new ScoreModel();
			scoreModel.setPlayerName("player1");
			scoreModel.setScore(100);
			
			// Create a Score instance
			Score score = new Score();
			score.setPlayerName("player1");
			score.setScore(100);
			
			// Configure the scoreRepository.save method to return the Score instance
			Mockito.when(scoreRepository.save(Mockito.any(Score.class))).thenReturn(score);
			
			// Call the saveScore method and store the result in a Score instance
			Score result = consumeScores.saveScore(scoreModel);
			System.out.println("SCORE-RESULT:" + score +" " + result);
			
			// Verify that the result is equal to the Score instance
			assertEquals(score, result);
			
			// Verify that the scoreRepository.save method was called once
			Mockito.verify(scoreRepository, Mockito.times(1)).save(Mockito.any(Score.class));
		}
}
