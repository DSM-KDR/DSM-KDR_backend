package com.dsm.kdr_backend.domain.category.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class CategoryResponse {

	private final Long id;
	private final String category;

}
