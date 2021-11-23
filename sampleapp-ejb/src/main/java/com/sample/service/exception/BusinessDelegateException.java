package com.sample.service.exception;

/**
 * BusinessDelegateException
 */
public class BusinessDelegateException extends RuntimeException {

	public BusinessDelegateException() {
		super();
	}

	public BusinessDelegateException(String message) {
		super(message);
	}

	public BusinessDelegateException(Throwable cause) {
		super(cause);
	}

	public BusinessDelegateException(String message, Throwable cause) {
		super(message, cause);
	}

}
