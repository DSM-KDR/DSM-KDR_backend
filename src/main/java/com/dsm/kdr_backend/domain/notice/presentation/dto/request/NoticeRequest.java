package com.dsm.kdr_backend.domain.notice.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class NoticeRequest {

	@NotBlank(message = "입력해주세요.")
	@Size(max = 20, message = "20자 이하로 입력해주세요.")
	private String title;

	@NotBlank(message = "입력해주세요.")
	@Size(max = 2000, message = "2000자 이하로 입력해주세요.")
	private String content;

}
