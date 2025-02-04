package com.casestudy.it.bitcoinservice.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration.
 * 
 * 
 * @author SridharThiyagarajan
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	/**
	 * This method is used to return message source reference.
	 * 
	 * @return MessageSource
	 */
	@Bean
	public MessageSource messageSource() {

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasenames("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");

		return messageSource;
	}

	/**
	 * This method is used to add CORS mappings.
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**").allowedOrigins("http://localhost:3000")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS").allowedHeaders("*")
				.allowCredentials(true).maxAge(3600);
	}
}