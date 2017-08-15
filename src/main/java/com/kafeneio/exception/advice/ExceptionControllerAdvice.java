package com.kafeneio.exception.advice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kafeneio.DTO.error.Error;
import com.kafeneio.exception.BadRequestException;
import com.kafeneio.exception.ObjectNotFoundException;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.exception.KafeneioSystemException;
import com.kafeneio.exception.UnauthorizedException;
import com.kafeneio.enums.Codes;

@ControllerAdvice
public class ExceptionControllerAdvice {
	  @Autowired
	  @Qualifier("errorProperties")
	  private Properties errorProperties;
	@ExceptionHandler(value = { Exception.class, KafeneioSystemException.class })
	  public ResponseEntity<Object> exceptionHandler(Exception exception) {

	    // TODO : create code and message in Properties files.
		Error  error = new Error(Codes.SYSTEM_ERROR.getErrorCode(),
	        errorProperties.getProperty(Codes.SYSTEM_ERROR.getErrorCode()));
	    HashMap<String, Object> errorResponseMap = new LinkedHashMap<String, Object>();
	       errorResponseMap.put("Error", error);
	  //.error("System exception occured while processing request", exception);
	    return new ResponseEntity<Object>(errorResponseMap, HttpStatus.INTERNAL_SERVER_ERROR);

	  }

	  /**
	   * This method handles application level exceptions. When throw DppmException, caller must set error code.
	   *
	   * @param exception
	   *          the exception
	   * @return the response entity
	   */
	  @ExceptionHandler(KafeneioException.class)
	  public ResponseEntity<Object> kafeneioExceptionHandler(KafeneioException exception) {
	    Error error = null;

	    if (exception.getCode() == null) {
	      //log.error("******Error code must not be null, Please fix it now.*******");
	    } else {
	      error = new Error(exception.getCode().getErrorCode(),
	          errorProperties.getProperty(exception.getCode().getErrorCode()));
	    }
	    HashMap<String, Object> errorResponseMap = new LinkedHashMap<String, Object>();
	    errorResponseMap.put("Error", error);
	   // log.error(exception.getCode().getErrorCode(), exception);
	    return new ResponseEntity<Object>(errorResponseMap, HttpStatus.OK);

	  }

	  /**
	   * This method handles object not found exceptions. When throw ObjectNotFoundException, caller must set error code.
	   *
	   * @param exception
	   *          the exception
	   * @return the response entity
	   */
	  @ExceptionHandler(ObjectNotFoundException.class)
	  public ResponseEntity<Object> objectNotFoundExceptionHandler(ObjectNotFoundException exception) {
	    Error error = null;

	    if (exception.getCode() == null) {
	      //log.error("******Error code must not be null, Please fix it now.*******");
	    } else {
	      error = new Error(exception.getCode().getErrorCode(),
	          errorProperties.getProperty(exception.getCode().getErrorCode()));
	    }
	    HashMap<String, Object> errorResponseMap = new LinkedHashMap<String, Object>();
	    errorResponseMap.put("Error", error);
	    //log.error(exception.getCode().getErrorCode(), exception);
	    return new ResponseEntity<Object>(errorResponseMap, HttpStatus.NOT_FOUND);

	  }

	  /**
	   * This method handles bad request exceptions. When throw BadRequestException, caller must set error code.
	   *
	   * @param exception
	   *          the exception
	   * @return the response entity
	   */
	  @ExceptionHandler(BadRequestException.class)
	  public ResponseEntity<Object> BadRequestExceptionHandler(BadRequestException exception) {
	    Error error = null;

	    if (exception.getCode() == null) {
	     // log.error("******Error code must not be null, Please fix it now.*******");
	    }
	    
	    else if(!StringUtils.isEmpty(exception.getMessage())){
	      error = new Error(exception.getCode().getErrorCode(),
	          exception.getMessage());
	    }
	    else {
	      error = new Error(exception.getCode().getErrorCode(),
	          errorProperties.getProperty(exception.getCode().getErrorCode()));
	    }
	    HashMap<String, Object> errorResponseMap = new LinkedHashMap<String, Object>();
	    errorResponseMap.put("Error", error);
	    //log.error(exception.getCode().getErrorCode(), exception);
	    return new ResponseEntity<Object>(errorResponseMap, HttpStatus.BAD_REQUEST);

	  }

	  /**
	   * This method handles unauthorized exceptions. When throw UnauthorizedException, caller must set error code.
	   *
	   * @param exception
	   *          the exception
	   * @return the response entity
	   */
	  @ExceptionHandler(UnauthorizedException.class)
	  public ResponseEntity<Object> UnauthorizedExceptionHandler(UnauthorizedException exception) {
	    Error error = null;

	    if (exception.getCode() == null) {
	      //log.error("******Error code must not be null, Please fix it now.*******");
	    } else {
	      error = new Error(exception.getCode().getErrorCode(),
	          errorProperties.getProperty(exception.getCode().getErrorCode()));
	    }
	    HashMap<String, Object> errorResponseMap = new LinkedHashMap<String, Object>();
	    errorResponseMap.put("Error", error);
	   // log.error(exception.getCode().getErrorCode(), exception);
	    return new ResponseEntity<Object>(errorResponseMap, HttpStatus.UNAUTHORIZED);

	  }

}
