package com.dsm.kdr_backend.domain.notice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.dsm.kdr_backend.global.entity.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notice extends BaseTimeEntity {

	@Column(nullable = false)
	private String image;

	@Column(nullable = false, length = 20)
	private String title;

	@Column(nullable = false, length = 2000)
	private String content;

	@Builder
	private Notice(String image, String title, String content) {
		this.image = image;
		this.title = title;
		this.content = content;
	}

	public Long update(String image, String title, String content) {
		this.image = image;
		this.title = title;
		this.content = content;
		return getId();
	}
	
}
