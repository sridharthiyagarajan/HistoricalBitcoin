package com.casestudy.it.bitcoinservice.client.coindesk.response;

import java.util.Map;

/**
 * Response DTO for coin desk bit coin price API.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
public class CoinDeskBitcoinResponse {

	/**
	 * Bitcoin price index.
	 */
	private Map<String, Double> bpi;

	/**
	 * This method is used to get bitcoin price index.
	 * 
	 * @return Map<String, Double>
	 */
	public Map<String, Double> getBpi() {
		return bpi;
	}

	/**
	 * This method is used to set bitcoin price index.
	 * 
	 * @param bpi
	 */
	public void setBpi(Map<String, Double> bpi) {
		this.bpi = bpi;
	}
}