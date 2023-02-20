package com.dsm.kdr_backend.global.jwt.exception;

import com.dsm.kdr_backend.global.exception.BaseException;
import com.dsm.kdr_backend.global.jwt.exception.error.TokenErrorCode;

public class NotAccessTokenException extends BaseException {

	public final static NotAccessTokenException EXCEPTION = new NotAccessTokenException();

	public NotAccessTokenException() {
		super(TokenErrorCode.NOT_ACCESS_TOKEN);
	}

}
