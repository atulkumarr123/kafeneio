

package com.kafeneio.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.kafeneio.utils.SecurityUtils;


/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.data.domain.AuditorAware#getCurrentAuditor()
   */
  @Override
  public String getCurrentAuditor() {
    // Get the current user email based on the login user profile
    String userName = SecurityUtils.getCurrentUserLogin();
    return userName != null ? userName : "system";
  }
}
