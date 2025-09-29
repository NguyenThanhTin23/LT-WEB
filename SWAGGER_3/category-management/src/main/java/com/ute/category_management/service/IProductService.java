package com.ute.category_management.service;

import com.ute.category_management.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Page<Product> getAllProducts(String keyword, Pageable pageable);
    Optional<Product> getProductById(Integer id);
    Product saveProduct(Product product);
    Product updateProduct(Integer id, Product productDetails);
    void deleteProduct(Integer id);
    List<Product> findAll();
}
