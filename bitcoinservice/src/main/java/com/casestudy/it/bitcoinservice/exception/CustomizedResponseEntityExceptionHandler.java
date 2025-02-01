package com.casestudy.it.bitcoinservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Customised response entity exception handler.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * This method is used to handle all exceptions.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * This method is used to handle bitcoin price not found exception.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(BitcoinPriceNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleBitcoinPriceNotFoundException(Exception ex, WebRequest request)
			throws Exception {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	/**
	 * This method is used to handle bitcoin currency invalid exception.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(BitcoinCurrencyInvalidException.class)
	public final ResponseEntity<ErrorDetails> handleBitcoinCurrencyInvalidException(Exception ex, WebRequest request)
			throws Exception {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method is used to handle bitcoin system exception.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(BitcoinSystemException.class)
	public final ResponseEntity<ErrorDetails> handleBitcoinSystemException(Exception ex, WebRequest request)
			throws Exception {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * This method is used to validate method arguments.
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
