package com.casestudy.it.bitcoinservice.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.casestudy.it.bitcoinservice.rest.response.BitcoinDTO;
import com.casestudy.it.bitcoinservice.service.BitcoinService;

@WebMvcTest(BitcoinController.class)
public class BitcoinControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BitcoinService bitcoinService;

	@InjectMocks
	private BitcoinController bitcoinController;

	private BitcoinDTO bitcoinDTO;

	@BeforeEach
	public void setUp() {
		bitcoinDTO = new BitcoinDTO();
		bitcoinDTO.setPrice(50000);
		bitcoinDTO.setDate(LocalDate.now());
	}

	@Test
	public void testGetBitcoinHistoryForGivenInputs() throws Exception {
		// Arrange
		LocalDate startDate = LocalDate.of(2023, 1, 1);
		LocalDate endDate = LocalDate.of(2023, 1, 31);
		when(bitcoinService.getBitcoinHistoryForGivenInputs(startDate, endDate, "USD", true))
				.thenReturn(Arrays.asList(bitcoinDTO));

		// Act & Assert
		mockMvc.perform(
				get("/bitcoin-history").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes())).param("startDate", startDate.toString()).param("endDate", endDate.toString())
						.param("currency", "USD").param("useOfflineData", "true").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].price").value(50000));
	}

	@Test
	public void testGetBitcoinPriceDetail() throws Exception {
		// Arrange
		long id = 1L;
		LocalDate startDate = LocalDate.of(2023, 1, 1);
		LocalDate endDate = LocalDate.of(2023, 1, 31);
		when(bitcoinService.getBitcoinPriceById(id)).thenReturn(bitcoinDTO);

		// Act & Assert
		mockMvc.perform(get("/bitcoin-history/bitcoin/{id}", id).header("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes())).param("startDate", startDate.toString())
				.param("endDate", endDate.toString()).param("currency", "USD").param("useOfflineData", "true")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.price").value(50000));
	}

	@Test
	public void testBitcoinPriceNotFoundException() throws Exception {
		// Arrange
		long id = 999L; // Non-existing ID
		when(bitcoinService.getBitcoinPriceById(id)).thenReturn(null);

		// Act & Assert
		mockMvc.perform(get("/bitcoin-history/bitcoin/{id}", id).header("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes())).param("startDate", LocalDate.of(2023, 1, 1).toString())
				.param("endDate", LocalDate.of(2023, 1, 31).toString()).param("currency", "USD")
				.param("useOfflineData", "true").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
}
