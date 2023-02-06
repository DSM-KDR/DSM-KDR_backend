package com.dsm.kdr_backend.global.jwt.exception;

import com.dsm.kdr_backend.global.exception.BaseException;
import com.dsm.kdr_backend.global.jwt.exception.error.TokenErrorCode;

public class NotRefreshTokenException extends BaseException {

	public final static NotRefreshTokenException EXCEPTION = new NotRefreshTokenException();

	public NotRefreshTokenException() {
		super(TokenErrorCode.NOT_REFRESH_TOKEN);
	}
}
