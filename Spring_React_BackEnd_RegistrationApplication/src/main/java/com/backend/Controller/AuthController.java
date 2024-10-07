package com.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.Service.UserService;
import com.backend.entity.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") //Adjust based on your frontend URL
public class AuthController {

	
	private UserService userService;
	
	
	 @Autowired
	    public AuthController(UserService userService) {
	        this.userService = userService;
	    }
	 
	@SuppressWarnings("unused")
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody User user){
		User registerUser=userService.registerUser(user);
		return ResponseEntity.ok("User authenticated successfully");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		User user=userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		//Here we can generate JWT ot set session

		return ResponseEntity.ok("User login successfully");
		
	}
	//DTO for Login
	public static class LoginRequest{
		@NotBlank(message="Email is mandatory")
		@Email(message = "Email should be valid")
		private String email;
		
		 @NotBlank(message = "Password is mandatory")
	        private String password;

	        // Getters and Setters
	        public String getEmail() {
	            return email;
	        }
	        public void setEmail(String email) {
	            this.email = email;
	        }
	        public String getPassword() {
	            return password;
	        }
	        public void setPassword(String password) {
	            this.password = password;
	        }
	}
	
}
