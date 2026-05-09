package com.example.warehouse;

import com.example.warehouse.model.Product;
import com.example.warehouse.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void testSaveProduct() {
        // 1. Létrehozunk egy teszt terméket
        Product product = new Product();
        product.setName("Teszt Monitor");
        product.setCategory("Elektronika");
        product.setQuantity(10);
        product.setPrice(55000.0);

        // 2. Elmentjük a service segítségével
        productService.saveProduct(product);

        // 3. Ellenőrizzük, hogy az adatbázis adott-e neki ID-t (tehát a mentés sikeres)
        assertNotNull(product.getId(), "A termék mentése után az ID nem lehet null!");

        // 4. Ellenőrizzük, hogy a név megegyezik-e
        assertEquals("Teszt Monitor", product.getName());
    }
}