package com.prms.entity;

import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a verification token entity used for email verification.
 * This token is associated with a user and has an expiration time.
 * 
 * <p>
 * The token is generated with a specific expiration time and can be used to verify the user's email.
 * </p>
 * 
 * <p>
 * The token is associated with a user entity to establish a relationship between the token and the user.
 * </p>
 * 
 * <p>
 * The token has a unique identifier, a string token value, an expiration time, and a reference to the associated user.
 * </p>
 * 
 * <p>
 * The token expiration time is set to a default value of 10 minutes.
 * </p>
 * 
 * <p>
 * Note: The token expiration time is calculated based on the current system time and is used for verification purposes.
 * </p>
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see User
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
public class VerificationToken {
	
	/**
	 * The unique identifier of the verification token.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The string value of the verification token.
	 */
	private String token;
	
	/**
	 * The expiration time of the verification token.
	 * After this time, the token is considered expired.
	 */
	private Date expirationTime;
	
	/**
	 * The default expiration time duration in minutes for the verification token.
	 */
	private static final int EXPIRATION_TIME = 10;
	
	/**
	 * The associated user for the verification token.
	 */
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;

	/**
	 * Constructs a new VerificationToken object with the specified token value.
	 * The expiration time is automatically set based on the current system time.
	 * 
	 * @param token The string token value.
	 */
	public VerificationToken(String token) {
		super();
		this.token = token;
		this.expirationTime = this.getTokenExpirationTime();
	}
	
	/**
	 * Constructs a new VerificationToken object with the specified token value and associated user.
	 * The expiration time is automatically set based on the current system time.
	 * 
	 * @param token The string token value.
	 * @param user The associated user.
	 */
	public VerificationToken(String token, User user) {
		super();
		this.token = token;
		this.user = user;
		this.expirationTime = this.getTokenExpirationTime();
	}

	/**
	 * Calculates and returns the token expiration time based on the current system time.
	 * The expiration time is set to the current time plus the default expiration duration (10 minutes).
	 * 
	 * @return The token expiration time.
	 */
	public Date getTokenExpirationTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
		
		return new Date(calendar.getTime().getTime());
	}
}
