package com.dsm.kdr_backend.domain.category.exception;

import com.dsm.kdr_backend.domain.category.exception.error.CategoryErrorCode;
import com.dsm.kdr_backend.global.exception.BaseException;

public class NotFoundCategoryException extends BaseException {

	public final static NotFoundCategoryException EXCEPTION = new NotFoundCategoryException();

	public NotFoundCategoryException() {
		super(CategoryErrorCode.NOT_FOUND_CATEGORY);
	}
}
