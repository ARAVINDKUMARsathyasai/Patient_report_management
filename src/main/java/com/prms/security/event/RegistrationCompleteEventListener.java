package com.prms.security.event;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.prms.entity.User;
import com.prms.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Application listener that handles the registration completion event.
 * It generates a verification token, saves it for the user, and sends a verification email.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see User
 * @see UserService
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

	/**
	 * The service for managing user-related operations.
	 */
	private final UserService userService;
	/**
	 * The mail sender used to send verification emails.
	 */
	private final JavaMailSender mailSender;
	/**
	 * The user associated with the registration completion event.
	 */
	private User theUser;
	
	/**
     * Handles the registration completion event.
     * Generates a verification token, saves it for the user, and sends a verification email.
     *
     * @param event The registration completion event.
     */
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		//get newly registered user 
		 theUser = event.getUser();
		//create a verification token for the user
		String verificationToken = UUID.randomUUID().toString();
		//save verification token of the user
		userService.saveUserVerificationToken(theUser,verificationToken);
		//Build verification url to send to the user
		String url = event.getApplicationUrl()+"/register/verifyEmail?token="+verificationToken;
		//send the email
		try {
			sendVerificationEmail(url);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
		log.info("click the link to verify your registration : {}",url);
	}
	
	/**
     * Sends a verification email to the user.
     *
     * @param url The verification URL.
     * @throws UnsupportedEncodingException If the encoding is not supported.
     * @throws MessagingException If an error occurs while sending the email.
     */
	public void sendVerificationEmail(String url) throws UnsupportedEncodingException, MessagingException {
		String subject="Email verification";
		String senderName ="Manipal Patient Record Management";
		String mailContent = "<p>Hi, "  +theUser.getFullname()+", </p>"+"<p>Thank you for registering with us,</p>"+"<br>"+"<p>"
		                                +""+"Please, follow the link below to complete your registration.</p>"+
				                         "<a href=\""+url+"\">verify your email to activate your account</a>"+
		                                "<p>Thank you <br> Manipal Patient Record Management";
		MimeMessage message = mailSender.createMimeMessage();
		var messageHelper = new MimeMessageHelper(message);
		messageHelper.setFrom("sanjanasupermart@gmail.com",senderName);
		messageHelper.setTo(theUser.getEmail());
		messageHelper.setSubject(subject);
		messageHelper.setText(mailContent,true);
		mailSender.send(message);
	}

}
