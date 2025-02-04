package com.casestudy.it.bitcoinservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Bitcoin service application.
 * 
 * @author SridharThiyagarajan
 *
 */
@SpringBootApplication
@EnableCaching
public class BitcoinServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitcoinServiceApplication.class, args);
	}

}
