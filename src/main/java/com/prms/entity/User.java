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
 * This class represents a User entity with its attributes and operations.
 * <p>It contains fields such as id, fullname, email, password, role and imageUrl.
 * The id is generated automatically, and email is unique.
 * The password field is validated to ensure it meets specific requirements.
 * The imageUrl field represents the profile picture of the user.</p>
 * 
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
*/

@Entity
@Table(name="user")
public class User {
	/**
	 * The id of the user.
	 * this field is auto generated
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",length =45)
	private int id;
	
	/**
	 * The full name of the user.
	 * This field is required and must be between 4 and 30 characters long.
	*/
	@NotBlank(message ="Name field is required")
	@Size(min=4,max=30, message="min 4 and max 20 characters are allowed")
	@Column(name="full_name",length =50)
	private String fullname;
	
	/**
	 * The email address of the user.
	 * This field is required and must be a valid email address.
	 * It is also unique.
	*/
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	@NotBlank(message="Email field is required")
	@Column(name="email",length =255,unique=true)
	@NaturalId(mutable=true)
	private String email;
	
	/**
	 * This field is required and must be at least 8 characters long.
	 * It must contain at least one uppercase letter, one lowercase letter,
	 * one digit, and one special character.
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
	 * 	The role of the user.
	*/
	@Column(name="role",length =50)
	private String role;
	
	/**
	 * 	This is to check weather user has accepted the terms and conditions.
	*/
	@Column(name="enable")
    private boolean enabled;
	
	/**
	 * 	This is to check weather user has verified the mail id or initially it is set to false.
	*/
	@Column(name ="checked")
	private boolean checked = false;
	
	/**
	 * The URL of the user's profile picture.
	*/
	@Column(name="image",length =255)
	private String imageUrl;
	
	/**
	 * Constructs a new User object with default values for attributes.
	 */
	public User() {
		super();
	}

	/**
	 * Constructs a new User object with the specified values for attributes.
	 * @param id The id of the User.
	 * @param fullname The fullname of the User.
	 * @param email The email of the User.
	 * @param password The password of the User.
	 * @param role The role of the user generally it is user
	 * @param enabled to check weather the the user has agreed the terms and conditions 
	 * @param imageUrl The image URL of the User.
	 */
	public User(int id, String fullname, String email, String password,String role ,boolean enabled,String imageUrl) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
		this.imageUrl = imageUrl;
	}

	/**
	 * Returns the id of the User.
	 * @return The id of the User.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the User.
	 * @param id The id of the User.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the fullname of the User.
	 * @return The fullname of the User.
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * Sets the fullname of the User.
	 * @param fullname The fullname of the User.
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * Returns the email of the User.
	 * @return The email of the User.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the User.
	 * @param email The email of the User.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the password of the User.
	 * @return The password of the User.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the User.
	 * @param password The password of the User.
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * Returns the image URL of the User.
	 * @return The image URL of the User.
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Sets the image URL of the User.
	 * @param imageUrl The image URL of the User.
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * Gets the role of the user.
	 * @return the role of the user as a String.
	*/
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role of the user.
	 * @param role the role of the user as a String.
	*/
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Returns whether the user has enabled terms and conditions or not.
	 * @return true if the check box is enabled, false otherwise
	*/
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled status of the user agreement.
	 * @param enabled the new enabled status
	*/
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * Returns whether the user has verified his mail or not.
	 * @return true if email is verified, false otherwise
	*/
	public boolean isChecked() {
		return checked;
	}

	/**
	 * Sets the email verification status of the user.
	 * @param checked the new user verification status
	*/
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * Returns a String representation of the User object.
	 * @return a String representation of the User object
	*/
	@Override
	public String toString() {
		return "User [id=" + id + ", fullname=" + fullname + ", email=" + email + ", password=" + password + ", role="
				+ role + ", enabled=" + enabled + ", checked=" + checked + ", imageUrl=" + imageUrl + "]";
	}
	
}
