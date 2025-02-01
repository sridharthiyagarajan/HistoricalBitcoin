package com.casestudy.it.bitcoinservice.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.casestudy.it.bitcoinservice.client.coindesk.CoinDeskBitcoinClient;
import com.casestudy.it.bitcoinservice.client.coindesk.response.CoinDeskBitcoinResponse;
import com.casestudy.it.bitcoinservice.db.entity.BitcoinEntityDTO;
import com.casestudy.it.bitcoinservice.db.repository.BitcoinCurrencyRepository;
import com.casestudy.it.bitcoinservice.db.repository.BitcoinRepository;
import com.casestudy.it.bitcoinservice.exception.BitcoinCurrencyInvalidException;
import com.casestudy.it.bitcoinservice.rest.response.BitcoinDTO;
import com.casestudy.it.bitcoinservice.service.BitcoinService;
import com.casestudy.it.bitcoinservice.service.util.BitcoinPriceType;
import com.casestudy.it.bitcoinservice.service.util.BitcoinServiceUtil;

/**
 * Bitcoin service implementation.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
@Service
public class BitcoinServiceImpl implements BitcoinService {

	/**
	 * Logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BitcoinServiceImpl.class);

	/**
	 * Bitcoin repository reference.
	 */
	private BitcoinRepository bitcoinRepository;

	/**
	 * Bitcoin currency repository reference.
	 */
	private BitcoinCurrencyRepository bitcoinCurrencyRepository;

	/**
	 * Coin desk bitcoin client.
	 */
	private CoinDeskBitcoinClient coinDeskBitcoinClient;

	/**
	 * Constructor.
	 * 
	 * @param bitcoinRepository
	 * @param bitcoinCurrencyRepository
	 * @param coinDeskBitcoinClient
	 */
	public BitcoinServiceImpl(BitcoinRepository bitcoinRepository, BitcoinCurrencyRepository bitcoinCurrencyRepository,
			CoinDeskBitcoinClient coinDeskBitcoinClient) {
		this.bitcoinRepository = bitcoinRepository;
		this.bitcoinCurrencyRepository = bitcoinCurrencyRepository;
		this.coinDeskBitcoinClient = coinDeskBitcoinClient;
	}

	@Transactional(readOnly = true)
	@Cacheable("bitcoinPrices")
	public List<BitcoinDTO> getBitcoinHistoryForGivenInputs(LocalDate startDate, LocalDate endDate, String currency,
			boolean useOfflineData) {

		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format(
					"In getBitcoinHistoryForGivenInputs - STARTED and inputs are: startDate - %s, endDate - %s, currency - %s, useOfflineData - %s.",
					startDate, endDate, currency, useOfflineData));
		}
		List<BitcoinDTO> outputBitcoinDTOs = null;

		// Validate the given currency.
		validateCurrency(currency);

		// If the flag useOfflineData is set to true, then use H2 database data.
		if (useOfflineData) {

			// Fetching data from database using given inputs.
			List<BitcoinEntityDTO> outputBitEntityDTOs = this.bitcoinRepository
					.findByPriceDateBetweenAndCurrencyOrderByPriceDateAsc(startDate, endDate, currency);

			// Converting database DTOs into API response DTOs.
			if (!CollectionUtils.isEmpty(outputBitEntityDTOs)) {
				outputBitcoinDTOs = BitcoinServiceUtil.convertBitcoinEntityToResponseDTO(outputBitEntityDTOs);
			}

			// If the flag useOfflineData is set to false, then use API response of CoinDesk
			// system.
		} else {

			// Fetching data using API of CoinDesk system using given inputs.
			CoinDeskBitcoinResponse coinDeskBitcoinResponse = this.coinDeskBitcoinClient.getHistoricalBitcoinPrice(
					BitcoinServiceUtil.retrieveDateInStringFormat(startDate),
					BitcoinServiceUtil.retrieveDateInStringFormat(endDate), currency);

			// Converting database DTOs into API response DTOs.
			if (coinDeskBitcoinResponse != null && coinDeskBitcoinResponse.getBpi() != null) {
				outputBitcoinDTOs = new ArrayList<>();

				for (String key : coinDeskBitcoinResponse.getBpi().keySet()) {

					BitcoinDTO bitcoinDTO = new BitcoinDTO(BitcoinServiceUtil.retrieveDateStringInDateFormat(key),
							coinDeskBitcoinResponse.getBpi().get(key), BitcoinPriceType.NORMAL.name());

					outputBitcoinDTOs.add(bitcoinDTO);
				}
			}
		}

		// Identifying HIGH and LOW price out of bitcoin prices fetched.
		populateHighAndLowBitcoinPrices(outputBitcoinDTOs);

		if (LOG.isDebugEnabled()) {
			LOG.debug("In getBitcoinHistoryForGivenInputs - ENDED.");
		}

		return outputBitcoinDTOs;
	}

	/**
	 * This method is used to populate high and low bitcoin prices.
	 * 
	 * @param outputBitcoinDTOs
	 */
	private void populateHighAndLowBitcoinPrices(List<BitcoinDTO> outputBitcoinDTOs) {

		if (!CollectionUtils.isEmpty(outputBitcoinDTOs)) {

			double maxPrice = outputBitcoinDTOs.stream().max(Comparator.comparing(BitcoinDTO::getPrice)).get()
					.getPrice();
			double minPrice = outputBitcoinDTOs.stream().min(Comparator.comparing(BitcoinDTO::getPrice)).get()
					.getPrice();

			outputBitcoinDTOs.forEach(dto -> {
				if (dto.getPrice() == maxPrice) {
					dto.setPriceType(BitcoinPriceType.HIGH.name());
				} else if (dto.getPrice() == minPrice) {
					dto.setPriceType(BitcoinPriceType.LOW.name());
				} else {
					dto.setPriceType(BitcoinPriceType.NORMAL.name());
				}
			});
		}
	}

	@Override
	public BitcoinDTO getBitcoinPriceById(long id) {

		if (LOG.isDebugEnabled()) {
			LOG.debug(String.format("In getBitcoinPriceById - STARTED and inputs are: id - %s.", id));
		}

		BitcoinDTO outputBitcoinDTO = null;

		// Fetching data from database using given inputs.
		Optional<BitcoinEntityDTO> outputBitcoinEntityDTOOptional = this.bitcoinRepository.findById(id);

		if (outputBitcoinEntityDTOOptional.isPresent()) {

			// Converting database DTO into API response DTO.
			List<BitcoinDTO> outputBitcoinDTOs = BitcoinServiceUtil
					.convertBitcoinEntityToResponseDTO(Arrays.asList(outputBitcoinEntityDTOOptional.get()));

			outputBitcoinDTO = CollectionUtils.isEmpty(outputBitcoinDTOs) ? null : outputBitcoinDTOs.get(0);
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("In getBitcoinPriceById - ENDED.");
		}

		return outputBitcoinDTO;
	}

	/**
	 * This method is used to validate given currency.
	 * 
	 * @param currency
	 */
	private void validateCurrency(String currency) {

		if (StringUtils.isBlank(currency) || this.bitcoinCurrencyRepository.findByCurrency(currency) == null) {
			throw new BitcoinCurrencyInvalidException(String.format("%s currency is invalid.", currency));
		}
	}
}