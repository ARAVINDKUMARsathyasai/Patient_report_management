package com.prms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prms.entity.VerificationToken;

/**
 * The repository interface for managing VerificationToken entities.
 * Extends the JpaRepository interface provided by Spring Data JPA.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see JpaRepository
 * @see VerificationToken
*/
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
	/**
	 * 	Retrieves a VerificationToken entity by its token.
	 * 
	 * @param token The token used for retrieval.
	 * @return The VerificationToken entity matching the token, or null if not found.
	*/
	public VerificationToken findByToken(String token);
}
