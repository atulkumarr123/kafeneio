

package com.kafeneio.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;



import io.jsonwebtoken.ExpiredJwtException;




/**
* <h1>The JWT Filter</h1>
* This JWT filter class Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is
* found.
* <p>
* <b>Note:</b> JWT token will be stored into header "Authorization"
* 
* @author vsheel95
*/
public class JWTFilter extends GenericFilterBean {


	  /** The log. */
	  private final Logger log = LoggerFactory.getLogger(JWTFilter.class);

	  /** The token provider. */
	  private TokenProvider tokenProvider;

	  /**
	   * Instantiates a new JWT filter.
	   *
	   * @param tokenProvider
	   *          the token provider
	   */
	  public JWTFilter(TokenProvider tokenProvider) {
	    this.tokenProvider = tokenProvider;
	  }

	  /*
	   * (non-Javadoc)
	   * 
	   * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
	   * javax.servlet.FilterChain)
	   */
	  @Override
	  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
	      throws IOException, ServletException {
	    try {
	      HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
	      String jwt = resolveToken(httpServletRequest);
	      if (StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)) {

	        Authentication authentication = this.tokenProvider.getAuthentication(jwt);
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	      }
	      filterChain.doFilter(servletRequest, servletResponse);
	    } catch (ExpiredJwtException eje) {

	      //log.info("Security exception for user {} - {}", eje.getClaims().getSubject(), eje.getMessage());
	      ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    }
	  }

	  /**
	   * Resolve token.
	   * Whenever the user wants to access a protected route or resource, 
	   * the user agent send the Json Web Tokens in the "Authorization" header using the Bearer scheme. 
	   *
	   * @param request
	   *          the request
	   * @return the jwt value without bearer prefix
	   */
	  private String resolveToken(HttpServletRequest request) {
	    String bearerToken = request.getHeader(JWTConfigurer.AUTHORIZATION_HEADER);
	    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
	      String jwt = bearerToken.substring(7, bearerToken.length());
	      return jwt;
	    }

	    return null;
	  }

}
