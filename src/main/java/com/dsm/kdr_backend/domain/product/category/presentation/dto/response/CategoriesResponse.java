package com.dsm.kdr_backend.domain.product.category.presentation.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoriesResponse {

	private final List<CategoryResponse> categoryResponses;

	@Getter
	@Builder
	public static class CategoryResponse {

		private final Long id;
		private final String image;
		private final String category;

	}
}
