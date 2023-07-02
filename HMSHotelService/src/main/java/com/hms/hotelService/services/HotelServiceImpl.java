package com.hms.hotelService.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.hotelService.entities.Hotel;
import com.hms.hotelService.exception.ResourceNotFoundException;
import com.hms.hotelService.repositories.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		String randomUUID = UUID.randomUUID().toString();
		hotel.setHotelId(randomUUID);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotel(String id) {
		return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource with id "+id+" not found on server."));
	}

	@Override
	public void deleteHotelbyId(String id) {
		hotelRepository.deleteById(id);
		
	}

	@Override
	public Hotel updateUser(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

}
