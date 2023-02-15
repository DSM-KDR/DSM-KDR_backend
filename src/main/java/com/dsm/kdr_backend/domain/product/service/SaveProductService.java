package com.dsm.kdr_backend.domain.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsm.kdr_backend.domain.product.category.domain.repository.CategoryRepository;
import com.dsm.kdr_backend.domain.product.category.exception.NotFoundCategoryException;
import com.dsm.kdr_backend.domain.product.domain.Product;
import com.dsm.kdr_backend.domain.product.domain.ProductCategoryMapper;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductCategoryMapperRepository;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductRepository;
import com.dsm.kdr_backend.domain.product.presentation.dto.request.ProductRequest;
import com.dsm.kdr_backend.global.aws.S3Util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaveProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ProductCategoryMapperRepository productCategoryMapperRepository;
	private final S3Util s3Util;

	@Transactional(rollbackFor = NotFoundCategoryException.class)
	public Long execute(ProductRequest request, MultipartFile file) {
		Product product = productRepository.save(Product.builder()
			.name(request.getName())
			.capacity(request.getCapacity())
			.short_description(request.getShort_description())
			.price(request.getPrice())
			.description(request.getDescription())
			.origin(request.getOrigin())
			.build()
		);

		for(Long categoryId : request.getCategory()) {
			categoryRepository.findById(categoryId).orElseThrow(() -> NotFoundCategoryException.EXCEPTION);
			productCategoryMapperRepository.save(new ProductCategoryMapper(categoryId, product.getId()));
		}

		product.updatePath(s3Util.uploadImage(file));
		return product.getId();

	}

}
