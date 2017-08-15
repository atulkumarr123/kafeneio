

package com.kafeneio.security;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kafeneio.DTO.LoginDTO;
import com.kafeneio.DTO.ParentChildDTO;
import com.kafeneio.DTO.RegistrationDTO;
import com.kafeneio.model.ParentChildModel;
import com.kafeneio.model.RegistrationModel;
import com.kafeneio.service.ParentChildService;
import com.kafeneio.service.RegistrationService;
import com.kafeneio.enums.AppConstant;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

  /** The log. */
  private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

  /** The user repository. */
  @Inject
  private ParentChildService parentChildService;

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
   */
  @Override
  @Transactional
  public UserDetails loadUserByUsername(final String userNameForAuthentication) {
    //log.debug("Authenticating {}", userNameForAuthentication);
	  
     ParentChildDTO  user=null;
    //Get userNameForAuthentication as the format of email_accountid
    String[] tokens = StringUtils.split(userNameForAuthentication, "_");

    String lowercaseEmailLogin = tokens[0];
   // String publicAccountId = tokens[1];

    //Load user profile from database using account id and email
    ParentChildModel userProfile = parentChildService.findOneByEmail(lowercaseEmailLogin);

   // com.doprocess.dppm.web.api.dto.User user = null;
    if (userProfile != null) {
      
      log.debug("User profile found from database {}" + userNameForAuthentication);
      
      List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

      
      grantedAuthorities.add(new SimpleGrantedAuthority(userProfile.getRegistration().getLoginModel().getRole()));

      //Map user account profile from Database into user dto
     user = new ParentChildDTO();
      
      //user.setPasswordSalt(userProfile.getUser().getPasswordSalt());
     /* user.setPassword(userProfile.getLoginModel().getPassword());
      user.setUsername(userProfile.getUser().getEmail());
      user.setAuthorities(grantedAuthorities);
      user.setCustomerAccountName(userProfile.getCustomerAccount().getName());
      user.setPublicAccountId(userProfile.getCustomerAccount().getPublicAccountId());*/
      BeanUtils.copyProperties(userProfile, user);
      LoginDTO loginDTO = new LoginDTO();
      loginDTO.setEmail(userProfile.getRegistration().getLoginModel().getEmail());
      loginDTO.setPassword(userProfile.getRegistration().getLoginModel().getPassword());
      loginDTO.setRole(userProfile.getRegistration().getLoginModel().getRole());
      
      RegistrationDTO registrationDTO=new RegistrationDTO();
      
      if(userProfile.getRegistration().getLoginModel().getRole().equals(AppConstant.ROLE_TYPE_USER.getStringValue()))
      {
      registrationDTO.setId(userProfile.getRegistration().getId());
      registrationDTO.setName(userProfile.getRegistration().getName());
      registrationDTO.setSponsorId(userProfile.getRegistration().getSponsorId());
      if(userProfile.getRegistration().getProfileImage()!=null)
      {
     
		try {
			registrationDTO.setProfileImage(userProfile.getRegistration().getProfileImage().getBytes(1, (int) userProfile.getRegistration().getProfileImage().length()));
		} catch (SQLException e) {
			
		}
	
      }
      
      else
      {
    	  registrationDTO.setProfileImage("".getBytes());  
      }
    }
      registrationDTO.setLogin(loginDTO);
      user.setRegistration(registrationDTO);
      
    } else {
      log.error("Userprofile not found");
      throw new UsernameNotFoundException("User " + lowercaseEmailLogin + " was not found in the " + "database");
    }
  
    return  user;
  }


}
