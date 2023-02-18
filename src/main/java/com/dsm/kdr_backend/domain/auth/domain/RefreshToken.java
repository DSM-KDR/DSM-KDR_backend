package com.dsm.kdr_backend.domain.auth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.dsm.kdr_backend.global.entity.BaseIdEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken extends BaseIdEntity {

	@Column(nullable = false, unique = true)
	private String refreshToken;

	public RefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public RefreshToken update(String refreshToken) {
		this.refreshToken = refreshToken;
		return this;
	}
}
