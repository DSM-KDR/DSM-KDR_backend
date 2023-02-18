package com.dsm.kdr_backend.domain.auth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsm.kdr_backend.domain.auth.domain.RefreshToken;
import com.dsm.kdr_backend.domain.auth.domain.repository.RefreshRepository;
import com.dsm.kdr_backend.domain.auth.exception.NotFoundRefreshTokenException;
import com.dsm.kdr_backend.domain.auth.exception.NotMatchesPasswordException;
import com.dsm.kdr_backend.domain.auth.exception.NotRefreshTokenException;
import com.dsm.kdr_backend.domain.auth.presentation.dto.request.PasswordRequest;
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

	@Transactional
	public TokenResponse login(PasswordRequest request) {
		if(!password.equals(request.getPassword())) throw NotMatchesPasswordException.EXCEPTION;

		return TokenResponse.builder()
			.accessToken(jwtTokenProvider.generateAccessToken())
			.refreshToken(refreshRepository.save(
				new RefreshToken(jwtTokenProvider.generateRefreshToken())).getRefreshToken())
			.build();
	}

	@Transactional
	public TokenResponse refreshToken(String refreshToken) {
		if(!jwtTokenProvider.isRefreshToken(refreshToken)) throw NotRefreshTokenException.EXCEPTION;

		RefreshToken token = refreshRepository.findByRefreshToken(refreshToken)
			.orElseThrow(() -> NotFoundRefreshTokenException.EXCEPTION);

		return TokenResponse.builder()
			.accessToken(jwtTokenProvider.generateAccessToken())
			.refreshToken(refreshRepository.save(
				token.update(jwtTokenProvider.generateRefreshToken())).getRefreshToken())
			.build();

	}

}
