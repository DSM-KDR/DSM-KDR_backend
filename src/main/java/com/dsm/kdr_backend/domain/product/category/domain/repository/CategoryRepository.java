package com.dsm.kdr_backend.domain.product.category.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dsm.kdr_backend.domain.product.category.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	List<Category> findAll();
}
