package com.casestudy.it.bitcoinservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.casestudy.it.bitcoinservice.client.coindesk.CoinDeskBitcoinClient;
import com.casestudy.it.bitcoinservice.client.coindesk.response.CoinDeskBitcoinResponse;
import com.casestudy.it.bitcoinservice.db.entity.BitcoinCurrencyEntityDTO;
import com.casestudy.it.bitcoinservice.db.entity.BitcoinEntityDTO;
import com.casestudy.it.bitcoinservice.db.repository.BitcoinCurrencyRepository;
import com.casestudy.it.bitcoinservice.db.repository.BitcoinRepository;
import com.casestudy.it.bitcoinservice.exception.BitcoinCurrencyInvalidException;
import com.casestudy.it.bitcoinservice.rest.response.BitcoinDTO;

public class BitcoinServiceImplTest {

	@Mock
	private BitcoinRepository bitcoinRepository;

	@Mock
	private BitcoinCurrencyRepository bitcoinCurrencyRepository;

	@Mock
	private CoinDeskBitcoinClient coinDeskBitcoinClient;

	private BitcoinServiceImpl bitcoinService;

	@BeforeEach
	void setUp() {
		// Initialize mocks
		MockitoAnnotations.openMocks(this);

		// Instantiate the service with mocked dependencies
		bitcoinService = new BitcoinServiceImpl(bitcoinRepository, bitcoinCurrencyRepository, coinDeskBitcoinClient);
	}

	@Test
	void testGetBitcoinHistoryForGivenInputs_ValidCurrency_OfflineData() {
		// Setup
		String currency = "USD";
		LocalDate startDate = LocalDate.of(2023, 1, 1);
		LocalDate endDate = LocalDate.of(2023, 1, 31);
		boolean useOfflineData = true;

		// Mock the BitcoinCurrencyRepository to return a valid entity for "USD"
		BitcoinCurrencyEntityDTO mockCurrencyEntity = new BitcoinCurrencyEntityDTO();
		mockCurrencyEntity.setCurrency("USD");
		when(bitcoinCurrencyRepository.findByCurrency("USD")).thenReturn(mockCurrencyEntity);

		// Mock the repository data for Bitcoin prices
		List<BitcoinEntityDTO> mockBitcoinEntities = new ArrayList<>();
		BitcoinEntityDTO mockEntity = new BitcoinEntityDTO();
		mockEntity.setPriceDate(LocalDate.of(2023, 1, 1));
		mockEntity.setCurrency("USD");
		mockEntity.setPrice(30000.0);
		mockBitcoinEntities.add(mockEntity);

		when(bitcoinRepository.findByPriceDateBetweenAndCurrencyOrderByPriceDateAsc(startDate, endDate, "USD"))
				.thenReturn(mockBitcoinEntities);

		// Test
		List<BitcoinDTO> result = bitcoinService.getBitcoinHistoryForGivenInputs(startDate, endDate, currency,
				useOfflineData);

		// Assertions
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals(30000.0, result.get(0).getPrice());
	}

	@Test
	void testGetBitcoinHistoryForGivenInputs_InvalidCurrency_ThrowsException() {
		// Setup
		String invalidCurrency = "XYZ";
		LocalDate startDate = LocalDate.of(2023, 1, 1);
		LocalDate endDate = LocalDate.of(2023, 1, 31);
		boolean useOfflineData = false;

		// Mock the BitcoinCurrencyRepository to return null for invalid currency
		when(bitcoinCurrencyRepository.findByCurrency(invalidCurrency)).thenReturn(null);

		// Test & Assert
		assertThrows(BitcoinCurrencyInvalidException.class, () -> {
			bitcoinService.getBitcoinHistoryForGivenInputs(startDate, endDate, invalidCurrency, useOfflineData);
		});
	}

	@Test
	void testGetBitcoinPriceById() {
		// Setup
		long id = 1L;
		BitcoinEntityDTO mockEntity = new BitcoinEntityDTO();
		mockEntity.setId(id);
		mockEntity.setPrice(35000.0);
		when(bitcoinRepository.findById(id)).thenReturn(java.util.Optional.of(mockEntity));

		// Test
		BitcoinDTO result = bitcoinService.getBitcoinPriceById(id);

		// Assertions
		assertNotNull(result);
		assertEquals(35000.0, result.getPrice());
	}

	@Test
	void testGetBitcoinPriceById_NotFound() {
		// Setup
		long id = 999L;
		when(bitcoinRepository.findById(id)).thenReturn(java.util.Optional.empty());

		// Test
		BitcoinDTO result = bitcoinService.getBitcoinPriceById(id);

		// Assertions
		assertNull(result);
	}

}
