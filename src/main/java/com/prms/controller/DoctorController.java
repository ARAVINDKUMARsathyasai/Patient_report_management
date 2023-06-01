package com.prms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prms.entity.Appointment;
import com.prms.entity.Doctor;
import com.prms.helper.AdminHelper;
import com.prms.repo.AppointmentRepository;
import com.prms.repo.DoctorRepository;
import com.prms.repo.SpecialistRepository;
import com.prms.repo.UserRepository;
import com.prms.service.AdminLoginService;
import com.prms.service.SpecialistService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.RequiredArgsConstructor;
/**
 * The DoctorController class is a Spring MVC controller responsible for handling requests related to doctors.
 * It provides methods for managing doctor-related operations such as displaying the doctor's dashboard, appointments, and updating appointment details.
 * This class is used to handle interactions with doctors in the system.
 * <p>
 * The DoctorController class contains the following methods:
 * doctorIndex: Displays the doctor's index page with their details.
 * doctorDashboard: Displays the doctor's dashboard with appointment statistics.
 * doctorAllAppointments: Displays all appointments associated with the doctor.
 * todayAppointment: Displays today's appointments for the doctor.
 * resolveApp: Opens the update form for resolving an appointment.
 * updateAppointment: Updates the status and medical description of an appointment.
 * </p>
 * <p>
 * Note: This class assumes the presence of Doctor, Appointment, and AdminHelper classes, as well as repositories for Doctor and Appointment entities.
 * </p>
 * <p>
 * Usage: This class should be annotated with "@Controller" and mapped to the appropriate request path using "@RequestMapping."
 * It requires the DoctorRepository and AppointmentRepository to be injected for accessing doctor and appointment data respectively.
 * </p>
 * <p>
 * Dependencies: This class requires Lombok's RequiredArgsConstructor, PasswordEncoder, and various Spring framework annotations.
 * It also depends on the DoctorRepository and AppointmentRepository interfaces.
 * </p>
 * "@Controller" indicates that this class serves as a Spring MVC controller.
 * "@RequestMapping("/doctor")" specifies the base URL path for doctor-related requests.
 * "@RequiredArgsConstructor" generates a constructor with required arguments for the class's final fields.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see Appointment
 * @see Doctor
 * @see AppointmentRepository
 * @see DoctorRepository
 * @see SpecialistRepository
 * @see UserRepository
 * @see SpecialistService
 */
