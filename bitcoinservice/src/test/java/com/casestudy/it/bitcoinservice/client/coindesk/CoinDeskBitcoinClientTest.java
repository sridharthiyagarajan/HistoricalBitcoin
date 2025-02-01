package com.casestudy.it.bitcoinservice.client.coindesk;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.casestudy.it.bitcoinservice.client.coindesk.response.CoinDeskBitcoinCurrencyResponse;
import com.casestudy.it.bitcoinservice.client.coindesk.response.CoinDeskBitcoinResponse;
import com.casestudy.it.bitcoinservice.db.entity.BitcoinCurrencyEntityDTO;
import com.casestudy.it.bitcoinservice.db.repository.BitcoinCurrencyRepository;
import com.casestudy.it.bitcoinservice.exception.BitcoinSystemException;

public class CoinDeskBitcoinClientTest {

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private BitcoinCurrencyRepository bitcoinCurrencyRepository;

	@InjectMocks
	private CoinDeskBitcoinClient coinDeskBitcoinClient;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetHistoricalBitcoinPrice() {
		// Setup
		String startDate = "2023-01-01";
		String endDate = "2023-01-31";
		String currency = "USD";

		// Mock RestTemplate to return a predefined CoinDeskBitcoinResponse
		CoinDeskBitcoinResponse mockResponse = mock(CoinDeskBitcoinResponse.class);
		when(restTemplate.getForObject(anyString(), eq(CoinDeskBitcoinResponse.class))).thenReturn(mockResponse);

		// Test
		CoinDeskBitcoinResponse response = coinDeskBitcoinClient.getHistoricalBitcoinPrice(startDate, endDate,
				currency);

		// Verify the call and the result
		assertNotNull(response);
		verify(restTemplate, times(1)).getForObject(anyString(), eq(CoinDeskBitcoinResponse.class));
	}

	@Test
    void testFetchSupportedCurrenciesOnStartup_NoCurrencies() {
        // Setup
        when(bitcoinCurrencyRepository.findAll()).thenReturn(List.of());

        // Mock RestTemplate to return an empty array
        ResponseEntity<CoinDeskBitcoinCurrencyResponse[]> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(new CoinDeskBitcoinCurrencyResponse[0]);
        when(restTemplate.getForEntity(anyString(), eq(CoinDeskBitcoinCurrencyResponse[].class)))
                .thenReturn(responseEntity);

        // Test the method
        coinDeskBitcoinClient.fetchSupportedCurrenciesOnStartup();

        // Verify that no save operation was triggered
        verify(bitcoinCurrencyRepository, never()).save(any(BitcoinCurrencyEntityDTO.class));
    }

	@Test
    void testFetchSupportedCurrenciesOnStartup_ExceptionHandling() {
        // Setup
        when(bitcoinCurrencyRepository.findAll()).thenReturn(List.of());

        // Mock RestTemplate to throw an exception
        when(restTemplate.getForEntity(anyString(), eq(CoinDeskBitcoinCurrencyResponse[].class)))
                .thenThrow(new RuntimeException("API call failed"));

        // Test & Assert
        assertThrows(BitcoinSystemException.class, () -> {
            coinDeskBitcoinClient.fetchSupportedCurrenciesOnStartup();
        });
    }
}
