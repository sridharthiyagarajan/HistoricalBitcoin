package com.casestudy.it.bitcoinservice.exception;

public class BitcoinPriceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BitcoinPriceNotFoundException(String message) {
		super(message);
	}

}
