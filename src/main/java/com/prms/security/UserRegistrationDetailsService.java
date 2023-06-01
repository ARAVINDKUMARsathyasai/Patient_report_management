package com.prms.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prms.repo.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * This service class implements the UserDetailsService interface to provide user registration details for Spring Security.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see UserRepository
 */
@Service
@RequiredArgsConstructor
public class UserRegistrationDetailsService implements UserDetailsService{
	/**
	 * The UserRepository instance used for accessing user data in the database.
	 */
	private final UserRepository userRepository;
	
	/**
     * Loads user details by username (email) and returns a UserDetails object.
     *
     * @param email the email (username) of the user
     * @return a UserDetails object representing the user details
     * @throws UsernameNotFoundException if the user with the specified email is not found
     */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email)
							 .map(UserRegistrationDetails::new)
							 .orElseThrow(()->new UsernameNotFoundException("User not found !!"));
	}
}
