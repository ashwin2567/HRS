package com.hms.userService.services;

import java.util.List;

import com.hms.userService.entities.User;

public interface UserService {

	//interface so we need not to define function body
	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String id);
	
	void deleteUserbyId(String id);
	
	void deleteUser(User user);
	
	User updateUser(User user);	
}
