package com.casestudy.it.bitcoinservice.db.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Entity DTO for bitcoin currency.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
@Entity(name = "bitcoin_currency")
public class BitcoinCurrencyEntityDTO {

	/**
	 * Identifier.
	 */
	@Id
	@GeneratedValue
	private long id;

	/**
	 * ISO currency code.
	 */
	private String currency;

	/**
	 * Country description.
	 */
	private String country;

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
	 * Constructor.
	 */
	public BitcoinCurrencyEntityDTO() {
		super();
	}

	/**
	 * Constructor.
	 * 
	 * @param currency
	 * @param country
	 */
	public BitcoinCurrencyEntityDTO(String currency, String country) {
		super();
		this.currency = currency;
		this.country = country;
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
	 * This method is used to set country description.
	 * 
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
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

	@Override
	public String toString() {
		return "BitcoinCurrencyEntityDTO [id=" + id + ", currency=" + currency + ", country=" + country + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", modifiedBy="
				+ modifiedBy + "]";
	}

}