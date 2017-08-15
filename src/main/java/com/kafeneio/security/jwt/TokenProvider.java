
package com.kafeneio.security.jwt;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.kafeneio.DTO.LoginDTO;
import com.kafeneio.DTO.RegistrationDTO;
import com.kafeneio.model.RegistrationModel;
import com.kafeneio.service.RegistrationService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
* <h1>The Class Token Provider</h1>
* This Token Provider class generates the JWT token based on SignatureAlgorithm and a secret key
* <p>
* <b>Note:</b> the Expiration is also set into the token
* 
*
*/
@Component
public class TokenProvider {

	  /** The log. */
	  private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

	  /** The Constant AUTHORITIES_KEY. */
	  private static final String AUTHORITIES_KEY = "auth";

	  /** The token validity in seconds. */
	  private long tokenValidityInSeconds;

	  /** The token validity in seconds for remember me. */
	  private long tokenValidityInSecondsForRememberMe;

	  /** The secret key. */
	  @Value("${jwt.secret}")
	  private String secretKey;

	  /** The user repository. */
	  @Inject
	  private RegistrationService registrationService;

	  /**
	   * Creates the token.
	   *
	   * @param authentication
	   *          the authentication
	   * @param rememberMe
	   *          the remember me
	   * @return the string
	   */
	  public String createToken(Authentication authentication, Boolean rememberMe) {
	    this.tokenValidityInSeconds = 1000L * 86400;
	    this.tokenValidityInSecondsForRememberMe = 1000L * 2592000;

	    String authorities = authentication.getAuthorities().stream().map(authority -> authority.getAuthority())
	        .collect(Collectors.joining(","));

	    long now = new Date().getTime();
	    Date validity;
	    if (rememberMe) {
	      validity = new Date(now + this.tokenValidityInSecondsForRememberMe);
	    } else {
	      validity = new Date(now + this.tokenValidityInSeconds);
	    }

	    return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities)
	        .signWith(SignatureAlgorithm.HS512, secretKey).setExpiration(validity).compact();
	  }

	  /**
	   * Creates the token.
	   *
	   * @param authentication
	   *          the authentication
	   * @param rememberMe
	   *          the remember me
	   * @param user
	   *          the user
	   * @return the string
	   * @throws DppmException
	   *           the dppm exception
	   */
	  public String createToken(Authentication authentication, Boolean rememberMe, LoginDTO user)  {   

	    this.tokenValidityInSeconds = 1000L * 86400;
	    this.tokenValidityInSecondsForRememberMe = 1000L * 2592000;
	   
	    long now = new Date().getTime();
	    Date validity;
	    if (rememberMe) {
	      validity = new Date(now + this.tokenValidityInSecondsForRememberMe);
	    } else {
	      validity = new Date(now + this.tokenValidityInSeconds);
	    }

	    Map<String, Object> claim = new HashMap<String, Object>();
	    claim.put("User", user);
	    return Jwts.builder().setSubject(authentication.getName()).setClaims(claim)
	        .signWith(SignatureAlgorithm.HS512, secretKey).setExpiration(validity).compact();

	  }

	  /**
	   * Gets the authentication.
	   *
	   * @param token
	   *          the token
	   * @return the authentication
	   */
	  public Authentication getAuthentication(String token) {
	    Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();   

	    HashMap userMap = (HashMap) claims.get("User");

	    RegistrationModel userProfile = registrationService.findOneByEmail((String) userMap.get("email"));

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

	    // TODO: currently hardcoded ROLE_ADMIN for all user
	    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	    return new UsernamePasswordAuthenticationToken(userProfile, "", grantedAuthorities);
	  }

	  /**
	   * Validate token.
	   *
	   * @param authToken
	   *          the auth token
	   * @return true, if successful
	   */
	  public boolean validateToken(String authToken) {
	    try {
	      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
	      return true;
	    } catch (SignatureException e) {
	      log.info("Invalid JWT signature: " + e.getMessage());
	      return false;
	    }
	  }
}
