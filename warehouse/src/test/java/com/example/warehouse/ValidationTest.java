package com.example.warehouse;

import com.example.warehouse.model.Product;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// A Product osztaly validacios szabalyait ellenorzo unit tesztek.
class ValidationTest {

    // A Validator futtatja le a Product mezoin levo jakarta.validation annotaciokat.
    private Validator validator;

    // Minden teszt elott uj validatort keszitunk, hogy tiszta allapotbol induljon a vizsgalat.
    @BeforeEach
    void setUp() {
        // Ez kesziti elo a validatort minden teszt elott.
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // Hibasan kitoltott termeknel validacios hibakat varunk.
    @Test
    void testInvalidProduct() {
        // 1. Letrehozunk egy direkt hibas termeket, peldaul ures nevvel es negativ arral.
        Product product = new Product();
        product.setName("");
        product.setPrice(-100.0);

        // 2. Lefuttatjuk a validaciot a Product objektumon.
        var violations = validator.validate(product);

        // 3. Ellenorizzuk, hogy talalt-e hibat, vagyis nem ures a hibalista.
        assertFalse(violations.isEmpty(), "A validacionak hibat kellene jeleznie ures nev es negativ ar eseten!");
    }

    // Helyesen kitoltott termeknel nem szabad validacios hibanak lennie.
    @Test
    void testValidProduct() {
        // Letrehozunk egy teljesen jo termeket minden kotelezo mezovel.
        Product product = new Product();
        product.setName("Rendes Termek");
        product.setCategory("Kategoria");
        product.setQuantity(10);
        product.setPrice(1000.0);

        // A validacio eredmenye akkor jo, ha nem tartalmaz hibat.
        var violations = validator.validate(product);

        // Ellenorizzuk, hogy nincs hiba.
        assertTrue(violations.isEmpty(), "Egy ervenyes termek eseten nem szabadna hibat talalnia!");
    }
}
