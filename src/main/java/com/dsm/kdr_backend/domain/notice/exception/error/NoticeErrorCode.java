package com.dsm.kdr_backend.domain.notice.exception.error;

import com.dsm.kdr_backend.global.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticeErrorCode implements ErrorCode {

	NOT_FOUND_NOTICE(404, "공지를 찾을 수 없습니다.");

	private final int status;
	private final String message;

}
