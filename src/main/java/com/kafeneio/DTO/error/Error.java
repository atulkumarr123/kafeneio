

package com.kafeneio.DTO.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for transfering error message with a list of field errors.
 */
public class Error implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The error code. */
  private String errorCode;

  /** The message. */
  private final String message;

  /** The field errors. */
  private List<FieldError> fieldErrors;

  /**
   * Instantiates a new error dto.
   *
   * @param errorCode
   *          the error code
   */
  public Error(String errorCode) {
    this(errorCode, null);
  }

  /**
   * Instantiates a new error with error code and message .
   *
   * @param errorCode
   *          the errorCode
   * @param message
   *          the message
   */
  public Error(String errorCode, String message) {
    this.message = message;
    this.errorCode = errorCode;
  }

  /**
   * Gets the error object.
   *
   * @param errorCode
   *          the error code
   * @param message
   *          the message
   * @return the error object
   */
  public static Error getErrorObject(String errorCode, String message) {
    return new Error(errorCode, message);
  }

  /**
   * Instantiates a new error with error code, message and fieldErrors.
   *
   * @param errorCode
   *          the error code
   * @param message
   *          the message
   * @param fieldErrors
   *          the field errors
   */
  public Error(String errorCode, String message, List<FieldError> fieldErrors) {
    this.errorCode = errorCode;
    this.message = message;
    this.fieldErrors = fieldErrors;
  }

  /**
   * Adds the.
   *
   * @param objectName
   *          the object name
   * @param field
   *          the field
   * @param message
   *          the message
   */
  public void add(String objectName, String field, String message) {
    if (fieldErrors == null) {
      fieldErrors = new ArrayList<>();
    }
    fieldErrors.add(new FieldError(objectName, field, message));
  }

  /**
   * Gets the message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Gets the field errors.
   *
   * @return the field errors
   */
  public List<FieldError> getFieldErrors() {
    return fieldErrors;
  }

  /**
   * Gets the error code.
   *
   * @return the errorCode.
   */
  public String getErrorCode() {
    return errorCode;
  }

}
