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

	@NotBlank(message = "입력해주세요.")
	@Size(max = 20, message = "20자 이하로 입력해주세요.")
	private String name;

	@Valid
	@NotNull(message = "입력해주세요.")
	@Size(min = 1, message = "입력해주세요.")
	private List<@NotNull(message = "입력해주세요.") Long> category;

	@PositiveOrZero(message = "0 이상의 용량을 입력해주세요.")
	private int capacity;

	@NotBlank(message = "입력해주세요.")
	@Size(max = 5000, message = "5000자 이하로 입력해주세요.")
	private String description;

	@PositiveOrZero(message = "0원 이상의 용량을 입력해주세요.")
	private int price;

	@NotBlank(message = "입력해주세요.")
	@Size(max = 20, message = "20자 이하로 입력해주세요.")
	private String origin;

}
