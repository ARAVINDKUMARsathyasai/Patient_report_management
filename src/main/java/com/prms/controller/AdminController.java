package com.prms.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.prms.entity.Specialist;
import com.prms.entity.Transaction;
import com.prms.entity.User;
import com.prms.helper.AdminHelper;
import com.prms.helper.Message;
import com.prms.entity.Admin;
import com.prms.entity.Appointment;
import com.prms.entity.Doctor;
import com.prms.repo.AdminRepository;
import com.prms.repo.AppointmentRepository;
import com.prms.repo.DoctorRepository;
import com.prms.repo.SpecialistRepository;
import com.prms.repo.UserRepository;
import com.prms.service.SpecialistService;
import com.prms.service.TransactionService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 *The AdminController class handles the requests and manages the operations related to the admin functionality in the application.
 *It is responsible for managing the admin dashboard, patient management, doctor management, payment history, appointments, 
 *and other admin-related features.
 *
 * Usage: This class should be annotated with "@Controller" and mapped to the appropriate request path using "@RequestMapping".
 * "@Controller" indicates that this class serves as a Spring MVC controller.
 * "@RequestMapping("/doctor")" specifies the base URL path for doctor-related requests.
 * "@RequiredArgsConstructor" generates a constructor with required arguments for the class's final fields.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see Specialist
 * @see Transaction
 * @see User
 * @see Appointment
 * @see Doctor
 * @see Admin
 * @see AdminRepository
 * @see AppointmentRepository
 * @see DoctorRepository
 * @see SpecialistRepository
 * @see UserRepository
 * @see TransactionService
