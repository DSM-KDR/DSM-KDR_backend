package com.dsm.kdr_backend.domain.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsm.kdr_backend.domain.product.category.domain.repository.CategoryRepository;
import com.dsm.kdr_backend.domain.product.category.exception.NotFoundCategoryException;
import com.dsm.kdr_backend.domain.product.domain.Product;
import com.dsm.kdr_backend.domain.product.domain.ProductCategoryMapper;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductCategoryMapperRepository;
import com.dsm.kdr_backend.domain.product.domain.repository.ProductRepository;
import com.dsm.kdr_backend.domain.product.exception.NotFoundProductException;
import com.dsm.kdr_backend.domain.product.presentation.dto.request.ProductRequest;
import com.dsm.kdr_backend.global.aws.S3Util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdateProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ProductCategoryMapperRepository productCategoryMapperRepository;
	private final S3Util s3Util;

	@Transactional(rollbackFor = NotFoundCategoryException.class)
	public Long execute(Long productId, ProductRequest request, MultipartFile file) {
		Product product = productRepository.findById(productId).orElseThrow(() -> NotFoundProductException.EXCEPTION);

		List<ProductCategoryMapper> productCategoryMappers = productCategoryMapperRepository.findAllByProductId(product.getId());
		productCategoryMapperRepository.deleteAll(productCategoryMappers);

		product.updateProduct(request.getName(), request.getShort_description(),
			request.getCapacity(), request.getDescription(), request.getPrice(), request.getOrigin());

		for(Long categoryId : request.getCategory()) {
			categoryRepository.findById(categoryId).orElseThrow(() -> NotFoundCategoryException.EXCEPTION);
			productCategoryMapperRepository.save(new ProductCategoryMapper(categoryId, productId));
		}

		if(file != null) {
			s3Util.delete(product.getPath());
			product.updatePath(s3Util.uploadImage(file));
		}
		return product.getId();

	}

}
