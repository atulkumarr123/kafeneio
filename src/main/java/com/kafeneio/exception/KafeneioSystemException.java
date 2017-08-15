package com.kafeneio.exception;

public class KafeneioSystemException extends RuntimeException {


	  /** The Constant serialVersionUID. */
	  private static final long serialVersionUID = 1L;

	  /**
	   * Instantiates a new dppm system exception.
	   */
	  public KafeneioSystemException() {

	  }

	  /**
	   * Instantiates a new dppm system exception.
	   *
	   * @param message
	   *          the message
	   */
	  public KafeneioSystemException(String message) {
	    super(message);

	  }

	  /**
	   * Instantiates a new dppm system exception.
	   *
	   * @param cause
	   *          the cause
	   */
	  public KafeneioSystemException(Throwable cause) {
	    super(cause);

	  }

	  
	  public KafeneioSystemException(String message, Throwable cause) {
	    super(message, cause);

	  }

	 
	  public KafeneioSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);

	  }


}
