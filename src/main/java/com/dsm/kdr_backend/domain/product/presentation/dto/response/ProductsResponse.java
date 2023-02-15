package com.dsm.kdr_backend.domain.product.presentation.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductsResponse {

	private final int totalPage;
	private final List<ProductResponse> productResponses;

	@Getter @Builder
	public class ProductResponse {

		private final Long id;
		private final String image;
		private final String name;

	}

}
