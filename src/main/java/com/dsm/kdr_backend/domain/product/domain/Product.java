package com.dsm.kdr_backend.domain.product.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.dsm.kdr_backend.global.entity.BaseIdEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product extends BaseIdEntity {

	@Column(nullable = false)
	private String image;

	@Column(nullable = false, length = 20)
	private String name;

	@Column
	private int	capacity;

	@Column(nullable = false, length = 5000)
	private String description;

	@Column
	private int price;

	@Column(nullable = false, length = 20)
	private String origin;

	@Builder
	private Product(String image, String name, int capacity, String description, int price, String origin) {
		this.image = image;
		this.name = name;
		this.capacity = capacity;
		this.description = description;
		this.price = price;
		this.origin = origin;
	}

	public Product updateProduct(String image, String name, int capacity, String description, int price, String origin) {
		this.image = image;
		this.name = name;
		this.capacity = capacity;
		this.description = description;
		this.price = price;
		this.origin = origin;
		return this;
	}
}