*/
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	/**
	 * The UserService class provides operations related to user management.
	 * It interacts with the UserRepository to perform CRUD operations on users.
	 * It also uses other repositories and services for additional functionality.
	 */
	private final UserRepository userRepository;
	/**
	 * The AppointmentRepository interface provides access to appointment data in the database.
	 * It allows retrieval and manipulation of appointment entities.
	 */
	private final AppointmentRepository appointmentRepository;
	/**
	 * The DoctorRepository interface provides access to doctor data in the database.
	 * It allows retrieval and manipulation of doctor entities.
	 */
	private final DoctorRepository doctorRepository;
	/**
	 * The SpecialistRepository interface provides access to specialist data in the database.
	 * It allows retrieval and manipulation of specialist entities.
	 */
	private final SpecialistRepository specialistRepository;
	/**
	 * The SpecialistService class provides operations related to specialist management.
	 * It uses the SpecialistRepository to retrieve specialist information.
	 */
	private final SpecialistService specialistService;
	/**
	 * The PasswordEncoder interface provides password encryption and validation functionality.
	 * It is used to encode and compare passwords for user authentication.
	 */
	private final PasswordEncoder passwordEncoder;
	/**
	 * The TransactionService class provides operations related to transaction management.
	 * It interacts with the TransactionRepository to perform CRUD operations on transactions.
	 */
	private final TransactionService transactionService;
	/**
	 * This is will store the appointment id for the update process
	 */
	private Integer id;
	
	
	/**
	 * Handles the request to display the admin index page.
	 * 
	 * @param model The model to be used for rendering the view.
	 * @return The view name for the admin index pege.
	*/	
	@RequestMapping("/index")
	public String DashBoard(Model model) {
		// Add the username to the model attribute
		model.addAttribute("user",AdminHelper.userName);		
		
		// Return the view name for the admin index page
		return "admin/AdminIndex";
	}
	
	/**
	 * Handles the request to display the admin dashboard view.
	 * 
	 * @param model The model to be used for rendering the view.
	 * @return The view name for the admin dashboard view.
	*/
	@RequestMapping("/dashboard")
	public String dashBoardView(Model model) {
		// Add the username to the model attribute
		model.addAttribute("user",AdminHelper.userName);	
		// Retrieve the counts from the respective repositories
		long userCount = userRepository.count();
		long appointCount = appointmentRepository.count();
		long docCount = doctorRepository.count();
		long splCount = specialistRepository.count();
		
		// Add the counts to the model attributes
		model.addAttribute("usercount", userCount);
		model.addAttribute("appCount", appointCount);
		model.addAttribute("docCount",docCount);
		model.addAttribute("splCount", splCount);
		
		// Return the view name for the admin dashboard
		return "admin/AdminDashboard";
	}
	
	/**
	 * Handles the request to display the patients record page.
	 * 
	 * @param model The model to be used for rendering the view.
	 * @return The view name for the patient page.
	*/
	@RequestMapping("/patient")
	public String patientPage(Model model) {
		// Add the username to the model attribute
		model.addAttribute("user",AdminHelper.userName);	
		
		// Retrieve a list of all users from the repository
		List<User> users =userRepository.findAll();
		
		// Add the list of users to the model attribute
		model.addAttribute("users", users);
		
		// Return the view name for the patient page
		return "admin/Patients";
	}
	
	
	/**
	 * Handles the request to display the doctors records page.
	 * 
	 * @param model The model to be used for rendering the view.
	 * @return The view name for the doctors page.
	*/
	@RequestMapping("/doctor")
	public String doctorsPage(Model model) {
	    // Add an empty Doctor object to the model attribute
	    model.addAttribute("doctor", new Doctor());

	    // Add the username to the model attribute
	    model.addAttribute("user", AdminHelper.userName);

	    // Retrieve a list of all specialists from the specialist service
	    model.addAttribute("specialists", specialistService.getAllSpecialists());

	    // Retrieve a list of all doctors from the doctor repository
	    List<Doctor> doctors = doctorRepository.findAll();

	    // Add the list of doctors to the model attribute
	    model.addAttribute("doctors", doctors);

	    // Return the view name for the doctors page
	    return "admin/Doctors";
	}
	
	/**
	 * Handles the request to display the payments history page.
	 * 
	 * @param model The model to be used for rendering the view.
	 * @return The view name for the payment history page.
	*/
	@RequestMapping("/payment")
	public String payments(Model model) {
		// Add an empty Doctor object to the model attribute
        model.addAttribute("doctor",new Doctor());
        // Add the username to the model attribute
        model.addAttribute("user",AdminHelper.userName);	
		
      // Retrieve a list of all transactions from the transaction service
		 List<Transaction> transactions = transactionService.getAllTransactions();
		// Update the fullname of the user associated with each transaction
		 for (Transaction transaction : transactions) {
			 // Retrieve the user associated with the transaction by email
			    Optional<User> optionalUser = userRepository.findByEmail(transaction.getUser().getEmail());
			    if (optionalUser.isPresent()) {
			    	// If the user exists, update the fullname in the transaction
			        User user = optionalUser.get();
			        String userName = user.getFullname();
			        transaction.getUser().setFullname(userName);
			    }
			}

		// Add the list of transactions to the model attribute
		 model.addAttribute("transactions", transactions);
		
		// Return the view name for the payment history page
		return "admin/PaymentHistory";
	}
	
	/**
	 * Handles the request to display the appointments page.
	 * 
	 * @param model The model to be used for rendering the view.
	 * @return The view name for the appointments page.
	*/
	@RequestMapping("/appointment")
	public String appointmentPage(Model model) {
	    // Add an empty Doctor object to the model attribute
	    model.addAttribute("doctor", new Doctor());

	    // Add the username to the model attribute
	    model.addAttribute("user", AdminHelper.userName);

	    // Retrieve a list of all doctors from the doctor repository
	    List<Doctor> doctors = doctorRepository.findAll();
	    model.addAttribute("doctors", doctors);

	    // Retrieve a list of all appointments from the appointment repository
	    List<Appointment> appointments = appointmentRepository.findAll();

	    // Update the doctor name for each appointment
	    for (Appointment appointment : appointments) {
	        // Retrieve the doctor associated with the appointment by ID
	        Optional<Doctor> doctorNameOptional = doctorRepository.findById(appointment.getDoctorId());
	        if (doctorNameOptional.isPresent()) {
	            // If the doctor exists, update the doctor name in the appointment
	            Doctor doctorNam = doctorNameOptional.get();
	            appointment.setDoctorName(doctorNam.getFullName());
	        }
	    }

	    // Add the list of appointments to the model attribute
	    model.addAttribute("appointments", appointments);

	    // Return the view name for the appointments page
	    return "admin/Appointments";
	}
	
	/**
	 * Handles the request to add a new doctor.
	 * 
	 * @param fullName The full name of the doctor.
	 * @param dob The date of birth of the doctor.
	 * @param email The email of the doctor.
	 * @param mobNo The mobile number of the doctor.
	 * @param qualification The qualification of the doctor.
	 * @param specialist The specialist of the doctor.
	 * @param password The password of the doctor.
	 * @param imageUrl The image URL of the doctor.
	 * @param model The model to be used for rendering the view.
	 * @param session The HttpSession object.
	 * 
	 * @return The view name for the doctors page.
	*/
	@PostMapping("/addDoctor")
	public String addDoctor(
			@RequestParam("fullName") String fullName,
			@RequestParam("dob") String dob,
			@RequestParam("email") String email,
			@RequestParam("mobNo") String mobNo,
			@RequestParam("qualification") String qualification,
			@RequestParam("specialist") String specialist,
			@RequestParam("password") String password,
			@RequestParam MultipartFile imageUrl ,Model model, HttpSession session) { 
		try {
			 // Create a new Doctor object and populate its attributes
			Doctor doctor = new Doctor();
			doctor.setFullName(fullName);
			doctor.setDob(dob);
			doctor.setEmail(email);
			doctor.setMobNo(mobNo);
			doctor.setQualification(qualification);
			doctor.setSpecialist(specialist);
			doctor.setPassword(password);
			doctor.setImageUrl(imageUrl.getOriginalFilename());
			
			// Save the doctor object in the doctor repository
			Doctor doc=this.doctorRepository.save(doctor);
			if(doc!=null) {
				try {
					// Retrieve the file path to save the uploaded image
					File saveFile = new ClassPathResource("static/img").getFile();
					Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+imageUrl.getOriginalFilename());
					System.out.println(path);
					// Copy the uploaded image to the specified path
					Files.copy(imageUrl.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			System.out.println(doctor);
		}catch(Exception e) {
			System.out.println("ERROR "+e.getMessage());
			e.printStackTrace();
		}
		// Set a success message in the session attribute
		session.setAttribute("msg","Image Upload Sucessfully");
		
		// Redirect to the doctors page
		return "redirect:/admin/doctor" ;
	}
	
	/**
	 * Handles the request to add a new specialist.
	 * 
	 * @param specName The name of the specialist.
	 * @param model The model to be used for rendering the view.
	 * 
	 * @return The view name for the admin dashboard.
	*/
	@PostMapping("/add")
	public String addSpecialist(@RequestParam("specName") String specName, Model model) {
	    try {
	        // Create a new Specialist object and set its attributes
	        Specialist specialist = new Specialist();
	        specName = specName.toLowerCase();
	        specialist.setSpecialistname(specName);
	        
	        // Save the specialist in the specialist repository
	        specialistRepository.save(specialist);
	        
	        // Add a success message to the model attribute
	        model.addAttribute("successMessage", "Specialist added successfully.");
	    } catch (Exception e) {
	        // Add an error message to the model attribute if an exception occurs
	        model.addAttribute("errorMessage", "Failed to add specialist.");
	    }
	    
	    // Redirect to the admin dashboard page
	    return "redirect:/admin/dashboard";
	}
	
	/**
	 * Handles the request to show the details of a specific doctor.
	 * 
	 * @param id The ID of the doctor.
	 * @param model The model to be used for rendering the view.
	 * 
	 * @return The view name for the doctor details page.
	*/
	// Showing particular doctor details
	@RequestMapping("/{id}/doctor")
	public String showDocDetails(@PathVariable("id") Integer id, Model model) {
	    // String userName=principal.getName();
	    model.addAttribute("doctor", new Doctor());
	    // Optional<User> user = userRepository.findByEmail(userName);
	    model.addAttribute("user", AdminHelper.userName);
	    
	    // Retrieve the doctor details from the doctor repository using the provided id
	    Optional<Doctor> doctorOptional = this.doctorRepository.findById(id);
	    Doctor doctor = doctorOptional.get();
	    
	    // Retrieve all appointments from the appointment repository
	    List<Appointment> appointments = appointmentRepository.findAll();
	    
	    // Update the appointment's doctor name based on the doctor's id
	    for (Appointment appointment : appointments) {
	        Optional<Doctor> doctorNameOptional = doctorRepository.findById(appointment.getDoctorId());
	        if (doctorNameOptional.isPresent()) {
	            Doctor doctorNam = doctorNameOptional.get();
	            appointment.setDoctorName(doctorNam.getFullName());
	        }
	    }
	    
	    // Add the appointments and doctor id to the model attributes
	    model.addAttribute("appointments", appointments);
	    model.addAttribute("id", id);
	    
	    // Add the retrieved doctor to the model attribute
	    model.addAttribute("doctor", doctor);
	    
	    System.out.println("DID " + id);
	    
	    // Return the view for displaying doctor details
	    return "admin/doctor_details";
	}
	
	/**
	 * 
	 * 	Handles the request to delete a doctor.
	 * 
	 * @param id The ID of the doctor.
	 * @param model The model to be used for rendering the view.
	 * @param session The HttpSession object.
	 * 
	 * @return The redirect URL for the doctors page.
	*/
	//delete the doctor handler
	@GetMapping("/delete/{id}")
	public String deleteDoctor(@PathVariable("id") Integer id, Model model, HttpSession session) {
	    // Find the doctor with the specified ID
	    Optional<Doctor> doctorOptional = this.doctorRepository.findById(id);
	    
	    // Get the Doctor object from the Optional
	    Doctor doctor = doctorOptional.get();
	    
	    // Delete the doctor from the repository
	    this.doctorRepository.delete(doctor);
	    
	    // Set a success message in the session
	    session.setAttribute("message", new Message("Details deleted successfully....", "success"));
	    
	    // Redirect to the admin doctor page
	    return "redirect:/admin/doctor";
	}

	/**
	 * Handles the request to open the update form for a doctor.
	 * 
	 * @param id The ID of the doctor.
	 * @param model The model to be used for rendering the view.
	 * 
	 * @return The view name for the update form.
	*/
	//open update form handler
	@PostMapping("/update-doctor/{id}")
	public String doctorUpdateForm(@PathVariable("id") Integer id, Model model) {
	    // Add a new Doctor object to the model
	    model.addAttribute("doctor", new Doctor());
	    
	    // Add the username of the admin to the model
	    model.addAttribute("user", AdminHelper.userName);
	    
	    // Store the provided ID in the class variable 'id'
	    this.id = id;
	    
	    // Find the doctor with the specified ID
	    Optional<Doctor> doctorOptional = this.doctorRepository.findById(id);
	    
	    // Get the Doctor object from the Optional
	    Doctor doctor = doctorOptional.get();
	    
	    // Add the doctor object to the model
	    model.addAttribute("doctor", doctor);
	    
	    // Add the list of specialists to the model
	    model.addAttribute("specialists", specialistService.getAllSpecialists());
	    
	    // Return the view name for the update form
	    return "admin/update_form";
	}
	
	/**
	 * 	Handles the request to update the details of a doctor.
	 * 
	 * @param fullName The full name of the doctor.
	 * @param dob The date of birth of the doctor.
	 * @param email The email of the doctor.
	 * @param mobNo The mobile number of the doctor.
	 * @param qualification The qualification of the doctor.
	 * @param specialist The specialist of the doctor.
	 * @param password The password of the doctor.
	 * @param imageUrl The image URL of the doctor.
	 * @param model The model to be used for rendering the view.
	 * @param session The HttpSession object.
	 * 
	 * @return The redirect URL for the doctor details page.
	*/
	//Update contact handler 
	@PostMapping("/process-update")
	public String updateDoctorHandler(
	        @RequestParam("fullName") String fullName,
	        @RequestParam("dob") String dob,
	        @RequestParam("email") String email,
	        @RequestParam("mobNo") String mobNo,
	        @RequestParam("qualification") String qualification,
	        @RequestParam("specialist") String specialist,
	        @RequestParam("password") String password,
	        @RequestParam MultipartFile imageUrl,
	        Model model, HttpSession session) {
	    
	    // Create a new Doctor object
	    Doctor doctor = new Doctor();
	    
	    // Find the doctor with the specified ID
	    Optional<Doctor> doctorOptional = this.doctorRepository.findById(id);
	    
	    // Get the Doctor object from the Optional
	    Doctor doc = doctorOptional.get();
	    
	    try {
	        if (imageUrl.isEmpty()) {
	            // Do nothing, fetch back the same data and retain it
	            doctor.setImageUrl(doc.getImageUrl());
	            System.out.println(doc.getImageUrl());
	        } else {
	            // Delete the old photo
	            File deleteFile = new ClassPathResource("static/img").getFile();
	            File fileDel = new File(deleteFile, doc.getImageUrl());
	            fileDel.delete();
	            
	            // Rewrite the previous data
	            doctor.setImageUrl(imageUrl.getOriginalFilename());
	            try {
	                File saveFile = new ClassPathResource("static/img").getFile();
	                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + imageUrl.getOriginalFilename());
	                System.out.println(path);
	                Files.copy(imageUrl.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    // Set the properties of the Doctor object
	    doctor.setId(this.id);
	    doctor.setFullName(fullName);
	    doctor.setDob(dob);
	    doctor.setEmail(email);
	    doctor.setMobNo(mobNo);
	    doctor.setQualification(qualification);
	    doctor.setSpecialist(specialist);
	    doctor.setPassword(password);
	    
	    // Save the updated doctor details in the repository
	    this.doctorRepository.save(doctor);
	    
	    // Set a success message in the session
	    session.setAttribute("message", new Message("Details updated....", "success"));
	    
	    // Redirect to the doctor's admin page
	    return "redirect:/admin/" + doctor.getId() + "/doctor";
	}
}
