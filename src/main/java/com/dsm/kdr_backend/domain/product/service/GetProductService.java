package com.dsm.kdr_backend.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsm.kdr_backend.domain.product.category.domain.repository.CategoryRepository;
import com.dsm.kdr_backend.domain.product.category.exception.NotFoundCategoryException;
import com.dsm.kdr_backend.domain.product.domain.Product;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductCategoryMapperRepository;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductRepository;
import com.dsm.kdr_backend.domain.product.exception.NotFoundProductException;
import com.dsm.kdr_backend.domain.product.presentation.dto.response.ProductResponse;
import com.dsm.kdr_backend.global.aws.S3Util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ProductCategoryMapperRepository productCategoryMapperRepository;
	private final S3Util s3Util;

	@Transactional(readOnly = true)
	public ProductResponse execute(Long productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> NotFoundProductException.EXCEPTION);

		List<String> categories = productCategoryMapperRepository.findAllByProductId(productId)
			.stream().map(productCategoryMapper -> {
				return categoryRepository.findById(productCategoryMapper.getCategoryId())
					.orElseThrow(() -> NotFoundCategoryException.EXCEPTION).getCategory();
			}).collect(Collectors.toList());

		return ProductResponse.builder()
			.image(s3Util.getS3ObjectUrl(product.getPath()))
			.name(product.getName())
			.capacity(product.getCapacity())
			.origin(product.getOrigin())
			.description(product.getDescription())
			.price(product.getPrice())
			.category(categories)
			.build();

	}

}
