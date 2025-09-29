package com.ute.category_management.controller;

import com.ute.category_management.entity.Category;
import com.ute.category_management.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String listCategories(Model model,
                                 @RequestParam(defaultValue = "") String keyword,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryService.findByNameContaining(keyword, pageable);

        model.addAttribute("categoryPage", categoryPage);
        model.addAttribute("keyword", keyword);
        return "category/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "category/form";
        }
        return "redirect:/categories";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
