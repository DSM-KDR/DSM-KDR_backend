package com.dsm.kdr_backend.global.aws.exception;

import com.dsm.kdr_backend.global.aws.exception.error.ImageErrorCode;
import com.dsm.kdr_backend.global.exception.BaseException;

public class ImageBadRequestException extends BaseException {
	public static final ImageBadRequestException EXCEPTION = new ImageBadRequestException();
	private ImageBadRequestException() {
		super(ImageErrorCode.IMAGE_BAD_REQUEST);
	}
}