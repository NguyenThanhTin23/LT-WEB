package com.ute.category_management.service;

import com.ute.category_management.entity.Product;
import com.ute.category_management.repository.ProductRepository;
import com.ute.category_management.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getAllProducts(String keyword, Pageable pageable) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return productRepository.findAll(pageable);
        }
        return productRepository.findByNameContaining(keyword, pageable);
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Integer id, Product productDetails) {
        return productRepository.findById(id).map(p -> {
            p.setName(productDetails.getName());
            p.setPrice(productDetails.getPrice());
            p.setStatus(productDetails.getStatus());
            p.setCategory(productDetails.getCategory());
            return productRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
