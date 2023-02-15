package com.dsm.kdr_backend.domain.product.category.presentation;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dsm.kdr_backend.domain.product.category.presentation.dto.response.CategoryResponse;
import com.dsm.kdr_backend.domain.product.category.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/product/category")
@RequiredArgsConstructor
@RestController
public class CategoryController {

	private final CategoryService categoryService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long saveCategory(@RequestBody @NotBlank(message = "생성할 카테고리 이름을 입력해주세요.")
								@Size(max = 10, message = "10자 이하로 작성해주세요.") String category) {
		return categoryService.saveCategory(category);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCategory(@PathVariable("id") @NotNull(message = "삭제할 카테고리 id가 입력되지 않았습니다.") Long id) {
		categoryService.deleteCategory(id);
	}

	@GetMapping
	public List<CategoryResponse> getCategories() {
		return categoryService.getCategories();
	}

}
