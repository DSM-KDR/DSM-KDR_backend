package com.dsm.kdr_backend.domain.product.presentation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dsm.kdr_backend.domain.product.presentation.dto.request.ProductRequest;
import com.dsm.kdr_backend.domain.product.presentation.dto.response.ProductsResponse;
import com.dsm.kdr_backend.domain.product.service.DeleteProductService;
import com.dsm.kdr_backend.domain.product.service.GetProductsService;
import com.dsm.kdr_backend.domain.product.service.SaveProductService;
import com.dsm.kdr_backend.domain.product.service.UpdateProductService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/product")
@RequiredArgsConstructor
@RestController
public class ProductController {

	private final SaveProductService saveProductService;
	private final UpdateProductService updateProductService;
	private final DeleteProductService deleteProductService;
	private final GetProductsService getProductsService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long saveProduct(@RequestBody @Valid ProductRequest request,
								@RequestParam(value = "file") MultipartFile file) {
		return saveProductService.execute(request, file);
	}

	@PutMapping("/{id}")
	public Long updateProduct(@RequestBody @Valid ProductRequest request,
								@PathVariable("id") @NotNull(message = "수정할 상품 id가 입력되지 않았습니다.") Long id,
								@RequestParam(value = "file") MultipartFile file) {
		return updateProductService.execute(id, request, file);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable("id") @NotNull(message = "수정할 상품 id가 입력되지 않았습니다.") Long id) {
		deleteProductService.execute(id);
	}

	@GetMapping
	public ProductsResponse getProducts(Pageable page) {
		return getProductsService.execute(page);
	}

}
