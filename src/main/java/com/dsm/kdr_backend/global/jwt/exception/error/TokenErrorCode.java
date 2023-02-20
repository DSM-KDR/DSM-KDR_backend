package com.dsm.kdr_backend.global.jwt.exception.error;

import com.dsm.kdr_backend.global.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenErrorCode implements ErrorCode {

	TOKEN_UNAUTHORIZED(401,"토큰이 유효 하지 않습니다."),
	NOT_ACCESS_TOKEN(401,"access token이 아닙니다. 토큰을 확인해주세요.");

	private final int status;
	private final String message;

}
