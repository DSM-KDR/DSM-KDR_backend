package com.dsm.kdr_backend.domain.product.category.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class CategoryRequest {

	@NotBlank(message = "입력해주세요.")
	@Size(max = 10, message = "10자 이하로 입력해주세요.")
	private String category;

}
