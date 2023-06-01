package com.prms.service;

import java.util.List;
import java.util.Optional;

import com.prms.entity.User;
import com.prms.entity.VerificationToken;
import com.prms.registration.RegistrationRequest;

/**
 * Interface for the user service.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see User
 * @see VerificationToken
 * @see RegistrationRequest
 */
public interface IUserService {
	 /**
     * Retrieves a list of all users.
     *
     * @return A list of all users.
     */
	public List<User> getUsers();
	/**
     * Registers a new user based on the provided registration request.
     *
     * @param request The registration request containing user details.
     * @return The registered user.
     */
	public User registerUser(RegistrationRequest request);
	/**
     * Finds a user by email.
     *
     * @param email The email of the user.
     * @return An optional containing the user, or empty if not found.
     */
	Optional<User> findByEmail(String email);
	/**
     * Saves a verification token for a user.
     *
     * @param theUser           The user for which the verification token should be saved.
     * @param verificationToken The verification token to be saved.
     */
	public void saveUserVerificationToken(User theUser, String verificationToken);
	/**
     * Validates a verification token.
     *
     * @param theToken The verification token to validate.
     * @return The result of the token validation.
     */
	public String validateToken(String theToken);
	/**
     * Generates a new verification token based on an old token.
     *
     * @param oldToken The old verification token.
     * @return The new verification token.
     */
	public VerificationToken generateNewVerificationToken(String oldToken);
}
