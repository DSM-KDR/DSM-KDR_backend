package com.dsm.kdr_backend.domain.auth.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class TokenResponse {

	private String accessToken;

	private String refreshToken;

}
