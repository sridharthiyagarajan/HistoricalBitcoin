package com.casestudy.it.bitcoinservice.client.coindesk.response;

import java.util.Map;

public class CoinDeskBitcoinResponse {

	private Map<String, Double> bpi;

	public Map<String, Double> getBpi() {
		return bpi;
	}

	public void setBpi(Map<String, Double> bpi) {
		this.bpi = bpi;
	}
}