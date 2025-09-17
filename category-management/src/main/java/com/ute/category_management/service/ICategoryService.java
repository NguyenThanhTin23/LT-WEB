package com.ute.category_management.service;
import com.ute.category_management.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICategoryService {
    Page<Category> findByNameContaining(String name, Pageable pageable);
    Category save(Category category);
    void deleteById(Integer id);
    Optional<Category> findById(Integer id);
}