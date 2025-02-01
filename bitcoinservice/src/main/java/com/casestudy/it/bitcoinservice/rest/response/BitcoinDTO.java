package com.casestudy.it.bitcoinservice.rest.response;

import java.time.LocalDate;

/**
 * API response DTO for bit coin price.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
public class BitcoinDTO {

	/**
	 * Price date.
	 */
	private LocalDate date;

	/**
	 * Price.
	 */
	private double price;

	/**
	 * Price type.
	 */
	private String priceType;

	/**
	 * Constructor
	 */
	public BitcoinDTO() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param date
	 * @param price
	 * @param priceType
	 */
	public BitcoinDTO(LocalDate date, double price, String priceType) {
		super();
		this.date = date;
		this.price = price;
		this.priceType = priceType;
	}

	/**
	 * This method is used to get price date.
	 * 
	 * @return
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * This method is used to set price date.
	 * 
	 * @param date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * This method is used to get price.
	 * 
	 * @return double
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * This method is used to set price.
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * This method is used to get price type.
	 * 
	 * @return String
	 */
	public String getPriceType() {
		return priceType;
	}

	/**
	 * This method is used to set price type.
	 * 
	 * @param priceType
	 */
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	@Override
	public String toString() {
		return "BitcoinDTO [date=" + date + ", price=" + price + ", priceType=" + priceType + "]";
	}

}