

package com.kafeneio.exception;

import com.kafeneio.enums.Codes;

/**
 * 
 * This is object not found exception class is subclass of checked exception. This exception classes is to notify the
 * caller about the object that the calling is search for is not found in application . Custom field like error code has
 * been added to this class for tracking purpose.
 * 
 * 
 *
 */
public class ObjectNotFoundException extends KafeneioException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The code. */
  private Codes code;

  /**
   * 
   * The Java compiler automatically inserts a call to the no-argument constructor of the superclass.
   * 
   */
  public ObjectNotFoundException() {

  }

  /**
   * Constructor new exception with with error code.
   *
   * @param errorCode
   *          the error code
   */
  public ObjectNotFoundException(Codes errorCode) {
    this.code = errorCode;
  }

  /**
   * Constructs a new exception with the error code and specified detail message .
   *
   * @param code
   *          the code
   * @param message
   *          the message
   */
  public ObjectNotFoundException(Codes code, String message) {
    super(message);
    this.code = code;

  }

  /**
   * Instantiates a new object not found exception.
   *
   * @param cause
   *          the cause
   */
  public ObjectNotFoundException(Throwable cause) {
    super(cause);

  }

  /**
   * Constructs a new object not found exception with the specified detail message and cause.
   *
   * @param message
   *          the message
   * @param cause
   *          the cause
   */
  public ObjectNotFoundException(String message, Throwable cause) {
    super(message, cause);

  }

  /**
   * Constructs a new object not found exception with the error code , specified detail message and cause.
   *
   * @param code
   *          the code
   * @param message
   *          the message
   * @param cause
   *          the cause
   */
  public ObjectNotFoundException(Codes code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;

  }

  /**
   * Constructs a new exception with the specified detail message, cause, suppression enabled or disabled, and writable
   * stack trace enabled or disabled.
   *
   * @param message
   *          the message
   * @param cause
   *          the cause
   * @param enableSuppression
   *          the enable suppression
   * @param writableStackTrace
   *          the writable stack trace
   */
  public ObjectNotFoundException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);

  }

  /**
   * Return error code to caller.
   * 
   * @return the errorCode
   */
  public Codes getCode() {
    return code;
  }
}
