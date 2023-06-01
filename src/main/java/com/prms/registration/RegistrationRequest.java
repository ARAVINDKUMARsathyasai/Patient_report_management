package com.prms.registration;

/**
 * Represents a registration request with the user's full name, email, password, role, enabled status, and image URL.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 */
public record RegistrationRequest(
		String fullname,
		String email,
		String password,
		String role,
		boolean enabled,
		String imageUrl
		) {

}
