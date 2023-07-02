package com.hms.hotelService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.hotelService.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
