package com.casestudy.it.bitcoinservice.service;

import java.time.LocalDate;
import java.util.List;

import com.casestudy.it.bitcoinservice.rest.response.BitcoinDTO;

/**
 * Bitcoin service interface.
 * 
 * @author SridharThiyagarajan
 *
 */
public interface BitcoinService {

	/**
	 * This method is used to get bitcoin history for given inputs.
	 * 
	 * @param startDate
	 * @param endDate
	 * @param currency
	 * @param useOfflineData
	 * @return List<BitcoinDTO>
	 */
	List<BitcoinDTO> getBitcoinHistoryForGivenInputs(LocalDate startDate, LocalDate endDate, String currency,
			boolean useOfflineData);

	/**
	 * This method is used to get bitcoin price by identifier.
	 * 
	 * @param id
	 * @return BitcoinDTO
	 */
	BitcoinDTO getBitcoinPriceById(long id);
}