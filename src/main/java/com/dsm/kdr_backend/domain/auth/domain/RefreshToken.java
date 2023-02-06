package com.dsm.kdr_backend.domain.auth.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken {

	@Id
	private String refreshToken;

	public RefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public RefreshToken update(String refreshToken) {
		this.refreshToken = refreshToken;
		return this;
	}
}
