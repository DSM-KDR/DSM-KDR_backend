package com.dsm.kdr_backend.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KdrException extends RuntimeException {

	private final ErrorCode errorCode;

}
