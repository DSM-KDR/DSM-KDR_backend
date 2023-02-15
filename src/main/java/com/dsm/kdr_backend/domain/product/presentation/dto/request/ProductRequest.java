package com.dsm.kdr_backend.domain.product.presentation.dto.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class ProductRequest {

	@NotBlank(message = "상품 이름을 입력해주세요.")
	@Size(max = 20, message = "상품 이름을 20자 이하로 입력해주세요.")
	private String name;

	@NotBlank(message = "상품 설명을 입력해주세요.")
	@Size(max = 30, message = "상품 설명을 30자 이하로 입력해주세요.")
	private String short_description;

	@Valid
	@NotNull(message = "상품 카테고리을 입력해주세요.")
	@Size(min = 1, message = "상품 카테고리을 입력해주세요.")
	private List<@NotBlank(message = "상품 카테고리을 입력해주세요.") Long> category;

	@PositiveOrZero(message = "0 이상의 용량을 입력해주세요.")
	private int capacity;

	@NotBlank(message = "상품 상세설명을 입력해주세요.")
	@Size(max = 5000, message = "상품 상세 설명을 5000자 이하로 입력해주세요.")
	private String description;

	@PositiveOrZero(message = "0원 이상의 용량을 입력해주세요.")
	private int price;

	@NotBlank(message = "상품 원산지을 입력해주세요.")
	@Size(max = 20, message = "상품 원산를 20자 이하로 입력해주세요.")
	private String origin;

}
