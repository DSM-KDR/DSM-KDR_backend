package com.dsm.kdr_backend.domain.product.category.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsm.kdr_backend.domain.product.category.domain.Category;
import com.dsm.kdr_backend.domain.product.category.domain.repository.CategoryRepository;
import com.dsm.kdr_backend.domain.product.category.exception.NotFoundCategoryException;
import com.dsm.kdr_backend.domain.product.category.presentation.dto.request.CategoryRequest;
import com.dsm.kdr_backend.domain.product.category.presentation.dto.response.CategoryResponse;
import com.dsm.kdr_backend.global.aws.S3Util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final S3Util s3Util;
	private final CategoryRepository categoryRepository;

	@Transactional
	public Long saveCategory(CategoryRequest request, MultipartFile file) {
		return categoryRepository.save(
			new Category(request.getCategory(), s3Util.uploadImage(file, "/category"))).getId();
	}

	@Transactional
	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> NotFoundCategoryException.EXCEPTION);
		s3Util.delete(category.getPath());
		categoryRepository.delete(category);
	}

	@Transactional(readOnly = true)
	public List<CategoryResponse> getCategories() {
		return categoryRepository.findAll()
			.stream().map(this::ofCategoryResponse).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<CategoryResponse> getSearchCategory(String category) {
		return categoryRepository.findAllByCategoryContaining(category)
			.stream().map(this::ofCategoryResponse).collect(Collectors.toList());
	}

	private CategoryResponse ofCategoryResponse(Category category) {
		return CategoryResponse.builder()
			.id(category.getId())
			.image(s3Util.getS3ObjectUrl(category.getPath()))
			.category(category.getCategory())
			.build();
	}

}
