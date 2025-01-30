package com.casestudy.it.bitcoinservice.rest.response;

import java.time.LocalDate;

public class BitcoinDTO {

	private LocalDate date;

	private double price;

	private String priceType;

	public BitcoinDTO() {
		super();
	}

	public BitcoinDTO(LocalDate date, double price, String priceType) {
		super();
		this.date = date;
		this.price = price;
		this.priceType = priceType;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	@Override
	public String toString() {
		return "BitcoinDTO [date=" + date + ", price=" + price + ", priceType=" + priceType + "]";
	}

}