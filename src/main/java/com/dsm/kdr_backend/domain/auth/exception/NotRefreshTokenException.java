package com.dsm.kdr_backend.domain.auth.exception;

import com.dsm.kdr_backend.domain.auth.exception.error.AuthErrorCode;
import com.dsm.kdr_backend.global.exception.BaseException;

public class NotRefreshTokenException extends BaseException {

	public final static NotRefreshTokenException EXCEPTION = new NotRefreshTokenException();

	public NotRefreshTokenException() {
		super(AuthErrorCode.NOT_REFRESH_TOKEN);
	}
}
