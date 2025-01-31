package com.casestudy.it.bitcoinservice.service.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	public static String retrieveDateInStringFormat(LocalDate inputDate) {

		String outputStrDate = null;

		if (inputDate != null) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			outputStrDate = inputDate.format(formatter);
		}

		return outputStrDate;
	}

	public static LocalDate retrieveDateStringInDateFormat(String inputStrDate) {

		LocalDate outputLocalDate = null;

		if (inputStrDate != null && !inputStrDate.isEmpty()) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			outputLocalDate = LocalDate.parse(inputStrDate, formatter);
		}

		return outputLocalDate;
	}
}
