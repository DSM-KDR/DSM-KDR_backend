package com.dsm.kdr_backend.domain.notice.presentation.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticesResponse {

	private final int totalPage;
	private final List<NoticeResponse> noticeResponses;

	@Getter @Builder
	static public class NoticeResponse {

		private final Long id;
		private final String preview;
		private final String title;
		private final LocalDate date;

	}

}
