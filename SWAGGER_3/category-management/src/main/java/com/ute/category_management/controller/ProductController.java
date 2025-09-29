package com.ute.category_management.controller;

import com.ute.category_management.entity.Product;
import com.ute.category_management.service.IProductService;
import com.ute.category_management.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    // Danh sách sản phẩm (có phân trang + tìm kiếm)
    @GetMapping
    public String listProducts(@RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               Model model) {
        Page<Product> products = productService.getAllProducts(keyword, PageRequest.of(page, size));
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        model.addAttribute("categories", categoryService.findAll()); // gửi categories xuống view
        return "product/list";  // templates/product/list.html
    }

    // Form thêm mới
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product/form";
    }

    // Lưu sản phẩm mới
    @PostMapping("/save")
    public String save(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    // Form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Product product = productService.getProductById(id).orElseThrow();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "product/form";
    }

    // Cập nhật sản phẩm
    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products";
    }

    // Xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
