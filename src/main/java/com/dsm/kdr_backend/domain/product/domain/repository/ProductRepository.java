package com.dsm.kdr_backend.domain.product.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.dsm.kdr_backend.domain.product.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	Page<Product> findAllByOrderByIdDesc(Pageable page);
	Optional<Product> findByIdOrderByIdDesc(Long id);
	Page<Product> findAllByNameContainingOrderByIdDesc(String name, Pageable page);
}
