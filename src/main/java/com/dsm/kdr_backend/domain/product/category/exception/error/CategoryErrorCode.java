package com.dsm.kdr_backend.domain.product.category.exception.error;

import com.dsm.kdr_backend.global.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryErrorCode implements ErrorCode {

	NOT_FOUND_CATEGORY(404, "카테고리를 찾을 수 없습니다."),
	NOT_EXIST_CATEGORY(409, "존재하지 않는 카테고리 입니다.");

	private final int status;
	private final String message;

}
