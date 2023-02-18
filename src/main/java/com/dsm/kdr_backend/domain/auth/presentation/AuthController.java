package com.dsm.kdr_backend.domain.auth.presentation;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dsm.kdr_backend.domain.auth.presentation.dto.request.PasswordRequest;
import com.dsm.kdr_backend.domain.auth.presentation.dto.response.TokenResponse;
import com.dsm.kdr_backend.domain.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	public TokenResponse login(@RequestBody @Valid PasswordRequest request) {
		return authService.login(request);
	}
	
	@PutMapping("/refresh")
	public TokenResponse refreshToken(@RequestHeader @NotBlank(message = "refreshToken를 입력해주세요.") String refreshToken) { return authService.refreshToken(refreshToken); }

}
