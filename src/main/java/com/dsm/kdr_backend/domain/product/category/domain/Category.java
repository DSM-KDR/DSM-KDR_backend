package com.dsm.kdr_backend.domain.product.category.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.dsm.kdr_backend.global.entity.BaseIdEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category extends BaseIdEntity {

	@Column(nullable = false)
	private String path;

	@Column(nullable = false, length = 10)
	private String category;

	public Category(String category, String path) {
		this.path = path;
		this.category = category;
	}

}
