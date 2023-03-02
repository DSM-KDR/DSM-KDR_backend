package com.dsm.kdr_backend.domain.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsm.kdr_backend.domain.product.domain.Product;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductRepository;
import com.dsm.kdr_backend.domain.product.presentation.dto.response.ProductsResponse;
import com.dsm.kdr_backend.global.aws.S3Util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetSearchProductNameService {

	private final ProductRepository productRepository;
	private final S3Util s3Util;

	@Transactional(readOnly = true)
	public ProductsResponse execute(String name, Pageable page) {
		Page<Product> products = productRepository.findAllByNameContainingOrderByIdDesc(name, page);

		return new ProductsResponse(products.getTotalPages(),
			products.map(product -> {
					return ProductsResponse.ProductResponse.builder()
						.id(product.getId())
						.image(s3Util.getS3ObjectUrl(product.getPath()))
						.name(product.getName())
						.build();
				}
			).toList());

	}

}
