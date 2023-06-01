package com.prms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * Represents a Doctor entity in the PRM System.
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
*/

@Entity
@Table(name="doctor")
public class Doctor {
	
	/**
	 * The unique identifier of the doctor.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",length =45)
	private int id;
	
	/**
	 * The full name of the doctor.
	 */
	@Column(name="fullname",length =50)
	private String fullName;
	
	/**
	 * The date of birth of the doctor.
	 */
	@Column(name="dob",length =45)
	private String dob;
	
	/**
	 * The qualification of the doctor.
	 */
	@Column(name="qualification",length =50)
	private String qualification;
	
	/**
	 * The specialist field of the doctor.
	 */
	@Column(name="specialist",length =250)
	private String specialist;
	
	/**
	 * The email of the doctor.
	 */
	@Column(name="email",length=250,unique=true)
	private String email;
	
	/**
	 * The mobile number of the doctor.
	 */
	@Column(name="mobNo",length=250,unique=true)
	private String mobNo;
	
	/**
	 * The password of the doctor.
	 */
	@Column(name="password",length=250)
	private String password;
	
	/**
	 * The URL of the doctor's profile picture.
	*/
	@Column(name="image",length =255)
	private String imageUrl;
			
	/**
	 * Constructs a new Doctor instance with the specified details.
	 * @param fullName The full name of the doctor.
	 * @param dob The date of birth of the doctor.
	 * @param qualification The qualification of the doctor.
	 * @param specialist The specialist field of the doctor.
	 * @param email The email of the doctor.
	 * @param mobNo The mobile number of the doctor.
	 * @param password The password of the doctor.
	 * @param imageUrl to store imageUrl of the doctor
	 */
	public Doctor(String fullName, String dob, String qualification, String specialist, String email, String mobNo,
			String password, String imageUrl) {
		super();
		this.fullName = fullName;
		this.dob = dob;
		this.qualification = qualification;
		this.specialist = specialist;
		this.email = email;
		this.mobNo = mobNo;
		this.password = password;
		this.imageUrl = imageUrl;
	}
	
	/**
	 * Constructs a new Doctor instance with the specified ID and details.
	 * @param id The ID of the doctor.
	 * @param fullName The full name of the doctor.
	 * @param dob The date of birth of the doctor.
	 * @param qualification The qualification of the doctor.
	 * @param specialist The specialist field of the doctor.
	 * @param email The email of the doctor.
	 * @param mobNo The mobile number of the doctor.
	 * @param password The password of the doctor.
	 * @param imageUrl to store imageUrl of the doctor.
	 */
	public Doctor(int id, String fullName, String dob, String qualification, String specialist, String email,
			String mobNo, String password,String imageUrl) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.dob = dob;
		this.qualification = qualification;
		this.specialist = specialist;
		this.email = email;
		this.mobNo = mobNo;
		this.password = password;
		this.imageUrl = imageUrl;
	}
	
	/**
	 * Constructs a new empty Doctor instance.
	 */
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets the ID of the doctor.
	 * @return The ID of the doctor.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the ID of the doctor.
	 * @param id The ID of the doctor.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the full name of the doctor.
	 * @return The full name of the doctor.
	 */
	public String getFullName() {
		return fullName;
	}
	
	/**
	 * Sets the full name of the doctor.
	 * @param fullName The full name of the doctor.
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	/**
	 * Gets the date of birth of the doctor.
	 * @return The date of birth of the doctor.
	 */
	public String getDob() {
		return dob;
	}
	
	/**
	 * Sets the date of birth of the doctor.
	 * @param dob The date of birth of the doctor.
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	/**
	 * Returns the qualification of the doctor.
	 * @return The qualification of the doctor.
	 */
	public String getQualification() {
		return qualification;
	}
	
	/**
	 * Sets the qualification of the doctor.
	 * @param qualification The qualification of the doctor.
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	/**
	 * Returns the specialist of the doctor.
	 * @return The specialist of the doctor.
	 */
	public String getSpecialist() {
		return specialist;
	}
	
	/**
	 * Sets the specialist of the doctor.
	 * @param specialist The specialist of the doctor.
	 */
	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}
	
	/**
	 * Returns the email of the doctor.
	 * @return The email of the doctor.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email of the doctor.
	 * @param email The email of the doctor.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Returns the mobile number of the doctor.
	 * @return The mobile number of the doctor.
	 */
	public String getMobNo() {
		return mobNo;
	}
	
	/**
	 * Sets the mobile number of the doctor.
	 * @param mobNo The mobile number of the doctor.
	 */
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	
	/**
	 * Returns the image URL of the Doctor.
	 * @return The image URL of the Doctor.
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Sets the image URL of the Doctor.
	 * @param imageUrl The image URL of the Doctor.
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	/**
	 * Returns the password of the doctor.
	 * @return The password of the doctor.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password of the doctor.
	 * @param password The password of the doctor.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns a string representation of the Doctor object.
	 *
	 * @return A string representation of the Doctor object, including the values of its fields.
	 */
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", fullName=" + fullName + ", dob=" + dob + ", qualification=" + qualification
				+ ", specialist=" + specialist + ", email=" + email + ", mobNo=" + mobNo + ", password=" + password
				+ ", imageUrl=" + imageUrl + "]";
	}
}
