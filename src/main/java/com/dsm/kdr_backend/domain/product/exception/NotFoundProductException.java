package com.dsm.kdr_backend.domain.product.exception;

import com.dsm.kdr_backend.domain.product.exception.error.ProductErrorCode;
import com.dsm.kdr_backend.global.exception.BaseException;

public class NotFoundProductException extends BaseException {
	public static final NotFoundProductException EXCEPTION = new NotFoundProductException();
	private NotFoundProductException() {
		super(ProductErrorCode.NOT_FOUND_PRODUCT);
	}
}