package com.dsm.kdr_backend.domain.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsm.kdr_backend.domain.product.domain.Product;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductRepository;
import com.dsm.kdr_backend.domain.product.presentation.dto.response.ProductsResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetProductsService {

	private final ProductRepository productRepository;

	@Transactional(readOnly = true)
	public ProductsResponse execute(Pageable page) {
		Page<Product> products = productRepository.findAllByOrderByIdDesc(page);

		return new ProductsResponse(products.getTotalPages(),
			products.map(product -> {
					return ProductsResponse.ProductResponse.builder()
						.id(product.getId())
						.image(product.getImage())
						.name(product.getName())
						.build();
				}
			).toList());

	}

}
