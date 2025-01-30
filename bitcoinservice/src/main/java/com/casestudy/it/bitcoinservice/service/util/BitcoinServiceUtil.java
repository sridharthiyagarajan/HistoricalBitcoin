package com.casestudy.it.bitcoinservice.service.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.casestudy.it.bitcoinservice.db.entity.BitcoinEntityDTO;
import com.casestudy.it.bitcoinservice.rest.response.BitcoinDTO;

public class BitcoinServiceUtil {

	private BitcoinServiceUtil() {

	}

	public static List<BitcoinDTO> convertBitcoinEntityToResponseDTO(List<BitcoinEntityDTO> bitcoinEntityDTOs) {

		if (CollectionUtils.isEmpty(bitcoinEntityDTOs)) {
			return null;
		}

		return bitcoinEntityDTOs.stream()
				.map(bitcoinPrice -> new BitcoinDTO(bitcoinPrice.getPriceDate(), bitcoinPrice.getPrice(), "NORMAL"))
				.collect(Collectors.toList());
	}
}
