package com.prms.security.event;

import org.springframework.context.ApplicationEvent;

import com.prms.entity.User;

import lombok.Getter;
import lombok.Setter;

/**
 * Application event class representing the completion of user registration.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see User
 */
@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent{
	/**
	 * The User associated with the registration completion event.
	 */
	private User user;
	/**
	 * The URL of the application.
	 */
	private String applicationUrl;
	
	/**
	 * Constructs a new RegistrationCompleteEvent.
	 *
	 * @param user           The registered User.
	 * @param applicationUrl The URL of the application.
	 */
	public RegistrationCompleteEvent(User user,String applicationUrl) {
		super(user);
		this.user = user;
		this.applicationUrl=applicationUrl;
	}
}
