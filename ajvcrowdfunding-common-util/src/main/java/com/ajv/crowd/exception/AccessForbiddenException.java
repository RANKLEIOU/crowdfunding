package com.ajv.crowd.exception;

/**
 * 登录验证异常
 */
public class AccessForbiddenException extends RuntimeException{

	private static final long serialVersionUID = -1134096031051796575L;

	public AccessForbiddenException() {
		super();
	}

	public AccessForbiddenException(String message) {
		super(message);
	}

	public AccessForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccessForbiddenException(Throwable cause) {
		super(cause);
	}

	protected AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
