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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dsm.kdr_backend.domain.product.presentation.dto.request.ProductRequest;
import com.dsm.kdr_backend.domain.product.presentation.dto.response.ProductResponse;
import com.dsm.kdr_backend.domain.product.presentation.dto.response.ProductsResponse;
import com.dsm.kdr_backend.domain.product.service.DeleteProductService;
import com.dsm.kdr_backend.domain.product.service.GetProductService;
import com.dsm.kdr_backend.domain.product.service.GetProductsService;
import com.dsm.kdr_backend.domain.product.service.GetSearchProductCategoryAndNameService;
import com.dsm.kdr_backend.domain.product.service.GetSearchProductCategoryService;
import com.dsm.kdr_backend.domain.product.service.GetSearchProductNameService;
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
	private final GetProductService getProductService;
	private final GetSearchProductNameService getSearchProductNameService;
	private final GetSearchProductCategoryService getSearchProductCategoryService;
	private final GetSearchProductCategoryAndNameService getSearchProductCategoryAndNameService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long saveProduct(@RequestPart(value = "productRequest") @Valid ProductRequest request,
								@RequestPart(value = "file") MultipartFile file) {
		return saveProductService.execute(request, file);
	}

	@PutMapping("/{id}")
	public Long updateProduct(@PathVariable("id") Long id,
								@RequestPart(value = "productRequest") @Valid ProductRequest request,
								@RequestPart(value = "file", required = false) MultipartFile file) {
		return updateProductService.execute(id, request, file);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable("id") Long id) {
		deleteProductService.execute(id);
	}

	@GetMapping
	public ProductsResponse getProducts(Pageable page) {
		return getProductsService.execute(page);
	}

	@GetMapping("/{id}")
	public ProductResponse getProduct(@PathVariable("id") Long id) {
		return getProductService.execute(id);
	}

	@GetMapping("/search")
	public ProductsResponse getSearchProductName(@RequestParam(value = "name") @NotNull(message = "???????????? ???????????????.")String name, Pageable page) {
		return getSearchProductNameService.execute(name, page);
	}

	@GetMapping("/category")
	public ProductsResponse getSearchProductCategory(Pageable page,
					@RequestParam(value = "id") @NotNull(message = "???????????? ???????????????.")Long id) {
		return getSearchProductCategoryService.execute(id, page);
	}

	@GetMapping("/combine")
	public ProductsResponse getSearchProductCategoryAndNameService(Pageable page,
								@RequestParam(value = "id") @NotNull(message = "???????????? ???????????????.")Long id,
								@RequestParam(value = "name") @NotNull(message = "???????????? ???????????????.")String name) {
		return getSearchProductCategoryAndNameService.execute(id, name, page);
	}

}
