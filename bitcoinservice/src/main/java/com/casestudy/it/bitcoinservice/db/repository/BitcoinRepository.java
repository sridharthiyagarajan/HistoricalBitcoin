package com.casestudy.it.bitcoinservice.db.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.it.bitcoinservice.db.entity.BitcoinEntityDTO;

/**
 * DB repository for bitcoin price.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
public interface BitcoinRepository extends JpaRepository<BitcoinEntityDTO, Long> {

	/**
	 * This method is used to find bitcoin price entities based on start and end
	 * date range and currency in ascending order.
	 * 
	 * @param startDate
	 * @param endDate
	 * @param currency
	 * @return List<BitcoinEntityDTO>
	 */
	List<BitcoinEntityDTO> findByPriceDateBetweenAndCurrencyOrderByPriceDateAsc(LocalDate startDate, LocalDate endDate,
			String currency);

}