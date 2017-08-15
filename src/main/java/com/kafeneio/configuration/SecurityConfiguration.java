package com.kafeneio.configuration;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kafeneio.security.Http401UnauthorizedEntryPoint;
import com.kafeneio.security.jwt.JWTConfigurer;
import com.kafeneio.security.jwt.TokenProvider;





/**
 * <h1>SecurityConfiguration
 * <h1>This class is responsible to configure web application security
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	/** The authentication entry point. */
	  @Inject
	  private Http401UnauthorizedEntryPoint authenticationEntryPoint;
	 @Inject
	  private UserDetailsService userDetailsService;
	 @Inject
	  private TokenProvider tokenProvider;
	
	 @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }

	  /*
	   * (non-Javadoc)
	   * 
	   * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#
	   * authenticationManagerBean()
	   */
	  @Bean
	  @Override
	  public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	  }
	  
	  @Inject
	  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	   // ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);

	    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

	   //ReflectionSaltSource saltsource = new ReflectionSaltSource();

	  // saltsource.setUserPropertyToUse("passwordSalt");

	   // daoAuthenticationProvider.setSaltSource(saltsource);

	    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

	    daoAuthenticationProvider.setUserDetailsService(userDetailsService);

	    auth.authenticationProvider(daoAuthenticationProvider);

	  }
	 
	 /*
	   * (non-Javadoc)
	   * 
	   * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.
	   * springframework.security.config.annotation.web.builders.HttpSecurity)
	   */
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and().csrf().disable().headers()
	         .frameOptions().disable().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	         .authorizeRequests().antMatchers("login/").permitAll()
	         .antMatchers("signUp/").permitAll()
	        
	        .antMatchers("/protected/**").authenticated();
	  }
	  
	  private JWTConfigurer securityConfigurerAdapter() {
		    return new JWTConfigurer(tokenProvider);
		  }

		  /**
		   * Security evaluation context extension.
		   *
		   * @return the security evaluation context extension
		   */
		/*  @Bean
		  public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
		    return new SecurityEvaluationContextExtension();
		  }*/
}
