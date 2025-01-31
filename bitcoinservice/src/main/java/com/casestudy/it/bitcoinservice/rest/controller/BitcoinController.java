package com.casestudy.it.bitcoinservice.rest.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.it.bitcoinservice.exception.BitcoinPriceNotFoundException;
import com.casestudy.it.bitcoinservice.rest.response.BitcoinDTO;
import com.casestudy.it.bitcoinservice.service.BitcoinService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/bitcoin-history")
@Validated
public class BitcoinController {

	private BitcoinService bitcoinService;

	public BitcoinController(BitcoinService bitcoinService) {
		this.bitcoinService = bitcoinService;
	}

	@GetMapping
	public ResponseEntity<List<BitcoinDTO>> getBitcoinHistoryForGivenDateRange(
			@RequestParam @NotNull(message = "{validation.start.date.empty.error}") @Past(message = "{validation.start.date.past.error}") LocalDate startDate,
			@RequestParam @NotNull(message = "{validation.end.date.empty.error}") @Past(message = "{validation.end.date.past.error}") LocalDate endDate,
			@RequestParam(defaultValue = "USD") @Pattern(regexp = "USD|INR", message = "{validation.currency.error}") String currency,
			@RequestParam(defaultValue = "false") boolean useOfflineData) {

		List<BitcoinDTO> response = null;

		response = bitcoinService.getBitcoinHistoryForGivenDateRange(startDate, endDate, currency, useOfflineData);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/bitcoin/{id}")
	public BitcoinDTO getBitcoinPriceDetail(@PathVariable("id") long id,
			@RequestParam @NotNull(message = "{validation.start.date.empty.error}") @Past(message = "{validation.start.date.past.error}") LocalDate startDate,
			@RequestParam @NotNull(message = "{validation.end.date.empty.error}") @Past(message = "{validation.end.date.past.error}") LocalDate endDate,
			@RequestParam(defaultValue = "USD") String currency,
			@RequestParam(defaultValue = "false") boolean useOfflineData) {

		BitcoinDTO outputBitcoinDTO = bitcoinService.getBitcoinPriceById(id);

		if (outputBitcoinDTO == null) {
			throw new BitcoinPriceNotFoundException("id: " + id);
		}

		EntityModel<BitcoinDTO> outputEntityModel = EntityModel.of(outputBitcoinDTO);

		// Add link to the filtered list with specific query parameters
		WebMvcLinkBuilder filteredListLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(BitcoinController.class)
						.getBitcoinHistoryForGivenDateRange(startDate, endDate, currency, useOfflineData));

		outputEntityModel.add(filteredListLink.withRel("filtered-list"));

		return outputBitcoinDTO;
	}

}
