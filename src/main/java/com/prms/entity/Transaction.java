package com.prms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a transaction entity in the PRM System.
 * This class stores information about a transaction made by a user.
 * Each transaction has an associated user who made the payment.
 * The transaction includes details such as the order ID, amount, receipt, and status.
 * The transaction is linked to a payment ID for reference.
 * 
 * Note: The user field is associated with the User entity through a Many-to-One relationship.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 */
@Entity
@Table(name="transaction")
public class Transaction {
	
	/**
	 * The unique identifier for the transaction.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * The order ID associated with the transaction.
	 */
	private String orderId;
	
	/**
	 * The amount of the transaction.
	 */
	private String amount;
	
	/**
	 * The receipt reference of the transaction.
	 */
	private String receipt;
	
	/**
	 * The status of the transaction.
	 */
	private String status;
	
	/**
	 * The user who made the transaction.
	 * This field is associated with the User entity through a Many-to-One relationship.
	 */
	@ManyToOne
	private User user;
	
	/**
	 * The payment ID associated with the transaction.
	 */
	private String paymentId;

	/**
	 * Constructs a new Transaction instance with the specified details.
	 * 
	 * @param id The unique identifier for the transaction.
	 * @param orderId The order ID associated with the transaction.
	 * @param amount The amount of the transaction.
	 * @param receipt The receipt reference of the transaction.
	 * @param status The status of the transaction.
	 * @param user The user who made the transaction.
	 * @param paymentId The payment ID associated with the transaction.
	 */
	public Transaction(Long id, String orderId, String amount, String receipt, String status, User user,
			String paymentId) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.amount = amount;
		this.receipt = receipt;
		this.status = status;
		this.user = user;
		this.paymentId = paymentId;
	}

	/**
	 * Constructs a new Transaction instance with the specified details.
	 * 
	 * @param orderId The order ID associated with the transaction.
	 * @param amount The amount of the transaction.
	 * @param receipt The receipt reference of the transaction.
	 * @param status The status of the transaction.
	 * @param user The user who made the transaction.
	 * @param paymentId The payment ID associated with the transaction.
	 */
	public Transaction(String orderId, String amount, String receipt, String status, User user, String paymentId) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.receipt = receipt;
		this.status = status;
		this.user = user;
		this.paymentId = paymentId;
	}

	/**
	 * Constructs a new empty Transaction instance.
	 */
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the unique identifier for the transaction.
	 * 
	 * @return The unique identifier for the transaction.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the unique identifier for the transaction.
	 * 
	 * @param id The unique identifier for the transaction.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the order ID associated with the transaction.
	 * 
	 * @return The order ID associated with the transaction.
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * Sets the order ID associated with the transaction.
	 * 
	 * @param orderId The order ID associated with the transaction.
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * Gets the amount of the transaction.
	 * 
	 * @return The amount of the transaction.
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * Sets the amount of the transaction.
	 * 
	 * @param amount The amount of the transaction.
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * Gets the receipt reference of the transaction.
	 * 
	 * @return The receipt reference of the transaction.
	 */
	public String getReceipt() {
		return receipt;
	}

	/**
	 * Sets the receipt reference of the transaction.
	 * 
	 * @param receipt The receipt reference of the transaction.
	 */
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	/**
	 * Gets the status of the transaction.
	 * 
	 * @return The status of the transaction.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status of the transaction.
	 * 
	 * @param status The status of the transaction.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the user who made the transaction.
	 * 
	 * @return The user who made the transaction.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user who made the transaction.
	 * 
	 * @param user The user who made the transaction.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the payment ID associated with the transaction.
	 * 
	 * @return The payment ID associated with the transaction.
	 */
	public String getPaymentId() {
		return paymentId;
	}

	/**
	 * Sets the payment ID associated with the transaction.
	 * 
	 * @param paymentId The payment ID associated with the transaction.
	 */
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * Returns a string representation of the Transaction object.
	 * The string contains the values of the id, orderId, amount, receipt, status, user, and paymentId fields.
	 * 
	 * @return A string representation of the Transaction object.
	 */
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", orderId=" + orderId + ", amount=" + amount + ", receipt=" + receipt
				+ ", status=" + status + ", user=" + user + ", paymentId=" + paymentId + "]";
	}	
}
