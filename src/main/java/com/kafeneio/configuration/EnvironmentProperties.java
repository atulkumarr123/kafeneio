package com.kafeneio.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
@Configuration
@PropertySources({
@PropertySource(value = "classpath:properties/messages_en.properties", ignoreResourceNotFound=true),
@PropertySource(value = "classpath:properties/applicationLabels_en.properties", ignoreResourceNotFound=true)})
public class EnvironmentProperties {

	@Autowired
	private Environment environment;	   

	@Bean
	public LocaleResolver localeResolver() {
		FixedLocaleResolver localeResolver = new FixedLocaleResolver();
		localeResolver.setDefaultLocale(new Locale(environment.getProperty("system.default.language")));
		return localeResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("properties/messages","properties/applicationLabels");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
