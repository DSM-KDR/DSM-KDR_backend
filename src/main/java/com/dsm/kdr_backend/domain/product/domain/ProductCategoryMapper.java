package com.dsm.kdr_backend.domain.product.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductCategoryMapper {

	@Id
	private Long categoryId;

	private Long productId;

	public ProductCategoryMapper(Long categoryId, Long productId) {
		this.categoryId = categoryId;
		this.productId = productId;
	}

}
