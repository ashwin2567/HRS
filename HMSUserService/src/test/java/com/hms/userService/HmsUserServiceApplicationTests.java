package com.hms.userService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hms.userService.entities.Rating;
import com.hms.userService.external.services.RatingService;

@SpringBootTest
class HmsUserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private RatingService ratingService;
	
//	@Test
//	void createRatingTest() {
//		Rating r = new Rating("abc", "c8d816b8-83f6-4517-bcdc-625699287b68", "9cc4bb6c-be97-42d1-ae34-fe67025278cc", 5, "I love this hotel");
//		Rating savedRating = ratingService.createRating(r);
//		System.out.println(savedRating);
//	}
}
