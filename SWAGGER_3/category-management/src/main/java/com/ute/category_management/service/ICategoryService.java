package com.ute.category_management.service;

import com.ute.category_management.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Page<Category> findByNameContaining(String keyword, Pageable pageable);
    Optional<Category> findById(Integer id);
    Category save(Category category);
    void deleteById(Integer id);
    List<Category> findAll();
}
