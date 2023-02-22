package com.dsm.kdr_backend.domain.notice.presentation.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class NoticeResponse {

	private final String title;
	private final String preview;
	private final String content;
	private final LocalDate date;

}
