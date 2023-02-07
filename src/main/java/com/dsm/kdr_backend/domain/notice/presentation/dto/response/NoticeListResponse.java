package com.dsm.kdr_backend.domain.notice.presentation.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeListResponse {

	private final int totalPage;
	private final List<NoticeResponse> noticeResponses;

	@Getter @Builder
	public class NoticeResponse {

		private final Long id;
		private final String title;
		private final String content;

	}

}
