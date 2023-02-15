package com.dsm.kdr_backend.domain.product.exception.error;

import com.dsm.kdr_backend.global.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductErrorCode implements ErrorCode {

	NOT_FOUND_PRODUCT(404, "상품을 찾을 수 없습니다.");

	private final int status;
	private final String message;

}
