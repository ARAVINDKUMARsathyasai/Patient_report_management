package com.prms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prms.entity.Appointment;

/**
 * Repository interface for managing appointments in the database.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see Appointment
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	 /**
     * Retrieves all appointments from the database.
     *
     * @return A list of all appointments.
     */
	public List<Appointment> findAll();

	 /**
     * Counts the number of appointments associated with a specific doctor.
     *
     * @param id The ID of the doctor.
     * @return The count of appointments for the specified doctor.
     */
	public int countByDoctorId(int id);

	/**
     * Retrieves all appointments associated with a specific doctor.
     *
     * @param id The ID of the doctor.
     * @return A list of appointments for the specified doctor.
     */
	public List<Appointment> findAllByDoctorId(int id);


}
