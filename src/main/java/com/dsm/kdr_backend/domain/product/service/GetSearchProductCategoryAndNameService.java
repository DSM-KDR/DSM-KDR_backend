package com.dsm.kdr_backend.domain.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsm.kdr_backend.domain.product.domain.Product;
import com.dsm.kdr_backend.domain.product.domain.ProductCategoryMapper;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductCategoryMapperRepository;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductRepository;
import com.dsm.kdr_backend.domain.product.presentation.dto.response.ProductsResponse;
import com.dsm.kdr_backend.global.aws.S3Util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetSearchProductCategoryAndNameService {

	private final ProductRepository productRepository;
	private final ProductCategoryMapperRepository productCategoryMapperRepository;
	private final S3Util s3Util;

	@Transactional(readOnly = true)
	public ProductsResponse execute(Long categoryId, String name, Pageable page) {

		Page<Product> products = productRepository.findAllByNameContainingOrderByIdDesc(name, page);

		List<Product> processedProducts = new ArrayList<>();
		for(Product product : products) {
			List<Long> ids = productCategoryMapperRepository.findAllByProductId(product.getId())
				.stream().map(ProductCategoryMapper::getCategoryId).collect(Collectors.toList());

			if(ids.contains(categoryId)) processedProducts.add(product);
		}

		int totalPages = processedProducts.size()/page.getPageSize() +1;

		return new ProductsResponse(totalPages,
			processedProducts.stream().map(product -> {
					return ProductsResponse.ProductResponse.builder()
						.id(product.getId())
						.image(s3Util.getS3ObjectUrl(product.getPath()))
						.name(product.getName())
						.build();
				}
			).collect(Collectors.toList()));

	}

}
