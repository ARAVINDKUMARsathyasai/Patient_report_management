package com.prms.repo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.prms.entity.User;

/**
 * Repository interface for managing User entities.
 * Extends JpaRepository to inherit basic CRUD operations.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see User
 */
public interface UserRepository extends JpaRepository<User, Integer>{
	/**
     * Finds a user by their email address.
     *
     * @param email the email address to search for
     * @return an Optional containing the User if found, or an empty Optional if not found
     */
	public Optional<User> findByEmail(@Param("email") String email);
	/**
     * Retrieves all users.
     *
     * @return a List of all users
     */
	public List<User> findAll();
}
