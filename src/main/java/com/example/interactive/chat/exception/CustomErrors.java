package com.example.interactive.chat.exception;

/**
 *
 * @author Artur Korra
 */
public enum CustomErrors {
	GENERIC_ERROR(1000), USER_NOT_FOUND(1001), NICKNAME_ISUSED(1002), USER_MSG_NOT_FOUND(1003), MSG_SEEN(1004);

	private CustomErrors(int code) {
		this.code = code;
	}

	private int code;

	public int getCode() {
		return code;
	}
}
