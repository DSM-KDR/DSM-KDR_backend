package com.dsm.kdr_backend.domain.product.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.dsm.kdr_backend.domain.product.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	Page<Product> findAllByOrderByIdDesc(Pageable page);
	Page<Product> findAllByIdOrderByIdDesc(List<Long> ids, Pageable page);
	Page<Product> findAllByNameContainingOrderByIdDesc(String name, Pageable page);
}
