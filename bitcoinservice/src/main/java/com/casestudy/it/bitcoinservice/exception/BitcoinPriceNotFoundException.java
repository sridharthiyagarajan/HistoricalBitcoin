package com.casestudy.it.bitcoinservice.exception;

/**
 * Bitcoin price not found exception.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
public class BitcoinPriceNotFoundException extends RuntimeException {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public BitcoinPriceNotFoundException(String message) {
		super(message);
	}

}