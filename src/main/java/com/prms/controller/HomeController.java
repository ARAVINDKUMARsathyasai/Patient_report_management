package com.prms.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prms.entity.User;
import com.prms.helper.AdminHelper;
import com.prms.helper.Message;
import com.prms.repo.AdminRepository;
import com.prms.repo.AppointmentRepository;
import com.prms.repo.DoctorRepository;
import com.prms.repo.UserRepository;
import com.prms.repo.VerificationTokenRepository;
import com.prms.service.DoctorService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import com.prms.entity.Admin;
import com.prms.entity.Doctor;

/**
 * The HomeController class is a Spring MVC controller responsible for handling various requests related to the home page,
 * user login, admin login, doctor login, user registration, and other related functionalities.
 * It provides methods for handling these requests and interacting with the corresponding services and repositories.
 * This class serves as the main entry point for the application and manages the navigation between different views.
 * The class also utilizes the AdminRepository, DoctorService, and PasswordEncoder components for specific operations.
 * It uses the AdminHelper class to store and retrieve user information during the session.
 * Additionally, it relies on the Message class for displaying error/success messages to the user.
 * This class is responsible for managing the routing and control flow of the application's home and authentication-related pages.
 * It handles the authentication process for admin and doctor users, as well as provides the necessary functionality for user registration.
 * The class also handles requests related to displaying developer information and managing the user's session.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see User
 * @see AdminRepository
 * @see AppointmentRepository
 * @see DoctorRepository
 * @see UserRepository
 * @see VerificationTokenRepository
 * @see DoctorService
*/
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final PasswordEncoder passwordEncoder;
	/**
	 * Tight coupling of the AdminRepository interface and are initialized in constructor
	 * 
	 * @see AdminRepository
	 */
	private final AdminRepository adminRepository;
	/**
	 * Tight coupling of the DoctorService and are initialized in constructor
	 * 
	 * @see DoctorService
	 */
	private final DoctorService doctorService;
	
	/**
	 * Handles the request for the home page.
	 *
	 * @return The logical view name of the home page.
	 */
	@RequestMapping("/")
	public String home() {
		// Return the view name for the homepage
		return "homepage";
	}
	
	/**
	 * Handles the request for the home page.
	 *
	 * @return The logical view name of the home page.
	 */	
	@RequestMapping("/home")
	public String homePage() {
		// Return the view name for the homepage
		return "homepage";
	}
	
	/**
	 * Handles the request for the home page and redirects to the root URL ("/").
	 *
	 * @return The redirect URL to the root URL ("/").
	 */
	@RequestMapping("/homePage")
	public String homePa() {
		// Redirects to the homepage
		return "redirect:/";
	}
	
	/**
	 * Handles the GET request for the admin login page.
	 *
	 * @return The ModelAndView object containing the logical view name of the admin login page and an empty Admin object.
	 */
	@GetMapping("/adminLogin")
	public ModelAndView adminLogin() {
	    // Create a new ModelAndView object with the view name "admin/adminLogin"
	    ModelAndView mav = new ModelAndView("admin/adminLogin");
	    
	    // Add a new "user" object of type Admin to the ModelAndView
	    mav.addObject("user", new Admin());

	    // Return the populated ModelAndView object
	    return mav;
	}
	
	/**
	 * Handles the POST request for the admin login process.
	 *
	 * @param email    The email address provided by the admin during login.
	 * @param password The password provided by the admin during login.
	 * @param model    The Model object used to pass data to the view.
	 * @return If the login is successful, it redirects to the admin index page.
	 *         If the login fails, it redirects back to the admin login page with an error message.
	 */
	@PostMapping("/adminLog")
	public String adminLoginProcess(@RequestParam("username") String email,
									@RequestParam("password") String password, Model model) {
		
		// Create a new Admin object
	    Admin admin = new Admin();
	    admin.setEmail(email);
	    admin.setPassword(password);
	    
	    try {
	        // Check if the email is not null
	        if (!admin.getEmail().equals(null)) {
	            // Check if the provided email and password match the expected values
	            if ((admin.getEmail().equals("kalyankumar@manipal.com") && admin.getPassword().equals("12345678")) ||
	                    (admin.getEmail().equals("aravindkumar@manipal.com") && admin.getPassword().equals("12345678"))) {
	                // Set the username in the AdminHelper based on the logged-in user
	                if (admin.getEmail().equals("kalyankumar@manipal.com") && admin.getPassword().equals("12345678")) {
	                    AdminHelper.userName = "Kalyan kumar";
	                    System.out.println("Logged in username = " + AdminHelper.userName);
	                } else {
	                    AdminHelper.userName = "Aravind kumar";
	                    System.out.println("Logged in username = " + AdminHelper.userName);
	                }
	                
	                // Redirect to the admin index page
	                return "redirect:/admin/index";
	            }
	        }
	    } catch (Exception e) {
	        // Handle any exceptions that occur during the login process
	        System.out.println(e.getMessage());
	        model.addAttribute("message", new Message(e.getMessage(), "danger"));
	        return "redirect:/adminLogin";
	    }
	    
	    // If the login credentials are invalid, redirect to the adminLogin page with an error message
	    System.out.println("Admin details: " + admin);
	    model.addAttribute("message", new Message("Invalid email or password", "danger"));
	    return "redirect:/adminLogin";
	}
	
	/**
	 * Handles the request for the doctor login page.
	 * 
	 * @return The logical view name of the doctor login page.
	*/
	@RequestMapping("/doctorLogin")
	public String doctorLogin() {
		// Return the view name for the doctor login page
		return "doctor/doctorLogin";
	}
	
	/**
	 * Handles the POST request for the doctor login process.
	 * 
	 * @param email The email address provided by the doctor during login.
	 * @param password The password provided by the doctor during login.
	 * @param model The Model object used to pass data to the view.
	 * 
	 * @return If the login is successful, it redirects to the doctor index page.
	 * 		   If the login fails, it redirects back to the doctor login page with an error message.
	 */
	@PostMapping("/docLog")
	public String doctorLoginProcess(@RequestParam("username") String email,
            @RequestParam("password") String password, Model model) {
		// Call the doctorService to find a doctor with the provided email and password
		Doctor doc = doctorService.findDoctorByEmailAndPassword(email, password);
		
		if (doc != null) {
		// Set the doctor's ID in the AdminHelper
		AdminHelper.id = doc.getId();
		
		System.out.println("Doctor ID = " + AdminHelper.id);
		
		// Redirect to the doctor index page
		return "redirect:/doctor/index";
		} else {
		// If the login credentials are invalid, redirect to the doctorLogin page with an error message
		model.addAttribute("message", new Message("Invalid email or password", "danger"));
		return "redirect:/doctorLogin";
	  }
	}

	
	/**
	 * Handles the request for the lab login page.
	 * 
	 * @return The logical view name of the lab login page.
	*/
	@RequestMapping("/LabLogin")
	public String labLogin() {
		// Return the view name for the lab incharge login page
		return "lab/labLogin";
	}
	
	/**
	 * Handles the request for the user login page.
	 * 
	 * @return The logical view name of the user login page.
	*/
	@RequestMapping("/userLogin")
	public String userLogin() {
		// Return the view name for the lab user login page
		return "patient/userLogin";
	}
	
	/**
	 * Handles the request for displaying developer information.
	 * 
	 * @param model The Model object used to pass data to the view.
	 * @return The logical view name of the developer information page.
	*/
	@RequestMapping("/developerInfo")
	public String developerInfo(Model model) {
				
		return "admin/DeveloperInfo";
	}
	
	/**
	 * Handles the request for the admin signup page.
	 * 
	 * @param model The Model object used to pass data to the view.
	 * @param session The HttpSession object used to manage the session attributes.
	 * 
	 * @return The logical view name of the admin signup page.
	*/
	@RequestMapping("/adminSignup")
	public String adminSignup(Model model, HttpSession session) {
		
		session.setAttribute("message", new Message(null,null));
		model.addAttribute("user", new User());
		return "admin/signup";
	}
	
	/**
	 * Handles the request for the user signup page.
	 * 
	 * @param model The Model object used to pass data to the view.
	 * @param session The HttpSession object used to manage the session attributes.
	 * 
	 * @return The logical view name of the user signup page.
	*/
	@RequestMapping("/signup")
	public String signUp(Model model, HttpSession session) {
		//clear previous message if any in the signup page 
		session.setAttribute("message", new Message(null,null));
		model.addAttribute("user", new User());
		return "patient/signup";
	}
	
	/**
	 * Handles the request for the message page.
	 * 
	 * @param model The Model object used to pass data to the view.
	 * @param session The HttpSession object used to manage the session attributes.
	 * 
	 * @return The logical view name of the message page.
	*/
	@RequestMapping("/message")
	public String message(Model model, HttpSession session) {
		
		return "helper/message";
	}
	
	/**
	 * Handles the POST request for admin signup.
	 * 
	 * @param fullname The full name of the admin.
	 * @param email The email address of the admin.
	 * @param password The password of the admin.
	 * @param model The Model object used to pass data to the view.
	 * @param session The HttpSession object used to manage the session attributes.
	 * 
	 * @return The logical view name of the admin login page.
	*/
	@PostMapping("/do_register")
	public String adminSignUP(@RequestParam("fullname") String fullname,
							  @RequestParam("email") String email,
							  @RequestParam("password") String password,
								Model model, HttpSession session) {
		
		Admin admin = new Admin();
		admin.setFullname(fullname);
		admin.setEmail(email);
		admin.setPassword(password);
		admin.setRole("ADMIN");
		
		adminRepository.save(admin);
		
		
		return "admin/adminLogin";
	}
}
