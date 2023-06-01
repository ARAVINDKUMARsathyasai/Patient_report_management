package com.prms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prms.entity.Doctor;
import com.prms.entity.User;

/**
 * Repository interface for accessing and managing Doctor entities in the database.
 * Extends JpaRepository to inherit basic CRUD operations and querying capabilities.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see Doctor
 */
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
	/**
     * Retrieves a list of all doctors.
     *
     * @return List of all doctors
     */
	public List<Doctor> findAll();
	 /**
     * Retrieves a doctor by their email and password.
     *
     * @param email    The email of the doctor
     * @param password The password of the doctor
     * @return The found doctor, or null if not found
     */
	Doctor findByEmailAndPassword(String email, String password);
}
