package com.ute.category_management.service;

import com.ute.category_management.entity.Category;
import com.ute.category_management.repository.CategoryRepository;
import com.ute.category_management.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> findByNameContaining(String keyword, Pageable pageable) {
        return categoryRepository.findByNameContaining(keyword, pageable);
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
