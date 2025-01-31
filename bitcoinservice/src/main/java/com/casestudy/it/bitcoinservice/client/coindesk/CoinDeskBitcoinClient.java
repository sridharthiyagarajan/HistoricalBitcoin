package com.casestudy.it.bitcoinservice.client.coindesk;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.casestudy.it.bitcoinservice.client.coindesk.response.CoinDeskBitcoinResponse;

@Service
public class CoinDeskBitcoinClient {

	private final RestTemplate restTemplate;

	public CoinDeskBitcoinClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@SuppressWarnings("deprecation")
	public CoinDeskBitcoinResponse getHistoricalBitcoinPrice(String startDate, String endDate, String currency) {

		String url = UriComponentsBuilder.fromHttpUrl("https://api.coindesk.com/v1/bpi/historical/close.json")
				.queryParam("start", startDate).queryParam("end", endDate).queryParam("currency", currency)
				.toUriString();

		return restTemplate.getForObject(url, CoinDeskBitcoinResponse.class);
	}
}