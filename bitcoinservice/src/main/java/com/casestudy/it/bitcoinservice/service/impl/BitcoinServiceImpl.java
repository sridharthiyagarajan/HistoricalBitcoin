package com.casestudy.it.bitcoinservice.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.casestudy.it.bitcoinservice.client.coindesk.CoinDeskBitcoinClient;
import com.casestudy.it.bitcoinservice.client.coindesk.response.CoinDeskBitcoinResponse;
import com.casestudy.it.bitcoinservice.db.entity.BitcoinEntityDTO;
import com.casestudy.it.bitcoinservice.db.repository.BitcoinRepository;
import com.casestudy.it.bitcoinservice.rest.response.BitcoinDTO;
import com.casestudy.it.bitcoinservice.service.BitcoinService;
import com.casestudy.it.bitcoinservice.service.util.BitcoinPriceType;
import com.casestudy.it.bitcoinservice.service.util.BitcoinServiceUtil;

@Service
public class BitcoinServiceImpl implements BitcoinService {

	private BitcoinRepository bitcoinRepository;

	@Autowired
	private CoinDeskBitcoinClient coinDeskBitcoinClient;

	public BitcoinServiceImpl(BitcoinRepository bitcoinRepository) {
		this.bitcoinRepository = bitcoinRepository;
	}

	@Transactional(readOnly = true)
	@Cacheable("bitcoinPrices")
	public List<BitcoinDTO> getBitcoinHistoryForGivenDateRange(LocalDate startDate, LocalDate endDate, String currency,
			boolean useOfflineData) {

		List<BitcoinDTO> outputBitcoinDTOs = null;

		if (useOfflineData) {

			List<BitcoinEntityDTO> outputBitEntityDTOs = this.bitcoinRepository
					.findByPriceDateBetweenAndCurrencyOrderByPriceDateAsc(startDate, endDate, currency);

			if (!CollectionUtils.isEmpty(outputBitEntityDTOs)) {
				outputBitcoinDTOs = BitcoinServiceUtil.convertBitcoinEntityToResponseDTO(outputBitEntityDTOs);
			}

		} else {

			CoinDeskBitcoinResponse coinDeskBitcoinResponse = this.coinDeskBitcoinClient.getHistoricalBitcoinPrice(
					BitcoinServiceUtil.retrieveDateInStringFormat(startDate),
					BitcoinServiceUtil.retrieveDateInStringFormat(endDate), currency);

			if (coinDeskBitcoinResponse != null && coinDeskBitcoinResponse.getBpi() != null) {
				outputBitcoinDTOs = new ArrayList<>();

				for (String key : coinDeskBitcoinResponse.getBpi().keySet()) {
					
					BitcoinDTO bitcoinDTO = new BitcoinDTO(BitcoinServiceUtil.retrieveDateStringInDateFormat(key),
							coinDeskBitcoinResponse.getBpi().get(key), BitcoinPriceType.NORMAL.name());

					outputBitcoinDTOs.add(bitcoinDTO);
				}
			}
		}

		populateHighAndLowBitcoinPrices(outputBitcoinDTOs);

		return outputBitcoinDTOs;
	}

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

		BitcoinDTO outputBitcoinDTO = null;
		Optional<BitcoinEntityDTO> outputBitcoinEntityDTOOptional = this.bitcoinRepository.findById(id);

		if (outputBitcoinEntityDTOOptional.isPresent()) {

			List<BitcoinDTO> outputBitcoinDTOs = BitcoinServiceUtil
					.convertBitcoinEntityToResponseDTO(Arrays.asList(outputBitcoinEntityDTOOptional.get()));
			outputBitcoinDTO = CollectionUtils.isEmpty(outputBitcoinDTOs) ? null : outputBitcoinDTOs.get(0);
		}

		return outputBitcoinDTO;
	}
}