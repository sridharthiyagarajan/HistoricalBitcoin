package com.casestudy.it.bitcoinservice.service.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.casestudy.it.bitcoinservice.db.entity.BitcoinEntityDTO;
import com.casestudy.it.bitcoinservice.rest.response.BitcoinDTO;

/**
 * Bitcoin service utility.
 * 
 * @author SridharThiyagarajan
 *
 */
public class BitcoinServiceUtil {

	/**
	 * Private constructor.
	 */
	private BitcoinServiceUtil() {
	}

	private static final String DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * This method is used to convert bitcoin entity to response DTO.
	 * 
	 * @param bitcoinEntityDTOs
	 * @return List<BitcoinDTO>
	 */
	public static List<BitcoinDTO> convertBitcoinEntityToResponseDTO(List<BitcoinEntityDTO> bitcoinEntityDTOs) {

		if (CollectionUtils.isEmpty(bitcoinEntityDTOs)) {
			return null;
		}

		return bitcoinEntityDTOs.stream().map(bitcoinPrice -> new BitcoinDTO(bitcoinPrice.getPriceDate(),
				bitcoinPrice.getPrice(), BitcoinPriceType.NORMAL.name())).collect(Collectors.toList());
	}

	/**
	 * This method is used to retrieve date in string format.
	 * 
	 * @param inputDate
	 * @return String
	 */
	public static String retrieveDateInStringFormat(LocalDate inputDate) {

		String outputStrDate = null;

		if (inputDate != null) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
			outputStrDate = inputDate.format(formatter);
		}

		return outputStrDate;
	}

	/**
	 * This method is used to retrieve date string to date.
	 * 
	 * @param inputStrDate
	 * @return LocalDate
	 */
	public static LocalDate retrieveDateStringInDateFormat(String inputStrDate) {

		LocalDate outputLocalDate = null;

		if (inputStrDate != null && !inputStrDate.isEmpty()) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

			outputLocalDate = LocalDate.parse(inputStrDate, formatter);
		}

		return outputLocalDate;
	}
}
