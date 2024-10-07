package com.backend.Service;

import com.backend.entity.User;

public interface UserService {

	public User registerUser(User user);
	User authenticate(String email,String password);
	
}
