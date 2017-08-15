package com.kafeneio.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * <h1>SpringSecurityInitializer.</h1>
 *SecurityInitializer creates the DelegatingFilterProxy which is used to look up a bean by the name of springSecurityFilterChain
 *
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
