package com.prms.service;

import com.prms.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prms.repo.DoctorRepository;
import java.util.List;

/**
 * Service class for handling doctor-related operations.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see Doctor
 * @see DoctorRepository
 */
@Service
public class DoctorService {
	/**
     * The repository for accessing doctor data.
     */
    private final DoctorRepository doctorRepository;
    /**
     * Constructs a new DoctorService with the specified doctor repository.
     *
     * @param doctorRepository The doctor repository to be used.
     */
    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    /**
     * Retrieves a list of all doctors.
     *
     * @return A list of all doctors.
     */
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    
    /**
     * Finds a doctor by email and password.
     *
     * @param email    The email of the doctor.
     * @param password The password of the doctor.
     * @return The doctor with the specified email and password, or null if not found.
     */
    public Doctor findDoctorByEmailAndPassword(String email, String password) {
        return doctorRepository.findByEmailAndPassword(email, password);
    }
}
