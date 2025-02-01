package com.casestudy.it.bitcoinservice.exception;

/**
 * Bitcoin currency invalid exception.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
public class BitcoinCurrencyInvalidException extends RuntimeException {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public BitcoinCurrencyInvalidException(String message) {
		super(message);
	}

}