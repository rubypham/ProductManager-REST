package com.minachi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minachi.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
