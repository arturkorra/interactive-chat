package com.example.interactive.chat.exception;

/**
 *
 * @author Artur Korra
 */
public class ApiServiceException extends Exception {

	private static final long serialVersionUID = -4067958352111999594L;
	private final Integer code;

	public ApiServiceException(Exception e) {
		super(e);
		this.code = CustomErrors.GENERIC_ERROR.getCode();
	}

	public ApiServiceException(Integer code) {
		super();
		this.code = code;
	}

	public ApiServiceException(String message, Integer code) {
		super(message);
		this.code = code;
	}

	public ApiServiceException(Throwable cause, Integer code) {
		super(cause);
		this.code = code;
	}

	public Integer getCode() {
		return this.code;
	}

}
