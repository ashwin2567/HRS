package com.hms.ratingService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.ratingService.entities.Rating;
import com.hms.ratingService.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@GetMapping("/")
	public String homepage(){
		return "Rating service is running.";
	}
	
	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
	} 
	
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRating(){
		return ResponseEntity.ok(ratingService.getAllRating());
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingForUser(@PathVariable String userId){
		return ResponseEntity.ok(ratingService.getByUserId(userId));
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingForHotel(@PathVariable String hotelId){
		return ResponseEntity.ok(ratingService.getByHotelId(hotelId));
	}
}
