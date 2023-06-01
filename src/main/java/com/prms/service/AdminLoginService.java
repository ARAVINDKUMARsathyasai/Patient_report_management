package com.prms.service;

import org.springframework.stereotype.Service;

import com.prms.entity.Admin;
import com.prms.repo.AdminRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service class for handling admin login operations.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 * 
 * @see Admin
 * @see AdminRepository
 */
@Service
@RequiredArgsConstructor
public class AdminLoginService {
	/**
     * The repository for accessing admin data.
     */
	private final AdminRepository adminRepo;
	 /**
     * Performs admin login by verifying the provided email and password.
     *
     * @param email    The email of the admin.
     * @param password The password of the admin.
     * @return The logged-in admin, or null if the credentials are invalid.
     */
	public Admin login(String email, String password) {
		Admin user = adminRepo.findByEmailAndPassword(email, password);
		return user;
	}
}
