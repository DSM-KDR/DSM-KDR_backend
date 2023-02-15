package com.dsm.kdr_backend.global.aws.exception;

import com.dsm.kdr_backend.global.aws.exception.error.ImageErrorCode;
import com.dsm.kdr_backend.global.exception.BaseException;

public class ImageNotSaveException extends BaseException {
	public static final ImageNotSaveException EXCEPTION = new ImageNotSaveException();
	private ImageNotSaveException() {
		super(ImageErrorCode.IMAGE_NOT_SAVE);
	}
}