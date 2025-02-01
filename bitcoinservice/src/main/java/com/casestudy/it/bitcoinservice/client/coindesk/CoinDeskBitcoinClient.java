package com.casestudy.it.bitcoinservice.client.coindesk;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.casestudy.it.bitcoinservice.client.coindesk.response.CoinDeskBitcoinCurrencyResponse;
import com.casestudy.it.bitcoinservice.client.coindesk.response.CoinDeskBitcoinResponse;
import com.casestudy.it.bitcoinservice.db.entity.BitcoinCurrencyEntityDTO;
import com.casestudy.it.bitcoinservice.db.repository.BitcoinCurrencyRepository;
import com.casestudy.it.bitcoinservice.exception.BitcoinSystemException;
import com.casestudy.it.bitcoinservice.service.util.BitcoinServiceUtil;
import com.casestudy.it.bitcoinservice.util.BitcoinConstants;

import jakarta.annotation.PostConstruct;

/**
 * Rest client used to get data from coin desk APIs.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
@Service
public class CoinDeskBitcoinClient {

	/**
	 * Logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(CoinDeskBitcoinClient.class);

	/**
	 * Rest template reference.
	 */
	private final RestTemplate restTemplate;

	/**
	 * Bitcoin currency repository reference.
	 */
	private BitcoinCurrencyRepository bitcoinCurrencyRepository;

	/**
	 * Constructor
	 * 
	 * @param restTemplate
	 * @param bitcoinCurrencyRepository
	 */
	public CoinDeskBitcoinClient(RestTemplate restTemplate, BitcoinCurrencyRepository bitcoinCurrencyRepository) {
		this.restTemplate = restTemplate;
		this.bitcoinCurrencyRepository = bitcoinCurrencyRepository;
	}

	/**
	 * This method is used to get historical bitcoin price using coin desk API.
	 * 
	 * @param startDate
	 * @param endDate
	 * @param currency
	 * @return CoinDeskBitcoinResponse
	 */
	@SuppressWarnings("deprecation")
	public CoinDeskBitcoinResponse getHistoricalBitcoinPrice(String startDate, String endDate, String currency) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("In getHistoricalBitcoinPrice() - STARTED.");
		}

		if (LOG.isInfoEnabled()) {
			LOG.info(String.format(
					"In getHistoricalBitcoinPrice() - inputs are: startDate - %s, endDate - %s, currency - %s",
					startDate, endDate, currency));
		}

		String url = UriComponentsBuilder.fromHttpUrl("https://api.coindesk.com/v1/bpi/historical/close.json")
				.queryParam("start", startDate).queryParam("end", endDate).queryParam("currency", currency)
				.toUriString();

		if (LOG.isDebugEnabled()) {
			LOG.debug("In getHistoricalBitcoinPrice() - ENDED.");
		}

		return restTemplate.getForObject(url, CoinDeskBitcoinResponse.class);
	}

	/**
	 * This method is used to fetch supported currencies on startup using coin desk
	 * currency API.
	 */
	@PostConstruct
	@Transactional
	public void fetchSupportedCurrenciesOnStartup() {

		if (LOG.isDebugEnabled()) {
			LOG.debug("In fetchSupportedCurrenciesOnStartup() - STARTED.");
		}

		try {

			if (CollectionUtils.isEmpty(this.bitcoinCurrencyRepository.findAll())) {

				ResponseEntity<CoinDeskBitcoinCurrencyResponse[]> response = restTemplate.getForEntity(
						"https://api.coindesk.com/v1/bpi/supported-currencies.json",
						CoinDeskBitcoinCurrencyResponse[].class);

				CoinDeskBitcoinCurrencyResponse[] supportedCurrencies = response.getBody();

				// Looping through obtained currency information from API.
				if (supportedCurrencies != null && supportedCurrencies.length > 0) {

					for (CoinDeskBitcoinCurrencyResponse supportedCurrency : supportedCurrencies) {

						BitcoinCurrencyEntityDTO bitcoinCurrencyEntityDTOToBeUpdated = null;
						BitcoinCurrencyEntityDTO existingEntity = this.bitcoinCurrencyRepository
								.findByCurrencyAndCountry(supportedCurrency.getCurrency(),
										supportedCurrency.getCountry());

						LOG.info("Exisiting currency --> " + existingEntity);

						if (existingEntity != null) {

							existingEntity
									.setModifiedBy(BitcoinServiceUtil.retrieveDateInStringFormat(LocalDate.now()));
							bitcoinCurrencyEntityDTOToBeUpdated = existingEntity;

							LOG.info("Updating exisiting currency --> " + existingEntity);

						} else {

							BitcoinCurrencyEntityDTO bitcoinCurrencyEntityDTO = new BitcoinCurrencyEntityDTO(
									supportedCurrency.getCurrency(), supportedCurrency.getCountry());

							LocalDate currentDate = LocalDate.now();
							bitcoinCurrencyEntityDTO.setCreatedBy(BitcoinConstants.SYSTEM_USER);
							bitcoinCurrencyEntityDTO.setModifiedBy(BitcoinConstants.SYSTEM_USER);
							bitcoinCurrencyEntityDTO.setCreatedDate(currentDate);
							bitcoinCurrencyEntityDTO.setModifiedDate(currentDate);

							bitcoinCurrencyEntityDTOToBeUpdated = bitcoinCurrencyEntityDTO;

							LOG.info("Adding new currency --> " + bitcoinCurrencyEntityDTO);
						}

						this.bitcoinCurrencyRepository.save(bitcoinCurrencyEntityDTOToBeUpdated);
						LOG.info("Saved currency successfully --> " + bitcoinCurrencyEntityDTOToBeUpdated);
					}
				} else {
					LOG.error("There are no currencies information found.");
				}
			}

		} catch (Exception e) {

			String errorMsg = String.format("Error while fetching data from CoinDesk API: %s", e.getMessage());

			LOG.error(errorMsg, e);
			throw new BitcoinSystemException(errorMsg);

		} finally {

			if (LOG.isDebugEnabled()) {
				LOG.debug("In fetchSupportedCurrenciesOnStartup() - ENDED.");
			}
		}

	}
}