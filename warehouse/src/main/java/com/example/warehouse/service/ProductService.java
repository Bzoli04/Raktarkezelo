package com.example.warehouse.service;

import com.example.warehouse.model.Product;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public List<Product> searchProductsByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }

    public void saveProduct(Product product) {
        repository.save(product);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
