package com.prms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * The Appointment class represents an appointment made by a patient with a doctor.
 * It contains fields for the appointment date, patient's email, phone number, diseases,
 * doctor ID, patient's address, status of the appointment, and medical description.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
*/

@Entity
@Table(name="appointment")
public class Appointment {
	
	/**
	 * Unique identifier for the appointment.
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",length =45)
	private int id;
	
	/**
	 * User ID of the user who made the appointment.
	*/
	@Column(name="user_id",length =45)
	private int userId;
	
	/**
	 * Full name of the patient who made the appointment.
	*/
	@Column(name="fullname",length =250)
	private String fullName;
	
	/**
	 * Gender of the patient who made the appointment.
	*/
	@Column(name="gender",length =45)
	private String gender;
	
	/**
	 * Age of the patient who made the appointment.
	*/
	@Column(name="age",length =45)
	private String age;
	
	/**
	 * Date and time of the appointment.
	*/
	@Column(name="appointDate",length =45)
	private String appointDate;
	
	/**
	 * Email address of the patient who made the appointment.
	*/
	@Column(name="email",length =250)
	private String email;
	
	/**
	 * Phone number of the patient who made the appointment.
	*/
	@Column(name="phNo",length =50)
	private String phNo;
	
	/**
	 * Pre-existing diseases or conditions of the patient.
	*/
	@Column(name="diseases",length =255)
	private String diseases;
	
	/**
	 * ID of the doctor who the appointment is with.
	*/
	@Column(name="doctorId",length =45)
	private int doctorId;
	
	/**
	 * Address of the patient who made the appointment.
	*/
	@Column(name="address",length =500)
	private String address;
	
	/**
	 * Status of the appointment (e.g. confirmed, canceled).
	*/
	@Column(name="status",length =100)
	private String status;
	
	/**
	 * Medical description or notes related to the appointment.
	*/
	@Column(name="meddiscrip",length =500)
	private String medDisc;

	/**
	 * Doctor name will be saved into this but no column is created in the database
	 */
	@Transient
    private String doctorName;
	/**
	 * Default constructor for Appointment class.
	*/
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor for Appointment class with all parameters.
	 * @param id unique identifier for the appointment
	 * @param userId user ID of the user who made the appointment
	 * @param fullName full name of the patient who made the appointment
	 * @param gender gender of the patient who made the appointment
	 * @param age age of the patient who made the appointment
	 * @param appointDate date and time of the appointment
	 * @param email email address of the patient who made the appointment
	 * @param phNo phone number of the patient who made the appointment
	 * @param diseases pre-existing diseases or conditions of the patient
	 * @param doctorId ID of the doctor who the appointment is with
	 * @param address address of the patient who made the appointment
	 * @param status status of the appointment
	 * @param medDisc medical description or notes related to the appointment
	*/
	public Appointment(int id, int userId, String fullName, String gender, String age, String appointDate, String email,
			String phNo, String diseases, int doctorId, String address, String status, String medDisc) {
		super();
		this.id = id;
		this.userId = userId;
		this.fullName = fullName;
		this.gender = gender;
		this.age = age;
		this.appointDate = appointDate;
		this.email = email;
		this.phNo = phNo;
		this.diseases = diseases;
		this.doctorId = doctorId;
		this.address = address;
		this.status = status;
		this.medDisc = medDisc;
	}

	/**
	 * Constructor for the Appointment class.
	 * 
	 * @param userId The user ID for the user who made this appointment.
	 * @param fullName The full name of the user who made this appointment.
	 * @param gender The gender of the user who made this appointment.
	 * @param age The age of the user who made this appointment.
	 * @param appointDate The date and time of the appointment.
	 * @param email The email address of the user who made this appointment.
	 * @param phNo The phone number of the user who made this appointment.
	 * @param diseases Any relevant medical conditions of the user who made this appointment.
	 * @param doctorId The ID of the doctor the user is seeing for this appointment.
	 * @param address The address of the user who made this appointment.
	 * @param status The status of the appointment.
	 * @param medDisc Any relevant medical information or notes associated with this appointment.
	*/

