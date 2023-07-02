package com.hms.ratingService.service;

import java.util.List;

import com.hms.ratingService.entities.Rating;

public interface RatingService{
	Rating createRating(Rating rating);
	
	List<Rating> getAllRating();
	
	List<Rating> getByUserId(String userId);
	
	List<Rating> getByHotelId(String hotelId);
	
}
