package com.prms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prms.entity.Doctor;
import com.prms.entity.Transaction;
import com.prms.entity.User;
import com.prms.helper.Message;
import com.prms.repo.AppointmentRepository;
import com.prms.repo.DoctorRepository;
import com.prms.repo.TransactionRepository;
import com.prms.repo.UserRepository;
import com.prms.service.DoctorService;
import com.prms.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.prms.entity.Appointment;
import com.razorpay.*;
import org.json.JSONObject;

/**
 * The UserController class is a Spring MVC controller that handles requests related to user operations.
 * It provides methods for user registration, login, appointment booking, payment processing, and other user-related functionalities.
 * This class manages the interaction between the user interface and the backend services.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see Doctor
 * @see Transaction
 * @see User
 * @see Message
 * @see AppointmentRepository
 * @see DoctorRepository
 * @see TransactionRepository
 * @see UserRepository
 * @see DoctorService
 * @see UserService
*/
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	/**
	 * Tight coupling of the UserService and are initialized in constructor
	 * 
	 * @see UserService
	 */	
	private final UserService userService;
	/**
	 * Tight coupling of the UserService and are initialized in constructor
	 * 
	 * @see UserService
	 */	
	private final AppointmentRepository appointmentRepository;
	/**
	 * Tight coupling of the UserService and are initialized in constructor
	 * 
	 * @see UserService
	 */	
	private final UserRepository userRepository;
	/**
	 * Tight coupling of the UserService and are initialized in constructor
	 * 
	 * @see UserService
	 */	
	private final DoctorService doctorService;
	/**
	 * Tight coupling of the UserService and are initialized in constructor
	 * 
	 * @see UserService
	 */	
	private final AppointmentRepository appRepo;
	/**
	 * Tight coupling of the UserService and are initialized in constructor
	 * 
	 * @see UserService
	 */	
	private final DoctorRepository doctorRepository;
	/**
	 * Tight coupling of the UserService and are initialized in constructor
	 * 
	 * @see UserService
	 */	
	private final TransactionRepository transRepo;
	/**
	 * This is to store the id of the updating appointment
	 */	
	private int appId;
	
	/**
	 * Retrieves the list of all users.
	 *
	 * @return List of User objects representing all users in the system.
	 */
	@GetMapping
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	/**
	 * Displays the user index page.
	 *
	 * @param model     The Model object used to pass data to the view.
	 * @param principal The Principal object representing the currently authenticated user.
	 * @return The view name for the user home page.
	 */
	@RequestMapping("/index")
	public String userDashBoard(Model model,Principal principal) {
		String userName=principal.getName();
		System.out.println("User name ="+userName);
		//get the user using the user email 
		Optional<User> user = userRepository.findByEmail(userName);
		System.out.println("User = "+user);
		model.addAttribute("user",user);
				
		return "patient/patientIndex";
	}
	
	/**
	 * Displays the appointment booking page for the user.
	 *
	 * @param model     The Model object used to pass data to the view.
	 * @param principal The Principal object representing the currently authenticated user.
	 * @return The view name for the appointment booking page.
	 */
	@RequestMapping("/appointment")
	public String patientAppointment(Model model, Principal principal) {
	    // Get the currently logged in user's name
	    String userName = principal.getName();
	    System.out.println("User name = " + userName);
	    
	    // Get the user using the user email
	    Optional<User> user = userRepository.findByEmail(userName);
	    System.out.println("User = " + user);
	    
	    // Add the user to the model
	    model.addAttribute("user", user);
	    
	    // Get all doctors
	    List<Doctor> doctors = doctorService.getAllDoctors();
	    model.addAttribute("doctors", doctors);
	    
	    // Create a new Appointment object (assuming it will be used later)
	    new Appointment();
	    
	    return "patient/Appointment";
	}

	
	/**
	 * Processes the appointment booking request.
	 *
	 * @param model     The Model object used to pass data to the view.
	 * @param userid    The ID of the user making the appointment.
	 * @param fullname  The full name of the user.
	 * @param gender    The gender of the user.
	 * @param age       The age of the user.
	 * @param date      The date of the appointment.
	 * @param email     The email address of the user.
	 * @param phoneNo   The phone number of the user.
	 * @param diseases  The diseases or symptoms provided by the user.
	 * @param doctorName The name of the selected doctor.
	 * @param address   The address of the user.
	 * @param session   The HttpSession object used to store session attributes.
	 * @return The view name for the appointment booking page.
	 */
	@PostMapping("/appAppointment")
	public String createAppointmant(Model model,
									@RequestParam("userid") Integer userid,
									@RequestParam("fullname") String fullname,
									@RequestParam("selGender") String gender,
									@RequestParam("age") String age,
									@RequestParam("appoint_date")String date,
									@RequestParam("email") String email,
									@RequestParam("phno") String phoneNo,
									@RequestParam("diseases") String diseases,
									@RequestParam("doctorName") String doctorName,
									@RequestParam("address") String address,
									HttpSession session) {
		
		String data[] = doctorName.split(" ");
		
		Appointment app = new Appointment();
		try {
			app.setUserId(userid);
			app.setFullName(fullname);
			app.setGender(gender);
			app.setAge(age);
			app.setAppointDate(date);
			app.setEmail(email);
			app.setPhNo(phoneNo);
			app.setDiseases(diseases);
			app.setDoctorId(Integer.parseInt(data[0]));
			app.setAddress(address);
		
			appRepo.save(app);
			
			model.addAttribute("appointment",new Appointment());
			session.setAttribute("message", new Message("Success! Please, follow up ","alert-success"));
			return "redirect:/user/showAppointment";
		}catch(Exception e) {
			session.setAttribute("message", new Message(e.getMessage(),"alert-danger"));
			return "redirect:/user/appointment";
		}
	}
	
	/**
	 * Displays the page showing all appointments of the user.
	 *
	 * @param model     The Model object used to pass data to the view.
	 * @param principal The Principal object representing the currently authenticated user.
	 * @return The view name for the appointment listing page.
	 */
	@RequestMapping("/showAppointment")
	public String appointmentPage(Model model,Principal principal) {
        String userName=principal.getName();
        model.addAttribute("doctor",new Doctor());
		Optional<User> user = userRepository.findByEmail(userName);
		model.addAttribute("user",user);	
		
		 List<Doctor> doctors =doctorRepository.findAll();
		 model.addAttribute("doctors", doctors);
		 
		 List<Appointment> appointments = appointmentRepository.findAll();
		 
		 for (Appointment appointment : appointments) {
				Optional<Doctor> doctorNameOptional = doctorRepository.findById(appointment.getDoctorId());
				if (doctorNameOptional.isPresent()) {
				    Doctor doctorNam = doctorNameOptional.get();
				    appointment.setDoctorName(doctorNam.getFullName());
				}
			}
		 model.addAttribute("appointments", appointments);
		 
		return "patient/ShowAppointments";
	}
	
	/**
	 * Displays the details of a particular appointment.
	 *
	 * @param id        The ID of the appointment.
	 * @param model     The Model object used to pass data to the view.
	 * @param principal The Principal object representing the currently authenticated user.
	 * @return The view name for the appointment details page.
	 */	
	@RequestMapping("/{id}/appointment")
	public String appointDetails(@PathVariable("id") Integer id, Model model, Principal principal) {
		String userName=principal.getName();
        model.addAttribute("doctor",new Doctor());
		Optional<User> user = userRepository.findByEmail(userName);
		model.addAttribute("user",user);
		
		Optional<Appointment> appp = appointmentRepository.findById(id);
		
		Appointment app = appp.get();
		
		Optional<Doctor> doc = doctorRepository.findById(app.getDoctorId());
		Doctor optionDoc = doc.get();
		
		String docNmae = optionDoc.getFullName();
		String spcl = optionDoc.getSpecialist();
		
		model.addAttribute("app", app);
		model.addAttribute("docName", docNmae);
		model.addAttribute("spcl", spcl);
		
		return "patient/ShowAppointmentData";
	}
	
	/**
	 * Creates a new order for the payment using Razorpay.
	 *
	 * @param data      The request body containing the payment details.
	 * @param principal The Principal object representing the currently authenticated user.
	 * @return The JSON response containing the order details.
	 * @throws Exception If an error occurs during the order creation process.
	 */	
	@PostMapping("/create_order")
	@ResponseBody
	public String createOrder(@RequestBody Map<String, Object> data, Principal principal) throws Exception {
		String userName=principal.getName();
		Optional<User> user = userRepository.findByEmail(userName);
		User logUser = user.get();
		int amt = Integer.parseInt(data.get("amount").toString());
		String aa = data.get("amount").toString();
		var client = new RazorpayClient("rzp_test_dZ1Un0Fc0tNMv2","OkScRcRsRn839uuFqBFo3iWM");
		JSONObject ob = new JSONObject();
		ob.put("amount", amt*100);
		ob.put("currency","INR");
		ob.put("receipt","txn_235425");
		
		//create new order.....
		Order order = client.orders.create(ob);
		
		//Save the transaction is database
		
		Transaction transaction = new Transaction();
		transaction.setAmount(aa);
		transaction.setOrderId(order.get("id"));
		transaction.setPaymentId(null);
		transaction.setStatus("created");
		transaction.setUser(logUser);
		transaction.setReceipt(order.get("receipt"));
		
		this.transRepo.save(transaction);
		
		System.out.println("Hey this is the order function "+data);
		System.out.println("Hey this is the order function "+order);
		
		return order.toString();
	}	
	
	/**
	 * Updates the transaction details after successful payment.
	 *
	 * @param data The request body containing the updated transaction details.
	 * @return The ResponseEntity containing the response message.
	 */	
	@PostMapping("/update_order")
	public ResponseEntity<?> updateTransaction(@RequestBody Map<String, Object> data){
	   
		Transaction trans = this.transRepo.findByOrderId(data.get("order_id").toString());
		trans.setPaymentId(data.get("payment_id").toString());
		trans.setStatus(data.get("status").toString());
		
		this.transRepo.save(trans);
		return ResponseEntity.ok(Map.of("msg","updated"));	
	}
	
	/**
	 * Deletes an appointment.
	 *
	 * @param id    The ID of the appointment to be deleted.
	 * @param model The Model object used to pass data to the view.
	 * @return The view name for the appointment listing page.
	 */
	@GetMapping("/appDelete/{id}")
	public String deleteAppointment(@PathVariable("id") Integer id, Model model) {
		
		Optional<Appointment> appontment = this.appointmentRepository.findById(id);
		Appointment app = new Appointment();
		if(appontment.isPresent())
		    app = appontment.get();
		
		this.appointmentRepository.delete(app);
		
		
		return "redirect:/user/showAppointment";
	}
	
	/**
	 * Updates an appointment with the given ID.
	 *
	 * @param id      The ID of the appointment to be updated.
	 * @param model   The Model object used to pass data to the view.
	 * @param principal The Principal object representing the currently authenticated user.
	 * @return The view name for updating the appointment.
	 */
	@PostMapping("/update-app/{id}")
	public String updateAppointment(@PathVariable("id")Integer id, Model model,Principal principal) {
		String userName=principal.getName();
		System.out.println("User name ="+userName);
		//get the user using the user email 
		Optional<User> user = userRepository.findByEmail(userName);
		System.out.println("User = "+user);
		model.addAttribute("user",user);
		
		List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        
        Optional<Appointment> appointment = this.appointmentRepository.findById(id);
        Appointment app = new Appointment();
        if(appointment.isPresent())
        	app = appointment.get();
        
        appId=app.getId();
        System.out.println("Appointment details "+app);
        model.addAttribute("app", app);
		
		return "patient/UpdateAppointment";
	}
	
	/**
	 * Updates the appointment details based on the submitted form data.
	 *
	 * @param model       The Model object used to pass data to the view.
	 * @param userid      The ID of the user associated with the appointment.
	 * @param fullname    The full name of the patient.
	 * @param gender      The gender of the patient.
	 * @param age         The age of the patient.
	 * @param date        The appointment date.
	 * @param email       The email address of the patient.
	 * @param phoneNo     The phone number of the patient.
	 * @param diseases    The diseases or symptoms reported by the patient.
	 * @param doctorName  The name of the doctor for the appointment.
	 * @param address     The address of the patient.
	 * @param session     The HttpSession object used to store session attributes.
	 * @return The view name for the updated appointment or a redirect to the appointment listing page.
	 */
	@PostMapping("/updateAppointment")
	public String updateAppointmant(Model model,
									@RequestParam("userid") Integer userid,
									@RequestParam("fullname") String fullname,
									@RequestParam("selGender") String gender,
									@RequestParam("age") String age,
									@RequestParam("appoint_date")String date,
									@RequestParam("email") String email,
									@RequestParam("phno") String phoneNo,
									@RequestParam("diseases") String diseases,
									@RequestParam("doctorName") String doctorName,
									@RequestParam("address") String address,
									HttpSession session) {
		
		String data[] = doctorName.split(" ");
		
		Appointment app = new Appointment();
		try {
			app.setId(appId);
			app.setUserId(userid);
			app.setFullName(fullname);
			app.setGender(gender);
			app.setAge(age);
			app.setAppointDate(date);
			app.setEmail(email);
			app.setPhNo(phoneNo);
			app.setDiseases(diseases);
			app.setDoctorId(Integer.parseInt(data[0]));
			app.setAddress(address);
		
			appRepo.save(app);
			
			model.addAttribute("appointment",new Appointment());
			session.setAttribute("message", new Message("Success! Updated Sucessfully ","alert-success"));
			return "redirect:/user/showAppointment";
		}catch(Exception e) {
			session.setAttribute("message", new Message(e.getMessage(),"alert-danger"));
			return "redirect:/user/showAppointment";
		}
	}
}
