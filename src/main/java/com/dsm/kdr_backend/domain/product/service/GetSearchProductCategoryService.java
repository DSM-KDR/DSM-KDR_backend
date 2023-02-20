package com.dsm.kdr_backend.domain.product.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsm.kdr_backend.domain.product.domain.Product;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductCategoryMapperRepository;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductRepository;
import com.dsm.kdr_backend.domain.product.exception.NotFoundProductException;
import com.dsm.kdr_backend.domain.product.presentation.dto.response.ProductsResponse;
import com.dsm.kdr_backend.global.aws.S3Util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class GetSearchProductCategoryService {

	private final ProductRepository productRepository;
	private final ProductCategoryMapperRepository productCategoryMapperRepository;
	private final S3Util s3Util;

	@Transactional(readOnly = true)
	public ProductsResponse execute(Long categoryId, Pageable pageable) {
		List<Long> productIds = productCategoryMapperRepository.findAllByCategoryId(categoryId)
			.stream().map(productCategoryMapper -> {
				return productCategoryMapper.getProductId();
			}).collect(Collectors.toList());

		List<Product> products = new ArrayList<>();
		for(Long id : productIds) {
			products.add(productRepository.findByIdOrderByIdDesc(id)
				.orElseThrow(() -> NotFoundProductException.EXCEPTION));
		}

		int page = pageable.getPageSize() * pageable.getPageNumber();
		List<Product> processedProducts = new ArrayList<>();
		log.info("page : " + page);

		if(products.size() >= page + pageable.getPageSize()) {
			int endPage = products.size() - page;
			processedProducts = products.subList(endPage - pageable.getPageSize() ,endPage);
		} else {
			processedProducts = products.subList(0, products.size() - page);
		}

		int totalPages = products.size()/pageable.getPageSize() +1;

		processedProducts.sort((product1, product2) -> (int)(product2.getId() - product1.getId()));

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
