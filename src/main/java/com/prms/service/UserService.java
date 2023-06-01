package com.prms.service;

import com.prms.entity.VerificationToken;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prms.entity.User;
import com.prms.exception.UserAlreadyExistsException;
import com.prms.registration.RegistrationRequest;
import com.prms.repo.UserRepository;
import com.prms.repo.VerificationTokenRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing user-related operations.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see User
 * @see UserAlreadyExistsException
 * @see RegistrationRequest
 * @see UserRepository
 * @see VerificationTokenRepository
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

	/**
	 * Repository for managing user data.
	 */
	private final UserRepository userRepository;
	/**
	 * Password encoder for encrypting and verifying passwords.
	 */
	private final PasswordEncoder passwordEncoder;
	/**
	 * Repository for managing verification tokens.
	 */
	private final VerificationTokenRepository tokenRepository;
	
	/**
     * Retrieves a list of all users.
     *
     * @return List of User objects representing all users.
     */
	@Override
	public List<User> getUsers() {
		
		return userRepository.findAll();
	}

	/**
     * Registers a new user based on the provided registration request.
     *
     * @param request The RegistrationRequest object containing user registration details.
     * @return The newly registered User object.
     * @throws UserAlreadyExistsException if a user with the same email already exists.
     */
	@Override
	public User registerUser(RegistrationRequest request) {
		Optional<User> user = this.findByEmail(request.email());
		if(user.isPresent()) {
			throw new UserAlreadyExistsException("User with email "+request.email()+ " already exists");
		}
		var newUser = new User();
		newUser.setFullname(request.fullname());
		newUser.setEmail(request.email());
		newUser.setPassword(passwordEncoder.encode(request.password()));
		newUser.setRole(request.role());
		newUser.setEnabled(request.enabled());
		newUser.setImageUrl(request.imageUrl());
		return userRepository.save(newUser);
	}

	/**
     * Finds a user by email.
     *
     * @param email The email of the user to find.
     * @return An Optional containing the User object if found, or an empty Optional if not found.
     */
	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	/**
     * Saves a verification token for a user.
     *
     * @param theUser The User object for which to save the verification token.
     * @param token   The verification token to be saved.
     */
	@Override
	public void saveUserVerificationToken(User theUser, String token) {
		var verificationToken = new VerificationToken(token,theUser);
		tokenRepository.save(verificationToken);
	}
	/**
     * Validates a verification token.
     *
     * @param theToken The verification token to validate.
     * @return A string indicating the validation result.
     */
	@Override
	public String validateToken(String theToken) {
		VerificationToken token = tokenRepository.findByToken(theToken);
		if(token == null) {
			return "Invalid verification code";
		}
		
		User user  = token.getUser();
		Calendar calendar = Calendar.getInstance();
		if((token.getExpirationTime().getTime()-calendar.getTime().getTime())<= 0) {
			tokenRepository.delete(token);
			
			return "Token already expired";
		}
		user.setChecked(true);
		userRepository.save(user);
		return "valid";
	}
	/**
     * Generates a new verification token based on the provided old token.
     *
     * @param oldToken The old verification token.
     * @return The newly generated VerificationToken object.
     */
	@Override
	public VerificationToken generateNewVerificationToken(String oldToken) {
		VerificationToken verificationToken = tokenRepository.findByToken(oldToken);
		var verificationTokenTime = new VerificationToken();
		
		verificationToken.setToken(UUID.randomUUID().toString());
		verificationToken.setExpirationTime(verificationTokenTime.getTokenExpirationTime());
		return tokenRepository.save(verificationToken);
	}
}
