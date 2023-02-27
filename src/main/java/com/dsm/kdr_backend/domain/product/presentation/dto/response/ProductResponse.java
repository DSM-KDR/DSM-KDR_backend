package com.dsm.kdr_backend.domain.product.presentation.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ProductResponse {

	private final String image;
	private final String name;
	private final List<String> category;
	private final int capacity;
	private final String description;
	private final int price;
	private final String origin;

}
