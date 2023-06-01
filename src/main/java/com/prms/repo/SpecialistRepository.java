package com.prms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prms.entity.Specialist;

/**
 * Repository interface for managing Specialist entities.
 * Extends JpaRepository to inherit basic CRUD and query operations.
 *
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see Specialist
 */

public interface SpecialistRepository extends JpaRepository<Specialist, Integer> {
 // no  additional methods
}
