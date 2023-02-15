package com.dsm.kdr_backend.global.aws.exception.error;

import com.dsm.kdr_backend.global.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageErrorCode implements ErrorCode {

	IMAGE_NOT_SAVE(400, "사진이 정상적으로 저장되지 못했습니다."),
	IMAGE_BAD_REQUEST(400, "이미지가 올바르지 않습니다.");

	private final int status;
	private final String message;

}
