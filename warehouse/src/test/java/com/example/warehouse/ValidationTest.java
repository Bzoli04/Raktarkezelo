package com.example.warehouse;

import com.example.warehouse.model.Product;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        // Ez készíti elő a validátort minden teszt előtt
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testInvalidProduct() {
        // 1. Létrehozunk egy direkt hibás terméket (pl. üres névvel)
        Product product = new Product();
        product.setName(""); 
        product.setPrice(-100.0);

        // 2. Lefuttatjuk a validációt
        var violations = validator.validate(product);

        // 3. Ellenőrizzük, hogy TALÁLT-E hibát (nem üres a hibalista)
        assertFalse(violations.isEmpty(), "A validációnak hibát kellene jeleznie üres név és negatív ár esetén!");
    }

    @Test
    void testValidProduct() {
        // Létrehozunk egy teljesen jó terméket
        Product product = new Product();
        product.setName("Rendes Termék");
        product.setCategory("Kategória");
        product.setQuantity(10);
        product.setPrice(1000.0);

        var violations = validator.validate(product);

        // Ellenőrizzük, hogy NINCS hiba
        assertTrue(violations.isEmpty(), "Egy érvényes termék esetén nem szabadna hibát találnia!");
    }
}