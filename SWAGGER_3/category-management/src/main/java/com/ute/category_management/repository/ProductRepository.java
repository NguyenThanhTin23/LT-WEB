package com.ute.category_management.repository;

import com.ute.category_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByNameContaining(String keyword, Pageable pageable);
}
