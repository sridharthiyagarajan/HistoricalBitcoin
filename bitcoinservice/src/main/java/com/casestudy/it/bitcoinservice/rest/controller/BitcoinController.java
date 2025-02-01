package com.casestudy.it.bitcoinservice.rest.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
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

/**
 * Rest controller exposing bitcoin APIs.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
@RestController
@RequestMapping("/bitcoin-history")
@Validated
public class BitcoinController {

	/**
	 * Logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BitcoinController.class);

	/**
	 * Bitcoin service reference.
	 */
	private BitcoinService bitcoinService;

	/**
	 * Constructor.
	 * 
	 * @param bitcoinService
	 */
	public BitcoinController(BitcoinService bitcoinService) {
		this.bitcoinService = bitcoinService;
	}

	/**
	 * This method is used to get bitcoin price history for given inputs.
	 * 
	 * @param startDate
	 * @param endDate
	 * @param currency
	 * @param useOfflineData
	 * @return <List<BitcoinDTO>
	 */
	@GetMapping
	public ResponseEntity<List<BitcoinDTO>> getBitcoinHistoryForGivenInputs(
			@RequestParam @NotNull(message = "{validation.start.date.empty.error}") @Past(message = "{validation.start.date.past.error}") LocalDate startDate,
			@RequestParam @NotNull(message = "{validation.end.date.empty.error}") @Past(message = "{validation.end.date.past.error}") LocalDate endDate,
			@RequestParam(defaultValue = "USD") @NotNull(message = "{validation.currency.error}") String currency,
			@RequestParam(defaultValue = "true") boolean useOfflineData) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("In getBitcoinHistoryForGivenInputs - STARTED.");
		}

		if (LOG.isInfoEnabled()) {
			LOG.info(String.format(
					"In getBitcoinHistoryForGivenInputs - inputs are: startDate - %s, endDate - %s, currency - %s, useOfflineData - %s.",
					startDate, endDate, currency, useOfflineData));
		}

		List<BitcoinDTO> outputBitcoinDTOs = bitcoinService.getBitcoinHistoryForGivenInputs(startDate, endDate,
				currency, useOfflineData);

		if (LOG.isDebugEnabled()) {
			LOG.debug("In getBitcoinHistoryForGivenInputs - ENDED.");
		}

		if (CollectionUtils.isEmpty(outputBitcoinDTOs)) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(outputBitcoinDTOs);
		}

	}

	/**
	 * This method is used to retrieve bitcoin price detail.
	 * 
	 * @param id
	 * @param startDate
	 * @param endDate
	 * @param currency
	 * @param useOfflineData
	 * @return BitcoinDTO
	 */
	@GetMapping("/bitcoin/{id}")
	public BitcoinDTO getBitcoinPriceDetail(@PathVariable("id") long id,
			@RequestParam @NotNull(message = "{validation.start.date.empty.error}") @Past(message = "{validation.start.date.past.error}") LocalDate startDate,
			@RequestParam @NotNull(message = "{validation.end.date.empty.error}") @Past(message = "{validation.end.date.past.error}") LocalDate endDate,
			@RequestParam(defaultValue = "USD") String currency,
			@RequestParam(defaultValue = "true") boolean useOfflineData) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("In getBitcoinPriceDetail - STARTED.");
		}

		if (LOG.isInfoEnabled()) {
			LOG.info(String.format(
					"In getBitcoinPriceDetail - inputs are: id - %s, startDate - %s, endDate - %s, currency - %s, useOfflineData - %s.",
					id, startDate, endDate, currency, useOfflineData));
		}

		BitcoinDTO outputBitcoinDTO = bitcoinService.getBitcoinPriceById(id);

		if (outputBitcoinDTO == null) {
			throw new BitcoinPriceNotFoundException("id: " + id);
		}

		EntityModel<BitcoinDTO> outputEntityModel = EntityModel.of(outputBitcoinDTO);

		WebMvcLinkBuilder filteredListLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(BitcoinController.class).getBitcoinHistoryForGivenInputs(startDate,
						endDate, currency, useOfflineData));

		outputEntityModel.add(filteredListLink.withRel("filtered-list"));

		if (LOG.isDebugEnabled()) {
			LOG.debug("In getBitcoinPriceDetail - ENDED.");
		}

		return outputBitcoinDTO;
	}

}
