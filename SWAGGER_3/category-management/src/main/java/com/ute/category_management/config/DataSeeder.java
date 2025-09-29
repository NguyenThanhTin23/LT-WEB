package com.ute.category_management.config;

import com.ute.category_management.entity.Category;
import com.ute.category_management.entity.Product;
import com.ute.category_management.repository.CategoryRepository;
import com.ute.category_management.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seed(CategoryRepository catRepo, ProductRepository proRepo){
        return args -> {
            if(catRepo.count() == 0 && proRepo.count() == 0){
                Category c1 = catRepo.save(Category.builder()
                        .name("Điện thoại")
                        .status(true)
                        .build());

                Category c2 = catRepo.save(Category.builder()
                        .name("Laptop")
                        .status(true)
                        .build());

                proRepo.save(Product.builder()
                        .name("iPhone 14")
                        .price(20990000.0)
                        .status(true)
                        .category(c1)
                        .build());

                proRepo.save(Product.builder()
                        .name("Galaxy S23")
                        .price(15990000.0)
                        .status(true)
                        .category(c1)
                        .build());

                proRepo.save(Product.builder()
                        .name("MacBook Air M2")
                        .price(27990000.0)
                        .status(true)
                        .category(c2)
                        .build());
            }
        };
    }
}
