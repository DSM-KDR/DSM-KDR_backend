package com.dsm.kdr_backend.domain.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dsm.kdr_backend.domain.auth.domain.RefreshToken;
import com.dsm.kdr_backend.domain.auth.domain.repository.RefreshRepository;
import com.dsm.kdr_backend.domain.auth.exception.NotMatchesPasswordException;
import com.dsm.kdr_backend.domain.auth.presentation.dto.response.TokenResponse;
import com.dsm.kdr_backend.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	@Value("${auth.password}")
	private String password;

	private final RefreshRepository refreshRepository;
	private final JwtTokenProvider jwtTokenProvider;

	public TokenResponse login(String comparePassword) {
		if(!password.equals(comparePassword)) throw NotMatchesPasswordException.EXCEPTION;

		return TokenResponse.builder()
			.accessToken(jwtTokenProvider.generateAccessToken())
			.refreshToken(refreshRepository.save(
				new RefreshToken(jwtTokenProvider.generateRefreshToken())).getRefreshToken())
			.build();
	}

}
