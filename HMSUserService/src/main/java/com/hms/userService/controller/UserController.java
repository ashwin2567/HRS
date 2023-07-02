package com.hms.userService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.userService.entities.User;
import com.hms.userService.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.spring6.fallback.FallbackMethod;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/")
	public String homepage() {
		return "microservices server is running";
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User u = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(u);
	}
	
	int retryCount = 1;
	
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//	@Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "ratingHotelRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		User u = userService.getUser(userId);
		logger.info("retry count {}", retryCount);
		retryCount++;
		return ResponseEntity.ok(u);
	}
	
	//creating fallback method for breaker
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception e){
		logger.info("Fallback is executed because RATING SERVICE is down", e.getMessage());
		User user = new User("1", "dummy", "dummy@dummy.com");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> list = userService.getAllUser();
		return ResponseEntity.ok(list);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userId){
		user.setId(userId);
		User u = userService.updateUser(user);
		return ResponseEntity.ok(u);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUer(@PathVariable String userId){
		userService.deleteUserbyId(userId);
		return ResponseEntity.ok("User Deleted");
	}
}
