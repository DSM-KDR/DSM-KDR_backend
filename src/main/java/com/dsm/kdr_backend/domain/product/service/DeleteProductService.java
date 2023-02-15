package com.dsm.kdr_backend.domain.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsm.kdr_backend.domain.product.domain.Product;
import com.dsm.kdr_backend.domain.product.domain.ProductCategoryMapper;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductCategoryMapperRepository;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductRepository;
import com.dsm.kdr_backend.domain.product.exception.NotFoundProductException;
import com.dsm.kdr_backend.global.aws.S3Util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteProductService {

	private final ProductRepository productRepository;
	private final ProductCategoryMapperRepository productCategoryMapperRepository;
	private final S3Util s3Util;

	@Transactional
	public void execute(Long productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> NotFoundProductException.EXCEPTION);

		List<ProductCategoryMapper> mappers = productCategoryMapperRepository.findAllByProductId(productId);
		productCategoryMapperRepository.deleteAll(mappers);

		s3Util.delete(product.getPath());

		productRepository.delete(product);
	}

}
