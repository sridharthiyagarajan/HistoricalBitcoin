package com.casestudy.it.bitcoinservice.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.it.bitcoinservice.db.entity.BitcoinCurrencyEntityDTO;

/**
 * DB repository for bitcoin currency.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
public interface BitcoinCurrencyRepository extends JpaRepository<BitcoinCurrencyEntityDTO, Long> {

	/**
	 * This method is used to find bitcoin currency entity based on currency.
	 * 
	 * @param currency
	 * @return BitcoinCurrencyEntityDTO
	 */
	BitcoinCurrencyEntityDTO findByCurrency(String currency);

	/**
	 * This method is used to find bitcoin currency entity based on currency and
	 * country.
	 * 
	 * @param currency
	 * @param country
	 * @return BitcoinCurrencyEntityDTO
	 */
	BitcoinCurrencyEntityDTO findByCurrencyAndCountry(String currency, String country);

}