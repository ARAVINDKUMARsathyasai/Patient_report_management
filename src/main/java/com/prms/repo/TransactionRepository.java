package com.prms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prms.entity.Transaction;
/**
 * Repository interface for managing transactions in the database.
 * Extends JpaRepository to inherit basic CRUD operations.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see Transaction
 */
public interface TransactionRepository extends JpaRepository<Transaction,Long>{
	/**
     * Finds a transaction by its order ID.
     *
     * @param orderId The order ID to search for.
     * @return The transaction with the specified order ID, or null if not found.
     */
	public Transaction findByOrderId(String orderId);
}
