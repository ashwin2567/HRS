package com.hms.hotelService.services;

import java.util.List;

import com.hms.hotelService.entities.Hotel;

public interface HotelService {

	Hotel createHotel(Hotel hotel);
	
	List<Hotel> getAllHotel();
	
	Hotel getHotel(String id);
	
	void deleteHotelbyId(String id);
	
	Hotel updateUser(Hotel hotel);	
}
