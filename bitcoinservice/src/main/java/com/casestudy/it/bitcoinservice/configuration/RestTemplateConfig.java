package com.casestudy.it.bitcoinservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Rest template configuration.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
@Configuration
public class RestTemplateConfig {

	/**
	 * This method is used to return rest template.
	 * 
	 * @return RestTemplate
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
