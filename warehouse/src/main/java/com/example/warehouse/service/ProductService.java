package com.example.warehouse.service;

import com.example.warehouse.model.Product;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

// A service reteg tartalmazza a termekekkel kapcsolatos uzleti muveleteket.
@Service
public class ProductService {
    // A repository vegzi a tenyleges adatbazis muveleteket.
    private final ProductRepository repository;

    // Konstruktoros dependency injection: a Spring adja at a repository peldanyt.
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // Visszaadja az osszes termeket az adatbazisbol.
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Nev alapjan keres termekeket, kis- es nagybetutol fuggetlenul.
    public List<Product> searchProductsByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }

    // Uj termeket ment, vagy letezo termeket frissit, ha mar van azonositoja.
    public void saveProduct(Product product) {
        repository.save(product);
    }

    // Az azonosito alapjan torli a termeket az adatbazisbol.
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    // Egy termeket keres azonosito alapjan; ha nincs talalat, null erteket ad vissza.
    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
