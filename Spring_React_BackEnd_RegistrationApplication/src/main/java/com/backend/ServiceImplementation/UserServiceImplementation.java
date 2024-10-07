package com.backend.ServiceImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.Exception.InvalidCredentialsException;
import com.backend.Exception.UserAlreadyExistsException;
import com.backend.Repository.UserRepository;
import com.backend.Service.UserService;
import com.backend.entity.User;



@Service
public class UserServiceImplementation implements UserService{

	
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    public UserServiceImplementation(UserRepository userRepo,
                           PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
	
	@Override
	public User registerUser(User user) {
		if(userRepo.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistsException("Email is already in use");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public User authenticate(String email, String password) {
		Optional<User> userOpt=userRepo.findByEmail(email);
		if(userOpt.isPresent()) {
			User user=userOpt.get();
			if(passwordEncoder.matches(password, user.getPassword())) {
				return user;
			}
		}
		throw new InvalidCredentialsException("Invalid email or password");
	}

}
