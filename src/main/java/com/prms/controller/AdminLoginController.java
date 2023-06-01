package com.prms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import com.prms.service.AdminLoginService;
import com.prms.entity.Admin;
import com.prms.helper.Message;
import com.prms.helper.*;

/**
 *The AdminLoginController class is a Spring MVC controller responsible for handling admin login requests.
 *It provides methods for validating admin credentials, authenticating the user, and performing the login process.
 *This class is used to manage the login functionality for admin users in the system.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * @see AdminLoginService
 * @see Admin
*/
@Controller
public class AdminLoginController {
	/**
	 * Loose coupling of the AdminLoginService class
	 * 
	 * @see AdminLoginService
	 */
	@Autowired
	private AdminLoginService adminService;
	
	/**
	 * Loose coupling of the PasswordEncoder Bean
	 * 
	 * @see PasswordEncoder
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * Handles the admin login request and authenticates the user.
	 *
	 * @param email    The email address provided by the user during login.
	 * @param password The password provided by the user during login.
	 * @param model    The Model object used to pass data to the view.
	 * @return If the login is successful, it redirects to the admin index page.
	 *         If the login fails, it redirects back to the admin login page with an error message.
	 */	
	@PostMapping("/adminLogin")
	public String adminLogin(@RequestParam("username") String email, @RequestParam("password") String password, Model model) {
		// Create a new Admin object
		Admin user = new Admin();
		
		// Set the email and password properties of the Admin object
		user.setEmail(email);
		user.setPassword(password);
		
		// Call the login method of the adminService to authenticate the user
		Admin oauthUser = adminService.login(user.getEmail(), user.getPassword());
		
		// Print the logged-in Admin information for debugging purposes
		System.out.println("Admin login: " + oauthUser);
		
		// Check if the authentication was successful
		if (oauthUser != null) {
			// Set the fullname property of the user object to the logged-in Admin's fullname
			user.setFullname(oauthUser.getFullname());
			
			// Set the static variable userName in AdminHelper to the logged-in Admin's fullname
			AdminHelper.userName = oauthUser.getFullname();
			
			// Redirect the user to the admin index page
			return "redirect:/admin/index";
		} else {
			// If authentication failed, add an error message to the model
			model.addAttribute("message", new Message("Invalid email or password", "danger"));
			
			// Redirect the user back to the admin login page
			return "redirect:/adminLogin";
		}
	}
}
