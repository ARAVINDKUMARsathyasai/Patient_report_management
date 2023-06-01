package com.prms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * The main class to start the PRM Management application.
 * This class serves as the entry point for the application.
 * It initializes the Spring Boot application and starts the application server.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 */
@SpringBootApplication
public class PrmManagementApplication {
	/**
	 * The main method to start the PRM Management application.
	 * It initializes the Spring Boot application and starts the application server.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		// Run the Spring Boot application
		SpringApplication.run(PrmManagementApplication.class, args);
	}
}
