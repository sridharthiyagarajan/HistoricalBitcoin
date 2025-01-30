package com.casestudy.it.bitcoinservice.db.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.it.bitcoinservice.db.entity.BitcoinEntityDTO;

public interface BitcoinRepository extends JpaRepository<BitcoinEntityDTO, Long> {

	List<BitcoinEntityDTO> findByPriceDateBetweenAndCurrencyOrderByPriceDateAsc(LocalDate startDate, LocalDate endDate, String currency);

}