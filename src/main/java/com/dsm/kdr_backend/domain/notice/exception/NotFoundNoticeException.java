package com.dsm.kdr_backend.domain.notice.exception;

import com.dsm.kdr_backend.domain.notice.exception.error.NoticeErrorCode;
import com.dsm.kdr_backend.global.exception.BaseException;

public class NotFoundNoticeException extends BaseException {

	public final static NotFoundNoticeException EXCEPTION = new NotFoundNoticeException();

	public NotFoundNoticeException() {
		super(NoticeErrorCode.NOT_FOUND_NOTICE);
	}
}
