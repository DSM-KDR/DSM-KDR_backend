package com.dsm.kdr_backend.global.jwt.exception;

import com.dsm.kdr_backend.global.exception.BaseException;
import com.dsm.kdr_backend.global.jwt.exception.error.TokenErrorCode;

public class TokenUnauthorizedException extends BaseException {

	public final static TokenUnauthorizedException EXCEPTION = new TokenUnauthorizedException();

	public TokenUnauthorizedException() {
		super(TokenErrorCode.TOKEN_UNAUTHORIZED);
	}

}
