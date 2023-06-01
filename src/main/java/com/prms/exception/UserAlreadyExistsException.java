package com.prms.exception;

/**
 * Exception thrown when attempting to create a user that already exists.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 */
public class UserAlreadyExistsException extends RuntimeException {
	/**
	 * Constructs a new UserAlreadyExistsException with the specified error message.
	 *
	 * @param message the error message
	 */
	public UserAlreadyExistsException(String message){
		super(message);
	}
}
