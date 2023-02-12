package com.leaderboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leaderboard.Model.ScoreModel;

@RestController
@RequestMapping("/kafka")
public class PublishScoresController {

	@Autowired
	private KafkaTemplate<String, ScoreModel> kafkaTemplate;

	private static final String TOPIC = "ENDGAME";

	@PostMapping("/publish")
	public ResponseEntity<String> publishMessage(@RequestBody ScoreModel score) {
		try {
			if(score.getScore()==null) {
				 return new ResponseEntity<String>("Score cannot be null..!!",HttpStatus.BAD_REQUEST);
			}
			kafkaTemplate.send(TOPIC, score);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Published successfully", HttpStatus.OK);
	}

}
