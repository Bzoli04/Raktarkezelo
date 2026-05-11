package com.example.warehouse;

import com.example.warehouse.model.Product;
import com.example.warehouse.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Integracios teszt, amely a ProductService mentesi mukodeset ellenorzi.
@SpringBootTest
class ProductServiceTest {

    // A Spring adja be a service peldanyt, ugyanugy, ahogy eles futas kozben is.
    @Autowired
    private ProductService productService;

    // Egy termek mentese utan azt varjuk, hogy az adatbazis azonosito adjon neki.
    @Test
    void testSaveProduct() {
        // 1. Letrehozunk egy teszt termeket.
        Product product = new Product();
        product.setName("Teszt Monitor");
        product.setCategory("Elektronika");
        product.setQuantity(10);
        product.setPrice(55000.0);

        // 2. Elmentjuk a service segitsegevel.
        productService.saveProduct(product);

        // 3. Ellenorizzuk, hogy az adatbazis adott-e neki ID-t, vagyis a mentes sikeres.
        assertNotNull(product.getId(), "A termek mentese utan az ID nem lehet null!");

        // 4. Ellenorizzuk, hogy a nev megegyezik-e azzal, amit beallitottunk.
        assertEquals("Teszt Monitor", product.getName());
    }
}
