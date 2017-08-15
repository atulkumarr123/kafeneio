package com.kafeneio.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kafeneio.DTO.LoginDTO;
import com.kafeneio.DTO.ParentChildDTO;
import com.kafeneio.DTO.RegistrationDTO;
import com.kafeneio.enums.Codes;
import com.kafeneio.enums.ResponseKeyName;
import com.kafeneio.exception.BadRequestException;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.exception.UnauthorizedException;
import com.kafeneio.model.ParentChildModel;
import com.kafeneio.model.RegistrationModel;
import com.kafeneio.security.jwt.JWTConfigurer;
import com.kafeneio.security.jwt.TokenProvider;
import com.kafeneio.service.MailService;
import com.kafeneio.service.RegistrationService;

@Controller
public class RegistrationController extends BaseRestController {

	
	@Inject
	private RegistrationService registrationService;
	/** The token provider. */
	@Inject
	private TokenProvider tokenProvider;

	/** The authentication manager. */
	@Inject
	private AuthenticationManager authenticationManager;

	/** The error properties. */
	@Autowired
	@Qualifier("errorProperties")
	private Properties errorProperties;

	@Autowired
	@Qualifier("mailService")
	private MailService mailService;

	@SuppressWarnings(value = { "" })
	@RequestMapping(value = "/register/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody ParentChildDTO parentChildDto)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		RegistrationModel registrationMode = registrationService.findOneByEmail(parentChildDto.getRegistration().getLogin().getEmail());
		if (registrationMode != null) {
			String errorCode = errorProperties.getProperty(Codes.ALREADY_EXISTS_EMAIL.getErrorCode());
			String errorMessage = MessageFormat.format(errorCode, parentChildDto.getRegistration().getLogin().getEmail());
			throw new BadRequestException(Codes.ALREADY_EXISTS_EMAIL, errorMessage);
		}
		//Long parentId=parentChildDto.getRegistration().getLogin().getEmail();
		mailService.sendEmail(parentChildDto.getRegistration());
		ParentChildModel parentChildModel= registrationService.saveUser(parentChildDto);
		if(!StringUtils.isEmpty((parentChildDto.getSponsorId()))){
			 Long Id=registrationService.findReferralUserId(parentChildDto.getSponsorId());
			 registrationService.saveDirectReferral(Id, parentChildModel);
			 registrationService.saveBinaryIncome(parentChildModel);
			 
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public ResponseEntity<Object> authenticateUser(@RequestBody LoginDTO loginDTO, HttpServletResponse response)
			throws KafeneioException {
		String userNameForAuthentication = null;
		// Checking whether the email and public account id are exists in the
		// login object or not
		if (!StringUtils.isEmpty(loginDTO.getEmail())) {
			userNameForAuthentication = loginDTO.getEmail().trim();
		}
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				userNameForAuthentication, loginDTO.getPassword());
		try {
			// Authenticating with authentication token
			Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
			ParentChildDTO registration = (ParentChildDTO) authentication.getPrincipal();
			boolean rememberMe = (loginDTO.isRememberMe() == null) ? false : loginDTO.isRememberMe();
            
			// Creating token
			String jwt = tokenProvider.createToken(authentication, rememberMe, registration.getRegistration().getLogin());

			// log.error("Token sucessfully created");

			response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);

			// log.debug("User logged successfully - " + user);
			return ResponseEntity.ok(createSuccessResponse(ResponseKeyName.USER, registration));
		} catch (AuthenticationException exception) {
			exception.printStackTrace();
			throw new UnauthorizedException(Codes.INVALID_CREDENTIALS);

		}

	}

	
	@RequestMapping(value = "/sponsorId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getSponsorName(
		      @RequestParam(value = "sponsorId", required = true) String sponsorId) throws BadRequestException {
		RegistrationDTO registration=registrationService.getSponsorName(sponsorId);
		if(registration!=null){
			 return ResponseEntity.ok(createSuccessResponse(ResponseKeyName.SPONSOR, registration));
		}
		else{
		String errorCode = errorProperties.getProperty(Codes.NOT_EXIST_SPONSOR_ID.getErrorCode());
		String errorMessage = MessageFormat.format(errorCode,sponsorId );
		throw new BadRequestException(Codes.NOT_EXIST_SPONSOR_ID, errorMessage);
		}
		
	}
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<Void> uploadProfileImg(@RequestParam(value = "userId") Long id ,@RequestParam(value = "file")MultipartFile multipartRequest) throws IOException, SerialException, SQLException{
		Blob profileImg = new SerialBlob(multipartRequest.getBytes());
		registrationService.saveProfileImage(profileImg, id);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/signUp")
	public String singUp(ModelMap modelMap)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		modelMap.put("fileName", "signUp");
		return "index";
	}


}
