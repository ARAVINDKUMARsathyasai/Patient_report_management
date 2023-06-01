package com.prms.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents an admin user in the system.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 */
@Entity
@Table(name="admin")
public class Admin {

	/**
     * The unique identifier for the admin.
     */	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",length =45)
	private int id;
	
	/**
     * The full name of the admin.
     */
	@NotBlank(message ="Name field is required")
	@Size(min=4,max=30, message="min 4 and max 20 characters are allowed")
	@Column(name="full_name",length =50)
	private String fullname;
	
	/**
     * The email address of the admin.
     */
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	@NotBlank(message="Email field is required")
	@Column(name="email",length =255,unique=true)
	@NaturalId(mutable=true)
	private String email;
	
	/**
     * The password of the admin.
     * Must be at least 8 characters long and contain at least one uppercase letter,
     * one lowercase letter, one digit, and one special character.
     */
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
	        message = "Password must be at least 8 characters long and "
	        		+ "contain at least one uppercase letter, "
	        		+ "one lowercase letter, "
	        		+ "one digit, "
	        		+ "and one special character.")
		@Column(name="password",length =255)
		@NotBlank(message="Password field is required")
	private String password;
	
	 /**
     * The role of the admin.
     */
	@Column(name="role",length =50)
	private String role;

	/**
     * Constructs a new instance of the Admin class.
     *
     * @param id       The ID of the admin.
     * @param fullname The full name of the admin.
     * @param email    The email address of the admin.
     * @param password The password of the admin.
     * @param role     The role of the admin.
     */
	public Admin(int id,
			@NotBlank(message = "Name field is required") @Size(min = 4, max = 30, message = "min 4 and max 20 characters are allowed") String fullname,
			@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$") @NotBlank(message = "Email field is required") String email,
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.") @NotBlank(message = "Password field is required") String password,
			String role) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	/**
     * Constructs a new instance of the Admin class.
     *
     * @param fullname The full name of the admin.
     * @param email    The email address of the admin.
     * @param password The password of the admin.
     * @param role     The role of the admin.
     */
	public Admin(
			@NotBlank(message = "Name field is required") @Size(min = 4, max = 30, message = "min 4 and max 20 characters are allowed") String fullname,
			@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$") @NotBlank(message = "Email field is required") String email,
			@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.") @NotBlank(message = "Password field is required") String password,
			String role) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	/**
     * Constructs a new instance of the Admin class.
     */
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	 /**
     * Retrieves the ID of the admin.
     *
     * @return The ID of the admin.
     */
	public int getId() {
		return id;
	}

	/**
     * Sets the ID of the admin.
     *
     * @param id The ID of the admin.
     */
	public void setId(int id) {
		this.id = id;
	}

	/**
     * Retrieves the full name of the admin.
     *
     * @return The full name of the admin.
     */
	public String getFullname() {
		return fullname;
	}

	/**
     * Sets the full name of the admin.
     *
     * @param fullname The full name of the admin.
     */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
     * Retrieves the email address of the admin.
     *
     * @return The email address of the admin.
     */
	public String getEmail() {
		return email;
	}

	/**
     * Sets the email address of the admin.
     *
     * @param email The email address of the admin.
     */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
     * Retrieves the password of the admin.
     *
     * @return The password of the admin.
     */
	public String getPassword() {
		return password;
	}
	/**
     * Sets the password of the admin.
     *
     * @param password The password of the admin.
     */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
     * Retrieves the role of the admin.
     *
     * @return The role of the admin.
     */
	public String getRole() {
		return role;
	}
	/**
     * Sets the role of the admin.
     *
     * @param role The role of the admin.
     */
	public void setRole(String role) {
		this.role = role;
	}

	/**
     * Returns a string representation of the Admin object.
     *
     * @return A string representation of the Admin object.
     */
	@Override
	public String toString() {
		return "Admin [id=" + id + ", fullname=" + fullname + ", email=" + email + ", password=" + password + ", role="
				+ role + "]";
	}
}
