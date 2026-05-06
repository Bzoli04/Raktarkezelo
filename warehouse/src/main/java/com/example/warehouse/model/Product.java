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

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A termék neve nem lehet üres.")
    private String name;

    @NotBlank(message = "A kategória nem lehet üres.")
    private String category;

    @NotNull(message = "A mennyiséget kötelező megadni.")
    @Min(value = 1, message = "A mennyiségnek legalább 1-nek kell lennie.")
    private Integer quantity;

    @NotNull(message = "Az árat kötelező megadni.")
    @DecimalMin(value = "0.01", message = "Az árnak nagyobbnak kell lennie 0-nál.")
    private Double price;
}
