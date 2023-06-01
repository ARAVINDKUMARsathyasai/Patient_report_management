package com.prms.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prms.entity.User;
import com.prms.entity.VerificationToken;
import com.prms.helper.Message;
import com.prms.registration.RegistrationRequest;
import com.prms.repo.VerificationTokenRepository;
import com.prms.security.event.RegistrationCompleteEvent;
import com.prms.security.event.RegistrationCompleteEventListener;
import com.prms.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * The RegistrationController class is a Spring MVC controller responsible for handling user registration requests.
 * It provides methods for registering a new user, verifying email addresses, and resending verification tokens.
 * This class is used to manage the registration process for users in the system.
 * It interacts with the UserService, VerificationTokenRepository, and other necessary components.
 * The controller handles user registration, email verification, and token generation for email verification.
 * This class requires a valid ApplicationEventPublisher, HttpServletRequest, UserService, VerificationTokenRepository,
 * and RegistrationCompleteEventListener to be injected via constructor-based dependency injection.
 * It also uses the HttpSession, Model, and BindingResult for handling the registration process.
 * The controller is mapped to "/register" and provides methods for registering a new user, verifying email addresses,
 * and resending verification tokens.
 *
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023 
 * 
 * @see User
 * @see VerificationToken
 * @see Message
 * @see RegistrationRequest
 * @see VerificationTokenRepository
 * @see RegistrationCompleteEvent
 * @see RegistrationCompleteEventListener
 * @see UserService
