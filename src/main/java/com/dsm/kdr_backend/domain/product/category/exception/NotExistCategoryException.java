package com.dsm.kdr_backend.domain.product.category.exception;

import com.dsm.kdr_backend.domain.product.category.exception.error.CategoryErrorCode;
import com.dsm.kdr_backend.global.exception.BaseException;

public class NotExistCategoryException extends BaseException {

	public final static NotExistCategoryException EXCEPTION = new NotExistCategoryException();

	public NotExistCategoryException() {
		super(CategoryErrorCode.NOT_EXIST_CATEGORY);
	}
}
