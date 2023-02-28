package com.dsm.kdr_backend.domain.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsm.kdr_backend.domain.product.category.domain.repository.CategoryRepository;
import com.dsm.kdr_backend.domain.product.category.exception.NotExistCategoryException;
import com.dsm.kdr_backend.domain.product.category.exception.NotFoundCategoryException;
import com.dsm.kdr_backend.domain.product.domain.Product;
import com.dsm.kdr_backend.domain.product.domain.ProductCategoryMapper;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductCategoryMapperRepository;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductRepository;
import com.dsm.kdr_backend.domain.product.presentation.dto.request.ProductRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ProductCategoryMapperRepository productCategoryMapperRepository;

	@Transactional(rollbackFor = NotFoundCategoryException.class)
	public Long execute(ProductRequest request) {

		Product product = productRepository.save(Product.builder()
			.image(request.getImage())
			.name(request.getName())
			.capacity(request.getCapacity())
			.price(request.getPrice())
			.description(request.getDescription())
			.origin(request.getOrigin())
			.build()
		);

		for(Long categoryId : request.getCategory()) {
			categoryRepository.findById(categoryId).orElseThrow(() -> NotExistCategoryException.EXCEPTION);
			productCategoryMapperRepository.save(new ProductCategoryMapper(categoryId, product.getId()));
		}

		return product.getId();

	}

}
