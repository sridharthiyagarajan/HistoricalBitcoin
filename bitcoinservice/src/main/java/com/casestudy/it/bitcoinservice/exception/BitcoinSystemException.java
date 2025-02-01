package com.casestudy.it.bitcoinservice.exception;

/**
 * Bitcoin price system exception.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
public class BitcoinSystemException extends RuntimeException {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public BitcoinSystemException(String message) {
		super(message);
	}

}