package com.casestudy.it.bitcoinservice.db.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "bitcoin_prices")
public class BitcoinEntityDTO {

	@Id
	private long id;

	private LocalDate priceDate;

	private LocalDate createdDate;

	private String createdBy;

	private LocalDate modifiedDate;

	private String modifiedBy;

	private double price;

	private String currency;

	public BitcoinEntityDTO() {
		super();
	}

	public BitcoinEntityDTO(long id, LocalDate priceDate, double price, String currency) {
		super();
		this.id = id;
		this.priceDate = priceDate;
		this.price = price;
		this.currency = currency;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(LocalDate priceDate) {
		this.priceDate = priceDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getCurrency() {
		return currency;
	}

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