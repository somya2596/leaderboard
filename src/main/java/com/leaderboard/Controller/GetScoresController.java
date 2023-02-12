package com.leaderboard.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leaderboard.Entity.Score;
import com.leaderboard.Service.ScoreService;

@RestController
@RequestMapping("/api")
public class GetScoresController {

	@Autowired
	private ScoreService scoreService;

	@GetMapping("/getscoredetails")
	public ResponseEntity<List<Score>> getScoreDetails() {
		List<Score> scoreDetails = scoreService.fetchAllDetails();
		if (scoreDetails.isEmpty() || scoreDetails == null) {
			return new ResponseEntity<List<Score>>(new ArrayList<>(),HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Score>>(scoreService.fetchAllDetails(), HttpStatus.OK);
		}
	}

}
