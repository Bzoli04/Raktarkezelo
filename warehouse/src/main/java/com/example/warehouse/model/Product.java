package com.example.warehouse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// A Product osztaly egy adatbazisban tarolt termeket ir le.
@Entity
// A Lombok @Data automatikusan letrehozza a gettereket, settereket, toString/equals/hashCode metodusokat.
@Data
public class Product {
    // Elsodleges kulcs, vagyis a termek egyedi azonositoja.
    @Id
    // Az adatbazis automatikusan noveli es osztja ki az id erteket.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A termek neve kotelezo, ures szoveg nem fogadhato el.
    @NotBlank(message = "A termék neve nem lehet üres.")
    private String name;

    // A kategoria is kotelezo, hogy a termekek rendezhetok legyenek.
    @NotBlank(message = "A kategória nem lehet üres.")
    private String category;

    // A mennyiseget meg kell adni.
    @NotNull(message = "A mennyiséget kötelező megadni.")
    // A keszlet nem lehet nulla vagy negativ.
    @Min(value = 1, message = "A mennyiségnek legalább 1-nek kell lennie.")
    private Integer quantity;

    // Az arat meg kell adni.
    @NotNull(message = "Az árat kötelező megadni.")
    // Az arnak legalabb 0.01-nek kell lennie.
    @DecimalMin(value = "0.01", message = "Az árnak nagyobbnak kell lennie 0-nál.")
    private Double price;
}
