package com.casestudy.it.bitcoinservice.exception;

import java.time.LocalDateTime;

/**
 * Error details DTO.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
public class ErrorDetails {

	/**
	 * Time stamp.
	 */
	private LocalDateTime timestamp;

	/**
	 * Message.
	 */
	private String message;

	/**
	 * Details.
	 */
	private String details;

	/**
	 * Constructor.
	 * 
	 * @param timestamp
	 * @param message
	 * @param details
	 */
	public ErrorDetails(LocalDateTime timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	/**
	 * This method is used to get time stamp.
	 * 
	 * @return LocalDateTime
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	/**
	 * This method is used to get message.
	 * 
	 * @return String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * This method is used to get details.
	 * 
	 * @return String
	 */
	public String getDetails() {
		return details;
	}

}
