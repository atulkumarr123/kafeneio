package com.kafeneio.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.MultipartConfigElement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages ="com.kafeneio")
@EnableWebMvc
public class AppConfiguration extends WebMvcConfigurerAdapter{
	
	@Bean(name = "errorProperties")
	  public Properties getErrorProperties() {
	    Properties properties = null;
	    try {
	      ClassPathResource resource = new ClassPathResource("/properties/error.properties");
	      properties = PropertiesLoaderUtils.loadProperties(resource);
	    } catch (IOException exception) {
	      //log.error("Error in loading Error properties", exception);
	    }
	    return properties;
	  }

	@Bean
	public CommonsMultipartResolver multipartResolver(){
	    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
	    commonsMultipartResolver.setDefaultEncoding("utf-8");
	    commonsMultipartResolver.setMaxUploadSize(50000000);
	    return commonsMultipartResolver;
	}
	
	//@/*Bean
	/*public MultipartConfigElement multipartConfigElement(){
	    MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
	    multipartConfigFactory.setMaxFileSize("10MB");
	    multipartConfigFactory.setMaxRequestSize("50MB");
	    return multipartConfigFactory.createMultipartConfig();
	}*/
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
	  @Bean
	    public JavaMailSender getMailSender(){
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	         
	        //Using gmail
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);
	        mailSender.setUsername("shweta.kafeneio@gmail.com");
	        mailSender.setPassword("@Arti0809");
	         
	        Properties javaMailProperties = new Properties();
	        javaMailProperties.put("mail.smtp.starttls.enable", "true");
	        javaMailProperties.put("mail.smtp.auth", "true");
	        javaMailProperties.put("mail.transport.protocol", "smtp");
	        javaMailProperties.put("mail.debug", "true");//Prints out everything on screen
	         
	        mailSender.setJavaMailProperties(javaMailProperties);
	        return mailSender;
	    }
}
