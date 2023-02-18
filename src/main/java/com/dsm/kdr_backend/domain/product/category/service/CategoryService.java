package com.dsm.kdr_backend.domain.product.category.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsm.kdr_backend.domain.product.category.domain.Category;
import com.dsm.kdr_backend.domain.product.category.domain.repository.CategoryRepository;
import com.dsm.kdr_backend.domain.product.category.exception.NotFoundCategoryException;
import com.dsm.kdr_backend.domain.product.category.presentation.dto.request.CategoryRequest;
import com.dsm.kdr_backend.domain.product.category.presentation.dto.response.CategoryResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Transactional
	public Long saveCategory(CategoryRequest request) {
		return categoryRepository.save(new Category(request.getCategory())).getId();
	}

	@Transactional
	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id)
			.orElseThrow(() -> NotFoundCategoryException.EXCEPTION);
		categoryRepository.delete(category);
	}

	@Transactional(readOnly = true)
	public List<CategoryResponse> getCategories() {
		return categoryRepository.findAll()
			.stream().map(category -> {
				return CategoryResponse.builder()
					.id(category.getId())
					.category(category.getCategory())
					.build();
			}).collect(Collectors.toList());
	}

}
