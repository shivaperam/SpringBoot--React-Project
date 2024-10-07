package com.backend.Exception;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends RuntimeException {

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
