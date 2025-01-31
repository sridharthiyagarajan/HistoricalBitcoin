package com.casestudy.it.bitcoinservice.service;

import java.time.LocalDate;
import java.util.List;

import com.casestudy.it.bitcoinservice.rest.response.BitcoinDTO;

public interface BitcoinService {

	List<BitcoinDTO> getBitcoinHistoryForGivenDateRange(LocalDate startDate, LocalDate endDate, String currency,
			boolean useOfflineData);
	
	BitcoinDTO getBitcoinPriceById(long id);
}