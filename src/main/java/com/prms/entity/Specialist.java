package com.prms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * This class represents a User entity with its attributes and operations.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
*/

@Entity
@Table(name="specialist")
public class Specialist {
	
	/**
	 * The ID of the specialist.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",length =45)
	private int id;
	
	/**
	 * The name of the specialist.
	 * It should be unique among all specialists.
	 */
	@Column(name="specialist",length =50,unique=true)
	private String specialistname;
	
	/**
	 * Constructs a new empty Specialist instance.
	 */
	public Specialist() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a new Specialist instance with the specified ID and specialist name.
	 * 
	 * @param id              The ID of the specialist.
	 * @param specialistname  The name of the specialist.
	 */
	public Specialist(int id, String specialistname) {
		super();
		this.id = id;
		this.specialistname = specialistname;
	}

	/**
	 * Gets the ID of the specialist.
	 * 
	 * @return The ID of the specialist.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the ID of the specialist.
	 * 
	 * @param id  The ID of the specialist.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name of the specialist.
	 * 
	 * @return The name of the specialist.
	 */
	public String getSpecialistname() {
		return specialistname;
	}

	/**
	 * Sets the name of the specialist.
	 * 
	 * @param specialistname  The name of the specialist.
	 */
	public void setSpecialistname(String specialistname) {
		this.specialistname = specialistname;
	}

	/**
	 * Returns a string representation of the Specialist object.
	 * 
	 * @return A string representation of the Specialist object, including the values of its fields.
	 */
	@Override
	public String toString() {
		return "Specialist [id=" + id + ", specialistname=" + specialistname + "]";
	}
}
