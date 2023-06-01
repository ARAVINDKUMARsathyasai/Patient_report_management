package com.prms.helper;

/**
 * The Message class represents a message with its content and type.
 * 
 * @author ARAVINDKUMAR SATHYASAI BOBBA
 * @version 1.0
 * @since   05/05/2023
 */
public class Message {
	/**
	 * The content of the message.
	 */
	private String content;
	/**
	 * The type of the message.
	 */
	private String type;
	
	/**
	 * Constructs a new Message object with the given content and type.
	 *
	 * @param content the content of the message
	 * @param type the type of the message
	 */
	public Message(String content, String type) {
		super();
		this.content = content;
		this.type = type;
	}
	
	/**
	 * Constructs a new empty Message object.
	 */
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Returns the content of the message.
	 *
	 * @return the content of the message
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * Sets the content of the message.
	 *
	 * @param content the content of the message
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * Returns the type of the message.
	 *
	 * @return the type of the message
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type of the message.
	 *
	 * @param type the type of the message
	 */
	public void setType(String type) {
		this.type = type;
	}
}
