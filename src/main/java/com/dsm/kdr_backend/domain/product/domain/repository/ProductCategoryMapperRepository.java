package com.dsm.kdr_backend.domain.product.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dsm.kdr_backend.domain.product.domain.ProductCategoryMapper;

public interface ProductCategoryMapperRepository extends CrudRepository<ProductCategoryMapper, Long> {
	List<ProductCategoryMapper> findAllByProductId(Long productId);
	List<ProductCategoryMapper> findAllByCategoryId(Long categoryId);
}
