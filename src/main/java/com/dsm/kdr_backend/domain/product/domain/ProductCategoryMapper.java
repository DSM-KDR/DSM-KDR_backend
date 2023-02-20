package com.dsm.kdr_backend.domain.product.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.dsm.kdr_backend.global.entity.BaseIdEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductCategoryMapper extends BaseIdEntity {

	@Column(nullable = false)
	private Long categoryId;

	@Column(nullable = false)
	private Long productId;

	public ProductCategoryMapper(Long categoryId, Long productId) {
		this.categoryId = categoryId;
		this.productId = productId;
	}

}
