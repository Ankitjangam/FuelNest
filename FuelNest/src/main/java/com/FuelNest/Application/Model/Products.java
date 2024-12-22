package com.FuelNest.Application.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Enter The Product Name")
    private String name;

    private String description;
    @NotNull(message = "Set Price")
    private double price;

    private String category;

    @Column(length = 500)
    private String imageUrl;
}
