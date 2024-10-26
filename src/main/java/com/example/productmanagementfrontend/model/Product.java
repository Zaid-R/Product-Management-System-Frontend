package com.example.productmanagementfrontend.model;

import java.math.BigDecimal;

public class Product implements Cloneable{
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            Product clone = new Product();
            clone.setId(this.id);
            clone.setName(this.name);
            clone.setPrice(this.price);
            clone.setDescription(this.description);
            return clone;
        }
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
