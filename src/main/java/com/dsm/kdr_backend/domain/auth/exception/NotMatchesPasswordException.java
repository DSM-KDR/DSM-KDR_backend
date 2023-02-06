package com.dsm.kdr_backend.domain.auth.exception;

import com.dsm.kdr_backend.domain.auth.exception.error.AuthErrorCode;
import com.dsm.kdr_backend.global.exception.BaseException;

public class NotMatchesPasswordException extends BaseException {

	public final static NotMatchesPasswordException EXCEPTION = new NotMatchesPasswordException();

	public NotMatchesPasswordException() {
		super(AuthErrorCode.NOT_REFRESH_TOKEN);
	}
}
