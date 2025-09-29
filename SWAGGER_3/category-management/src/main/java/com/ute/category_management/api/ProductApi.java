package com.ute.category_management.api;

import com.ute.category_management.entity.Product;
import com.ute.category_management.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductApi {
    private final IProductService productService;
    public ProductApi(IProductService productService){ this.productService = productService; }

    @GetMapping
    public Page<Product> list(@RequestParam(defaultValue="") String keyword,
                              @RequestParam(defaultValue="0") int page,
                              @RequestParam(defaultValue="5") int size){
        return productService.getAllProducts(keyword, PageRequest.of(page, size));
    }
    @GetMapping("/{id}") public Product get(@PathVariable Integer id){ return productService.getProductById(id).orElseThrow(); }
    @PostMapping public Product create(@RequestBody Product p){ return productService.saveProduct(p); }
    @PutMapping("/{id}") public Product update(@PathVariable Integer id, @RequestBody Product p){ return productService.updateProduct(id, p); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Integer id){ productService.deleteProduct(id); }
}
