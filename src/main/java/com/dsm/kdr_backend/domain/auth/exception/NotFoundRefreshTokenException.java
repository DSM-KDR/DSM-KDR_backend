package com.dsm.kdr_backend.domain.auth.exception;

import com.dsm.kdr_backend.domain.auth.exception.error.AuthErrorCode;
import com.dsm.kdr_backend.global.exception.BaseException;

public class NotFoundRefreshTokenException extends BaseException {

	public final static NotFoundRefreshTokenException EXCEPTION = new NotFoundRefreshTokenException();

	public NotFoundRefreshTokenException() {
		super(AuthErrorCode.NOT_FOUND_REFRESH_TOKEN);
	}
}
