package com.dsm.kdr_backend.domain.auth.presentation.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class PasswordRequest {

	@NotBlank(message = "입력해주세요.")
	private String password;

}
