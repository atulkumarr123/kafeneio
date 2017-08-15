

package com.kafeneio.security.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
* <h1>The JWT Configurer</h1>
* This JWT Configurer Instantiates new JWT configurer handles the authorization header.
* <p>
* <b>Note:</b> JWT token will be stored into header "Authorization"
* 
* @author vsheel95
*/
public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  /** The Constant AUTHORIZATION_HEADER. */
  public static final String AUTHORIZATION_HEADER = "Authorization";

  /** The token provider. */
  private TokenProvider tokenProvider;

  /**
   * Instantiates a new JWT configurer.
   *
   * @param tokenProvider
   *          the token provider
   */
  public JWTConfigurer(TokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.security.config.annotation.SecurityConfigurerAdapter#configure(org.springframework.security.
   * config.annotation.SecurityBuilder)
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    JWTFilter customFilter = new JWTFilter(tokenProvider);
    http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
