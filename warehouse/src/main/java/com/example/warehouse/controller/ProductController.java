package com.example.warehouse.controller;

import com.example.warehouse.model.Product;
import com.example.warehouse.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String viewHomePage(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        String trimmedKeyword = keyword == null ? "" : keyword.trim();
        boolean hasKeyword = !trimmedKeyword.isEmpty();
        model.addAttribute("products", hasKeyword ? service.searchProductsByName(trimmedKeyword) : service.getAllProducts());
        model.addAttribute("keyword", trimmedKeyword);
        return "index";
    }

    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "new_product";
        }
        service.saveProduct(product);
        return "redirect:/";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        service.deleteProduct(id);
        return "redirect:/";
    }
}
