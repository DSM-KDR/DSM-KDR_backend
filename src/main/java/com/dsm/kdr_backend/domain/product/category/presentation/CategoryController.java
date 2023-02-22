package com.dsm.kdr_backend.domain.product.category.presentation;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dsm.kdr_backend.domain.product.category.presentation.dto.request.CategoryRequest;
import com.dsm.kdr_backend.domain.product.category.presentation.dto.response.CategoryResponse;
import com.dsm.kdr_backend.domain.product.category.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/category")
@RequiredArgsConstructor
@RestController
public class CategoryController {

	private final CategoryService categoryService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long saveCategory(@RequestPart(value = "categoryRequest") @Valid CategoryRequest request,
							@RequestPart(value = "file") MultipartFile file) {
		return categoryService.saveCategory(request, file);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCategory(@PathVariable("id") Long id) {
		categoryService.deleteCategory(id);
	}

	@GetMapping
	public List<CategoryResponse> getCategories() {
		return categoryService.getCategories();
	}

}