	public Appointment(int userId, String fullName, String gender, String age, String appointDate, String email,
			String phNo, String diseases, int doctorId, String address, String status, String medDisc) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.gender = gender;
		this.age = age;
		this.appointDate = appointDate;
		this.email = email;
		this.phNo = phNo;
		this.diseases = diseases;
		this.doctorId = doctorId;
		this.address = address;
		this.status = status;
		this.medDisc = medDisc;
	}
	
	/**
	 * Retrieves the unique identifier for the appointment.
	 * @return The ID of the appointment.
	*/
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier for the appointment.
	 * @param id The ID of the appointment.
	*/
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the ID of the user who made the appointment.
	 * @return The ID of the user.
	*/
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets the ID of the user who made the appointment.
	 * @param userId The ID of the user.
	*/
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Retrieves the full name of the user who made the appointment.
	 * @return The full name of the user.
	*/
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name of the user who made the appointment.
	 * @param fullName The full name of the user.
	*/
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Retrieves the gender of the user who made the appointment.
	 * @return The gender of the user.
	*/
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the user who made the appointment.
	 * @param gender The gender of the user.
	*/
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Retrieves the age of the user who made the appointment.
	 * @return The age of the user.
	*/
	public String getAge() {
		return age;
	}

	/**
	 * Sets the age of the user who made the appointment.
	 * @param age The age of the user.
	*/
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * Retrieves the date of the appointment.
	 * @return The date of the appointment.
	*/
	public String getAppointDate() {
		return appointDate;
	}

	/**
	 * Sets the date of the appointment.
	 * @param appointDate the date of the appointment
	*/
	public void setAppointDate(String appointDate) {
		this.appointDate = appointDate;
	}

	/**
	 * Returns the email address of the patient.
	 * @return the email address of the patient
	*/
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the patient.
	 * @param email the email address of the patient
	*/
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the phone number of the patient.
	 * @return the phone number of the patient
	*/
	public String getPhNo() {
		return phNo;
	}

	/**
	 * Sets the phone number of the patient.
	 * @param phNo the phone number of the patient
	*/
	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	/**
	 * Returns the diseases the patient is suffering from.
	 * @return the diseases the patient is suffering from
	*/
	public String getDiseases() {
		return diseases;
	}

	/**
	 * Sets the diseases the patient is suffering from.
	 * @param diseases the diseases the patient is suffering from
	*/
	public void setDiseases(String diseases) {
		this.diseases = diseases;
	}

	/**
	 * Returns the ID of the doctor the appointment is made with.
	 * @return the ID of the doctor the appointment is made with
	*/
	public int getDoctorId() {
		return doctorId;
	}

	/**
	 * Sets the ID of the doctor the appointment is made with.
	 * @param doctorId the ID of the doctor the appointment is made with
	*/
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * Returns the address of the patient.
	 * @return the address of the patient
	*/
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address of the patient.
	 * @param address the address of the patient
	*/
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns the appointment status of the patient.
	 * @return the status of the patient
	*/
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the appointment status of the patient.
	 * @param status the appointment status of the patient
	*/
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Returns the medication discription of the patient.
	 * @return the medDisc medication discription of the patient
	*/
	public String getMedDisc() {
		return medDisc;
	}

	/**
	 * Sets the medication discription of the patient.
	 * @param medDisc medication discription of the patient
	*/
	public void setMedDisc(String medDisc) {
		this.medDisc = medDisc;
	}

	/**
	 * Retrieves the name of the doctor associated with the appointment.
	 * 
	 * @return the name of the doctor
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * Sets the name of the doctor associated with the appointment.
	 * 
	 * @param doctorName the name of the doctor
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	/**
	 * Returns a string representation of the Appointment object.
	 * 
	 * @return a string containing the values of the Appointment object's fields
	 */
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", userId=" + userId + ", fullName=" + fullName + ", gender=" + gender
				+ ", age=" + age + ", appointDate=" + appointDate + ", email=" + email + ", phNo=" + phNo
				+ ", diseases=" + diseases + ", doctorId=" + doctorId + ", address=" + address + ", status=" + status
				+ ", medDisc=" + medDisc + "]";
	}
}