@Controller
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
	/**
	 * Tight coupling of the DoctorRepository interface and are initialized in constructor
	 * 
	 * @see DoctorRepository
	 */
	private final DoctorRepository docRepo;
	/**
	 * Tight coupling of the AppointmentRepository interface and are initialized in constructor
	 * 
	 * @see AppointmentRepository
	 */
	private final AppointmentRepository appRepo;
	
	/**
	 * Displays the doctor's index page with their details.
	 * 
	 * @param model The Model object used to pass data to the view.
	 * @return The view name for the doctor's index page.
	 */
	@RequestMapping("/index")
	public String doctorIndex(Model model) {
		// Retrieve the doctor details using the id stored in AdminHelper
		Optional<Doctor> doctor = docRepo.findById(AdminHelper.id);
		
		// Print the doctor details for debugging purposes
		System.out.println("Doctor details: " + doctor);
		
		// Add the doctor object to the model
		model.addAttribute("doctor", doctor);
		
		// Return the view name for the doctor index page
		return "doctor/doctorIndex";
	}

	
	/**
	 * Displays the doctor's dashboard with appointment statistics.
	 * 
	 * @param model The Model object used to pass data to the view.
	 * @return The view name for the doctor's dashboard page.
	 */
	@RequestMapping("/dashboard")
	public String doctorDashboard(Model model) {
		// Get the current date
		LocalDate currentDate = LocalDate.now();
		
		// Retrieve the doctor details using the id stored in AdminHelper
		Optional<Doctor> doctor = docRepo.findById(AdminHelper.id);
	    
		// Count the total number of appointments for the doctor
		int totalApp = appRepo.countByDoctorId(AdminHelper.id);
		// Initialize variables to track today's appointments and unsolved appointments
	    int todayApp = 0;
	    int todayUnsovled = 0;
	    
	    // Retrieve all appointments for the doctor
	    List<Appointment> appointments = appRepo.findAllByDoctorId(AdminHelper.id);
	    
	    // Iterate through the appointments
	    for (Appointment appointment : appointments) {
	        // Retrieve the appointment date as a string
	        String appointDateStr = appointment.getAppointDate();
	        
	        if (appointDateStr != null) {
	            // Parse the appointment date string into a LocalDate object
	            LocalDate appointDate = LocalDate.parse(appointDateStr, DateTimeFormatter.ISO_DATE);
	            
	            // Check if the appointment date is equal to the current date
	            if (appointDate.equals(currentDate)) {
	                // Increment today's appointment count
	                todayApp++;
	                
	                // Check if the appointment is unsolved (status is null)
	                if (appointment.getStatus() == null) {
	                    // Increment today's unsolved appointment count
	                    todayUnsovled++;
	                }
	            }
	        }
	    }
	    
	    // Add the doctor object to the model
	    model.addAttribute("doctor", doctor);
	    
	    // Add the total appointment count, today's appointment count, and today's unsolved appointment count to the model
	    model.addAttribute("allTotal", totalApp);
	    model.addAttribute("today", todayApp);
	    model.addAttribute("unsolved", todayUnsovled);
	    
	    // Return the view name for the doctor's dashboard page
	    return "doctor/doctorDashboard";
	}
	
	/**
	 * Displays all appointments associated with the doctor.
	 * 
	 * @param model The Model object used to pass data to the view.
	 * @return The view name for displaying all appointments.
	 */
	@RequestMapping("/allAppointment")
	public String doctorAllAppointments(Model model) {
	    // Retrieve the doctor details using the id stored in AdminHelper
	    Optional<Doctor> doctor = docRepo.findById(AdminHelper.id);
	    
	    // Retrieve all appointments from the repository
	    List<Appointment> appointments = appRepo.findAll();
	    
	    // Iterate through the appointments
	    for (Appointment appointment : appointments) {
	        // Retrieve the doctor's name associated with the appointment
	        Optional<Doctor> doctorNameOptional = docRepo.findById(appointment.getDoctorId());
	        
	        // Check if the doctor name is available
	        if (doctorNameOptional.isPresent()) {
	            // Get the Doctor object from the Optional
	            Doctor doctorName = doctorNameOptional.get();
	            
	            // Set the doctor's full name in the appointment object
	            appointment.setDoctorName(doctorName.getFullName());
	        }
	    }
	    
	    // Add the appointments and doctor object to the model
	    model.addAttribute("appointments", appointments);
	    model.addAttribute("doctor", doctor);
	    
	    // Return the view name for the doctor appointments page
	    return "doctor/Appointments";
	}
	
	/**
	 * Displays today's appointments for the doctor.
	 * 
	 * @param model The Model object used to pass data to the view.
	 * @return The view name for displaying today's appointments.
	 */
	@RequestMapping("/todayAppointment")
	public String todayAppointment(Model model) {
	    // Retrieve the doctor details using the id stored in AdminHelper
	    Optional<Doctor> doctor = docRepo.findById(AdminHelper.id);
	    
	    // Retrieve all appointments from the repository
	    List<Appointment> appointments = appRepo.findAll();
	    
	    // Iterate through the appointments
	    for (Appointment appointment : appointments) {
	        // Retrieve the doctor's name associated with the appointment
	        Optional<Doctor> doctorNameOptional = docRepo.findById(appointment.getDoctorId());
	        
	        // Check if the doctor name is available
	        if (doctorNameOptional.isPresent()) {
	            // Get the Doctor object from the Optional
	            Doctor doctorName = doctorNameOptional.get();
	            
	            // Set the doctor's full name in the appointment object
	            appointment.setDoctorName(doctorName.getFullName());
	        }
	    }
	    
	    // Add the appointments and doctor object to the model
	    model.addAttribute("appointments", appointments);
	    model.addAttribute("doctor", doctor);
	    
	    // Return the view name for the doctor's today appointment page
	    return "doctor/todayAppointment";
	}
	
	/**
	 * Opens the update form for resolving an appointment.
	 * 
	 * @param id    The ID of the appointment to be resolved.
	 * @param model The Model object used to pass data to the view.
	 * @return The view name for the appointment update form.
	 */
	//open update form handler
	@PostMapping("/updateReport/{id}")
	public String resolveApp(@PathVariable("id") Integer id, Model model) {
	    // Retrieve the doctor details using the id stored in AdminHelper
	    Optional<Doctor> doctor = docRepo.findById(AdminHelper.id);
	    
	    // Retrieve the appointment details by id
	    Optional<Appointment> app = appRepo.findById(id);
	    
	    // Add the appointment and doctor objects to the model
	    model.addAttribute("app", app);
	    model.addAttribute("doctor", doctor);
	    
	    // Return the view name for the appointment resolution page
	    return "doctor/Appointment";
	}

	
	/**
	 * Updates the status and medical description of an appointment.
	 * 
	 * @param id      The ID of the appointment to be updated.
	 * @param status  The updated status of the appointment.
	 * @param medDisc The updated medical description of the appointment.
	 * @param model   The Model object used to pass data to the view.
	 * @return The redirect URL for today's appointments page.
	 */
	//update the appointment status
	@PostMapping("/appAppointment")
	public String updateAppointment(@RequestParam("appid") Integer id,
									@RequestParam("ststus") String status,
									@RequestParam("medDisc") String medDisc,
							        Model model) {
		
		// Retrieve the appointment details by id
	    Optional<Appointment> app = appRepo.findById(id);
	    
	    // Create a new Appointment object to store the updated values
	    Appointment update = new Appointment();
	    
	    // Check if the appointment exists
	    if (app.isPresent()) {
	        // Get the existing appointment object from the Optional
	        update = app.get();
	        
	        // Update the appointment properties with the provided values
	        update.setId(id);
	        update.setStatus(status);
	        update.setMedDisc(medDisc);
	    }
	    
	    // Save the updated appointment
	    appRepo.save(update);
	    
	    // Redirect to the todayAppointment page for the doctor
	    return "redirect:/doctor/todayAppointment"; 	
	}
}
