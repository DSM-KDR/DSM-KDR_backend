package com.dsm.kdr_backend.domain.notice.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class NoticeRequest {

	@NotBlank
	@Size(max = 20)
	private String title;

	@NotBlank
	@Size(max = 2000)
	private String content;

}
