package com.dsm.kdr_backend.domain.category.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsm.kdr_backend.domain.category.domain.Category;
import com.dsm.kdr_backend.domain.category.domain.repository.CategoryRepository;
import com.dsm.kdr_backend.domain.category.exception.NotFoundCategoryException;
import com.dsm.kdr_backend.domain.category.presentation.dto.response.CategoryResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Transactional
	public Long saveCategory(String category) {
		return categoryRepository.save(new Category(category)).getId();
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
