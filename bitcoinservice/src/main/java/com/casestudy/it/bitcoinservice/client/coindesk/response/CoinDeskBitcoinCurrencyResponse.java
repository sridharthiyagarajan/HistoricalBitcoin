package com.casestudy.it.bitcoinservice.client.coindesk.response;

/**
 * Response DTO for coin desk currency API.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
public class CoinDeskBitcoinCurrencyResponse {

	/**
	 * ISO currency code.
	 */
	private String currency;

	/**
	 * Country description.
	 */
	private String country;

	/**
	 * This method is used to get ISO currency code.
	 * 
	 * @return String
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * This method is used to set ISO currency code.
	 * 
	 * @param currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * This method is used to get country description.
	 * 
	 * @return String
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * This method is used to get country description.
	 * 
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

}