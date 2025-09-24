package com.ute.customer_api.controller;

import com.ute.customer_api.input.*;
import com.ute.customer_api.model.*;
import com.ute.customer_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.HashSet;
import java.util.List;

@Controller
public class AppGraphqlController {

    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private UserRepository userRepository;

    // =========== QUERIES ===========
    @QueryMapping
    public List<Product> allProductsSortedByPrice() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productRepository.findByCategories_Id(categoryId);
    }

    @QueryMapping
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }

    @QueryMapping
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    // =========== MUTATIONS ===========
    // --- User CRUD ---
    @MutationMapping
    public User createUser(@Argument UserInput input) {
        User user = new User();
        user.setFullname(input.getFullname());
        user.setEmail(input.getEmail());
        user.setPassword(input.getPassword()); // Trong thực tế cần mã hóa mật khẩu
        user.setPhone(input.getPhone());
        return userRepository.save(user);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument UserInput input) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setFullname(input.getFullname());
        user.setEmail(input.getEmail());
        user.setPassword(input.getPassword());
        user.setPhone(input.getPhone());
        return userRepository.save(user);
    }

    @MutationMapping
    public String deleteUser(@Argument Long id) {
        userRepository.deleteById(id);
        return "User with id " + id + " deleted successfully.";
    }

    // --- Category CRUD ---
    @MutationMapping
    public Category createCategory(@Argument CategoryInput input) {
        Category category = new Category();
        category.setName(input.getName());
        category.setImages(input.getImages());
        return categoryRepository.save(category);
    }

    @MutationMapping
    public String deleteCategory(@Argument Long id) {
        categoryRepository.deleteById(id);
        return "Category with id " + id + " deleted successfully.";
    }

    // --- Product CRUD ---
    @MutationMapping
    public Product createProduct(@Argument ProductInput input) {
        User user = userRepository.findById(input.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Category> categories = categoryRepository.findAllById(input.getCategoryIds());

        Product product = new Product();
        product.setTitle(input.getTitle());
        product.setDesc(input.getDesc());
        product.setPrice(input.getPrice());
        product.setQuantity(input.getQuantity());
        product.setUser(user);
        product.setCategories(new HashSet<>(categories));

        return productRepository.save(product);
    }

    @MutationMapping
    public String deleteProduct(@Argument Long id) {
        productRepository.deleteById(id);
        return "Product with id " + id + " deleted successfully.";
    }
}