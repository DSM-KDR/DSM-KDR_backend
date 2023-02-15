package com.dsm.kdr_backend.domain.product.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.dsm.kdr_backend.domain.product.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
