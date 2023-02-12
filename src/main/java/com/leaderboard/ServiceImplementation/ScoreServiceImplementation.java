package com.leaderboard.ServiceImplementation;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.leaderboard.Entity.Score;
import com.leaderboard.Model.ScoreModel;
import com.leaderboard.Repository.ScoreRepository;
import com.leaderboard.Service.ScoreService;

@Service
public class ScoreServiceImplementation implements ScoreService {


	@Autowired
	private ScoreRepository scoreRepository;
	
	public ScoreServiceImplementation(ScoreRepository scoreRepository) {
		this.scoreRepository = scoreRepository;
	}

	@Value("${top.score.value}")
	private Integer numOfTopScore;

	private static final Logger LOGGER = LoggerFactory.getLogger(ScoreServiceImplementation.class);

	@KafkaListener(topics = "ENDGAME", groupId = "id", containerFactory = "scoreListner")
	public void consume(ScoreModel scoreModel) {
		LOGGER.info(String.format("Json message received -> %s", scoreModel.toString()));
		Score response = saveScore(scoreModel);
		LOGGER.info(String.format("Data saved succesfully -> %s", response.toString()));

	}

	public Score saveScore(ScoreModel scoreModel) {

		Score score = new Score();
		score.setPlayerName(scoreModel.getPlayerName());
		score.setScore(scoreModel.getScore());
		List<Score> input = scoreRepository.findAll();

		if (!input.isEmpty()) {
			// comparing score with minimum score in input list
			Score min = input.stream().min(Comparator.comparing(Score::getScore))
					.orElseThrow(NoSuchElementException::new);

			if (input.size() < numOfTopScore) {
				return scoreRepository.save(score);

			} else if (score.getScore() >= min.getScore()) {
				Optional<Score> oldObject = scoreRepository.findById(min.getId());
				if (oldObject.isPresent()) {
					scoreRepository.deleteById(min.getId());
				}
				return scoreRepository.save(score);
			}
		} else {
			// save to the database;
			return scoreRepository.save(score);
		}
		return scoreRepository.save(score);
	}

	@Override
	public List<Score> fetchAllDetails() {
		// always contains top 5 scores object and sort from high to low
		List<Score> scoreDetails = scoreRepository.findAllByOrderByScoreDesc();
		return scoreDetails;
	}

}
