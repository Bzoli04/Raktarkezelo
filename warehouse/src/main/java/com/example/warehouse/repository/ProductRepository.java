package com.example.warehouse.repository;

import com.example.warehouse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// A repository reteg felel a Product entitas adatbazis elereseert.
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Spring Data JPA a metodus nevebol generalja a keresest: nevreszlet alapjan, kis- es nagybetut figyelmen kivul hagyva.
    List<Product> findByNameContainingIgnoreCase(String name);
}
