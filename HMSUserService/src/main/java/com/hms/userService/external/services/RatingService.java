package com.hms.userService.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.hms.userService.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@PostMapping("/rating")
	public Rating createRating(Rating rating);
	
	@PutMapping("/rating/{ratingId}")
	public Rating updateRating(@PathVariable String ratingId, Rating rating);
	
	@DeleteMapping("/rating/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);
}
