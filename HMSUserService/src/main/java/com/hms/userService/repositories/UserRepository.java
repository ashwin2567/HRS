package com.hms.userService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.userService.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
