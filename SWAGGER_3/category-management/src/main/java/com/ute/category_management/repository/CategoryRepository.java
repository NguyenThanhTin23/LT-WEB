package com.ute.category_management.repository;

import com.ute.category_management.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findByNameContaining(String keyword, Pageable pageable);
}