*/
@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
	
	/**
	 * Tight coupling of the UserService and are initialized in constructor
	 * 
	 * @see UserService
	 */
	private final UserService userService;
	/**
	 * Tight coupling of the ApplicationEventPublisher and are initialized in constructor
	 * 
	 * @see ApplicationEventPublisher
	 */
	private final ApplicationEventPublisher publisher;
	/**
	 * Tight coupling of the VerificationTokenRepository and are initialized in constructor
	 * 
	 * @see VerificationTokenRepository
	 */	
	private final VerificationTokenRepository tokenRepository;
	/**
	 * Tight coupling of the RegistrationCompleteEventListener and are initialized in constructor
	 * 
	 * @see RegistrationCompleteEventListener
	 */
	private final RegistrationCompleteEventListener eventListener;
	/**
	 * Tight coupling of the HttpServletRequest and are initialized in constructor
	 */
	private final HttpServletRequest servletRequest;
	
	//@RequestMapping(value="/do_register",method=RequestMethod.POST)
	//public String regisetrUser(@RequestParam, RegistrationRequest registrationRequest, final HttpServletRequest request)
	/**
	 * Handles the user registration process.
	 *
	 * @param user      The User object containing the user's registration details.
	 * @param res       The BindingResult object for handling validation errors.
	 * @param agreement The agreement flag indicating whether the user agreed to terms and conditions.
	 * @param model     The Model object used to pass data to the view.
	 * @param session   The HttpSession object for storing session data.
	 * @param request   The HttpServletRequest object for retrieving application URL.
	 * @return If the registration is successful, it redirects to the registration success page.
	 *         If there are validation errors or registration fails, it redirects back to the registration page with an error message.
	 */
	@PostMapping("/do_register")
	public String regisetrUser(@ModelAttribute("user") User user,
							   BindingResult res,
			                   @RequestParam(value="agreement",
			                   defaultValue="false") boolean agreement,
			                   Model model,
			                   final HttpSession session ,final HttpServletRequest request)
	{	
		try {
	        if (!agreement) {
	            // Check if the user has agreed to the terms and conditions
	            System.out.println("You have not agreed to the terms and conditions");
	            throw new Exception("You have not agreed to the terms and conditions!");
	        }
	        
	        if (res.hasErrors()) {
	            // Check if there are any binding errors
	            System.out.println("Error " + res.toString());
	            model.addAttribute("user", user);
	            // return "patient/signup";
	        }
	        
	        // Set default values for the user
	        user.setRole("USER");
	        user.setEnabled(true);
	        user.setImageUrl("default.png");
	        
	        // Create a RegistrationRequest object with user details
	        RegistrationRequest registrationRequest = new RegistrationRequest(
	            user.getFullname(),
	            user.getEmail(),
	            user.getPassword(),
	            user.getRole(),
	            user.isEnabled(),
	            user.getImageUrl()
	        );
	        
	        // Register the user and get the registered user object
	        User regUser = userService.registerUser(registrationRequest);
	        model.addAttribute(user);
	        
	        // Publish a registration complete event
	        publisher.publishEvent(new RegistrationCompleteEvent(regUser, applicationUrl(request)));
	        
	        // Reset the user object
	        user = new User();
	        model.addAttribute("user", new User());
	        
	        // Set the success message and redirect to the signup page
	        session.setAttribute("message", new Message("Success! Please check your email to complete your registration", "alert-success"));
	        return "patient/signup";
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("user", user);
	        
	        // Handle different exception cases and set appropriate error messages
	        if (e.getMessage().equals("You have not agreed to the terms and conditions!")) {
	            session.setAttribute("message", new Message(e.getMessage(), "alert-danger"));
	        } else if (e.getMessage().equals("Account exists with " + user.getEmail())) {
	            session.setAttribute("message", new Message("Account exists with " + user.getEmail(), "alert-danger"));
	        } else {
	            session.setAttribute("message", new Message("Something went wrong. Please try again!", "alert-danger"));
	        }
	        
	        // Return to the signup page
	        return "patient/signup";
	    }
	}
	
	/**
	 * Handles the verification of the user's email address based on the provided token.
	 *
	 * @param token   The verification token sent to the user's email.
	 * @param session The HttpSession object for storing session data.
	 * @return If the email verification is successful, it displays a success message.
	 *         If the email verification fails, it displays an error message.
	 */
	@GetMapping("/verifyEmail")
	public String verifyEmail(@RequestParam("token") String token, final HttpSession session) {
	    // Get the URL for resending verification token
	    String url = applicationUrl(servletRequest) + "/register/resend-verification-token?token=" + token;
	    
	    // Find the verification token by token string
	    VerificationToken theToken = tokenRepository.findByToken(token);
	    
	    if (theToken.getUser().isChecked()) {
	        // Check if the account has already been verified
	        session.setAttribute("message", new Message("This account has already been verified, please log in!", "alert-success"));
	        return "helper/message";
	    }
	    
	    // Validate the verification token
	    String verificationResult = userService.validateToken(token);
	    
	    if (verificationResult.equalsIgnoreCase("valid")) {
	        // If the token is valid, set the success message and redirect to the success page
	        session.setAttribute("message", new Message("Email verified successfully. Now you can log in to your account", "alert-success"));
	        return "helper/message";
	    }
	    
	    // If the verification link is invalid, set the appropriate error message with a new verification link
	    session.setAttribute("message", new Message("Invalid verification link, <a href=\"" + url + "\">Get a new verification link.</a>", "alert-danger"));
	    return "helper/wrong_message";
	}

	
	/**
	 * Resends a verification token to the user's email for email address verification.
	 *
	 * @param oldToken The old verification token to be replaced with a new one.
	 * @param request  The HttpServletRequest object for retrieving application URL.
	 * @return A message indicating that a new verification link has been sent to the user's email.
	 * @throws UnsupportedEncodingException If there is an issue with encoding.
	 * @throws MessagingException           If there is an issue with sending the verification email.
	 */
	@GetMapping("/resend-verification-token")
	public String resendVerificationToken(@RequestParam("token") String oldToken, final HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
	    // Generate a new verification token based on the old token
	    VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
	    
	    // Get the user associated with the verification token
	    User theUser = verificationToken.getUser();
	    
	    // Resend the verification token email to the user
	    resendVerificationTokenEmail(theUser, applicationUrl(request), verificationToken);
	    
	    return "A new verification link has been sent to your email. Please check to activate your registration.";
	}

	
	/**
	 * Resends the verification token email to the user's email address.
	 *
	 * @param theUser            The User object containing the user's details.
	 * @param applicationUrl     The URL of the application.
	 * @param verificationToken  The new verification token.
	 * @throws UnsupportedEncodingException If there is an issue with encoding.
	 * @throws MessagingException           If there is an issue with sending the verification email.
	 */
	private void resendVerificationTokenEmail(User theUser, String applicationUrl, VerificationToken verificationToken) throws UnsupportedEncodingException, MessagingException {
	    // Construct the verification email URL
	    String url = applicationUrl + "/register/verifyEmail?token=" + verificationToken.getToken();
	    
	    // Send the verification email with the URL
	    eventListener.sendVerificationEmail(url);
	}

	/**
	 * Retrieves the application URL based on the HttpServletRequest.
	 *
	 * @param request The HttpServletRequest object.
	 * @return The application URL.
	 */
	private String applicationUrl(HttpServletRequest request) {
	    // Construct the base application URL using the request information
	    return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
}
