package com.prms.repo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prms.entity.Admin;
import com.prms.entity.User;

/**
 * Repository interface for performing CRUD operations on the Admin entity.
 * Extends JpaRepository interface providing generic CRUD operations and querying capabilities.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see User
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

	/**
     * Retrieves an Admin entity by email and password.
     *
     * @param email    The email of the Admin.
     * @param password The password of the Admin.
     * @return The Admin entity matching the provided email and password, or null if not found.
     */
    Admin findByEmailAndPassword(String email, String password);

    /**
     * Retrieves all Admin entities.
     *
     * @return A list of all Admin entities.
     */
    public List<Admin> findAll();

}
