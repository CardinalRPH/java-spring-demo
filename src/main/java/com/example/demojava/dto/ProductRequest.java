package com.example.demojava.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProductRequest {

    @NotBlank(message = "Product name cannot be empty")
    @Size(min = 3, message = "Product name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Price cannot be empty")
    @Pattern(regexp = "^[0-9]+(\\.[0-9]+)?$", message = "Price must be a valid raw number without quotes or letters")
    private String price;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
}