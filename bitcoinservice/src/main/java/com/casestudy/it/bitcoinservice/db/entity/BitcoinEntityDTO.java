package com.casestudy.it.bitcoinservice.db.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Entity DTO for bitcoin price.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
@Entity(name = "bitcoin_prices")
public class BitcoinEntityDTO {

	/**
	 * Identifier.
	 */
	@Id
	@GeneratedValue
	private long id;

	/**
	 * Price date.
	 */
	private LocalDate priceDate;

	/**
	 * Created by user.
	 */
	private String createdBy;

	/**
	 * Created date.
	 */
	private LocalDate createdDate;

	/**
	 * Modified date.
	 */
	private LocalDate modifiedDate;

	/**
	 * Modified by user.
	 */
	private String modifiedBy;

	/**
	 * Price.
	 */
	private double price;

	/**
	 * Currency.
	 */
	private String currency;

	/**
	 * Constructor.
	 */
	public BitcoinEntityDTO() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param priceDate
	 * @param price
	 * @param currency
	 */
	public BitcoinEntityDTO(long id, LocalDate priceDate, double price, String currency) {
		super();
		this.id = id;
		this.priceDate = priceDate;
		this.price = price;
		this.currency = currency;
	}

	/**
	 * This method is used to get identifier.
	 * 
	 * @return long
	 */
	public long getId() {
		return id;
	}

	/**
	 * This method is used to set identifier.
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * This method is used to get price date.
	 * 
	 * @return LocalDate
	 */
	public LocalDate getPriceDate() {
		return priceDate;
	}

	/**
	 * This method is used to set price date.
	 * 
	 * @param priceDate
	 */
	public void setPriceDate(LocalDate priceDate) {
		this.priceDate = priceDate;
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
	 * This method is used to get created by user.
	 * 
	 * @return String
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * This method is used to set created by user.
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * This method is used to get created date.
	 * 
	 * @return LocalDate
	 */
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	/**
	 * This method is used to set created date.
	 * 
	 * @param createdDate
	 */
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * This method is used to get modified date.
	 * 
	 * @return LocalDate
	 */
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * This method is used to set modified date.
	 * 
	 * @param modifiedDate
	 */
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * This method is used to get modified by user.
	 * 
	 * @return String
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * This method is used to set modified by user.
	 * 
	 * @param modifiedBy
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * This method is used to get currency.
	 * 
	 * @return String
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * This method is used to set currency.
	 * 
	 * @param currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "BitcoinEntityDTO [id=" + id + ", priceDate=" + priceDate + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", modifiedDate=" + modifiedDate + ", modifiedBy=" + modifiedBy
				+ ", price=" + price + ", currency=" + currency + "]";
	}

}