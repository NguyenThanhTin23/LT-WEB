package com.ute.category_management.repository;

import com.ute.category_management.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Spring Data JPA sẽ tự động tạo câu query để tìm kiếm theo tên
    // LIKE %keyword% và trả về kết quả dưới dạng trang (Page)
    Page<Category> findByNameContaining(String name, Pageable pageable);
}