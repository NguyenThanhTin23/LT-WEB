package com.ute.category_management.api;

import com.ute.category_management.entity.Category;
import com.ute.category_management.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryApi {
    private final ICategoryService svc;
    public CategoryApi(ICategoryService svc){ this.svc = svc; }

    @GetMapping
    public Page<Category> list(@RequestParam(defaultValue="") String keyword,
                               @RequestParam(defaultValue="0") int page,
                               @RequestParam(defaultValue="5") int size) {
        return svc.findByNameContaining(keyword, PageRequest.of(page, size));
    }
    @GetMapping("/{id}") public Category get(@PathVariable Integer id){ return svc.findById(id).orElseThrow(); }
    @PostMapping public Category create(@RequestBody Category c){ return svc.save(c); }
    @PutMapping("/{id}") public Category update(@PathVariable Integer id, @RequestBody Category c){ c.setId(id); return svc.save(c); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Integer id){ svc.deleteById(id); }
}
