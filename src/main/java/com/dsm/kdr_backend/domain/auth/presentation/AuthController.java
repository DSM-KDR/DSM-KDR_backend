package com.dsm.kdr_backend.domain.auth.presentation;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dsm.kdr_backend.domain.auth.presentation.dto.response.TokenResponse;
import com.dsm.kdr_backend.domain.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/auth")
@RequiredArgsConstructor
@Controller
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	public TokenResponse login(@RequestBody @NotBlank String password) {
		return authService.login(password);
	}

}
