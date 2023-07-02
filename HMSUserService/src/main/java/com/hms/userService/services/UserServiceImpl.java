package com.hms.userService.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hms.userService.entities.Hotel;
import com.hms.userService.entities.Rating;
import com.hms.userService.entities.User;
import com.hms.userService.exception.ResourceNotFoundException;
import com.hms.userService.external.services.HotelService;
import com.hms.userService.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id '"+id+"' not found on server."));
		//fetch Rating of above user from RATING SERVICE
		//https://localhost:8082/rating/users/ae0461f2-7d9a-42ef-b778-01a71d563ad0
		Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+id, Rating[].class);
		//logger.info("{}",ratingOfUser);
		List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
		
		List<Rating> ratingListWithHotelDetails = ratings.stream().map(rating -> {
			//api call to hotel service
//			Hotel hotelOfRating = restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			//logger.info("{}", hotelOfRating);
			//set hotel to rating
//			rating.setHotel(hotelOfRating);
			rating.setHotel(hotel);
			//return rating
			return rating;
		}).collect(Collectors.toList());
		
		
		
		user.setRatings(ratingListWithHotelDetails);
		return user;
		//userRepository.findById(id);
		//return new User();
	}

	@Override
	public void deleteUserbyId(String id) {
		userRepository.deleteById(id);
	}

	

	@Override
	public User updateUser(User u) {
		return userRepository.save(u);
		
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}
}
