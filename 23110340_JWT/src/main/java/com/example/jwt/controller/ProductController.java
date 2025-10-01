package com.example.jwt.controller;
import com.example.jwt.entity.Product;
import com.example.jwt.repository.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repo;
    public ProductController(ProductRepository repo){this.repo=repo;}
    @GetMapping
    public List<Product> all(){return repo.findAll();}
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public Product create(@RequestBody Product p){return repo.save(p);}
}
