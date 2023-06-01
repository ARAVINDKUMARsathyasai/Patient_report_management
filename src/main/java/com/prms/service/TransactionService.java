package com.prms.service;

import org.springframework.stereotype.Service;

import com.prms.entity.Transaction;
import com.prms.repo.TransactionRepository;

import java.util.List;

/**
 * Service class for managing transactions.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see Transaction
 * @see TransactionRepository
 */
@Service
public class TransactionService {
	/**
	 * The repository for accessing and managing transaction data.
	 */
    private final TransactionRepository transactionRepository;

    /**
     * Constructs a new TransactionService with the specified TransactionRepository.
     *
     * @param transactionRepository the repository for accessing and managing transaction data
     */
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    /**
     * Retrieves all transactions.
     *
     * @return a list of all transactions
     */
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
