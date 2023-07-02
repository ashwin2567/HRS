package com.hms.ratingService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.ratingService.entities.Rating;
import com.hms.ratingService.repositories.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating createRating(Rating rating) {
		String randomRatingId = UUID.randomUUID().toString();
		rating.setRatingId(randomRatingId);
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}

}
