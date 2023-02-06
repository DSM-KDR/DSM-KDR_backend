package com.dsm.kdr_backend.domain.auth.exception.error;

import com.dsm.kdr_backend.global.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {

	NOT_MATCHES_PASSWORD(409, "비밀번호가 일치하지 않습니다."),
	NOT_REFRESH_TOKEN(401,"refresh 토큰이 아닙니다."),
	NOT_FOUND_REFRESH_TOKEN(404, "refresh 토큰을 찾을 수 없습니다.");

	private final int status;
	private final String message;

}
