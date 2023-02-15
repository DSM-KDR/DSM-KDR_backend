package com.dsm.kdr_backend.domain.product.presentation;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dsm.kdr_backend.domain.product.presentation.dto.request.ProductRequest;
import com.dsm.kdr_backend.domain.product.service.SaveProductService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/product")
@RequiredArgsConstructor
@RestController
public class ProductController {

	private final SaveProductService saveProductService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long saveProduct(@RequestBody @Valid ProductRequest request,
								@RequestParam(value = "file") MultipartFile file) {
		return saveProductService.execute(request, file);
	}

}
