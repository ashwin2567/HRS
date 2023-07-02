package com.hms.hotelService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.hotelService.entities.Hotel;
import com.hms.hotelService.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	@Autowired
	private HotelService hotelService;
	
	@GetMapping("/")
	public String homepage() {
		return "Hotel service is running";
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		Hotel h = hotelService.createHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(h);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
		Hotel h = hotelService.getHotel(hotelId);
		return ResponseEntity.status(HttpStatus.OK).body(h);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotel(){
		return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAllHotel());
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<Hotel> updateUser(@RequestBody Hotel hotel, @PathVariable String userId){
		hotel.setHotelId(userId);
		Hotel h = hotelService.updateUser(hotel);
		return ResponseEntity.ok(h);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUer(@PathVariable String userId){
		hotelService.deleteHotelbyId(userId);
		return ResponseEntity.ok("Hotel Deleted");
	}
}
