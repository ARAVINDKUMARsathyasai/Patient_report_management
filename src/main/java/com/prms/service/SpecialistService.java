package com.prms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prms.entity.Specialist;
import com.prms.repo.SpecialistRepository;

import java.util.List;

/**
 * Service class for managing specialists.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see SpecialistRepository
 * @see Specialist
 */
@Service
public class SpecialistService {
	/**
	 * The repository for accessing and managing specialist data.
	 */
    @Autowired
    private SpecialistRepository specialistRepository;
    /**
     * Retrieves a list of all specialists.
     *
     * @return A list of all specialists.
     */
    public List<Specialist> getAllSpecialists() {
        return specialistRepository.findAll();
    }
}

