package com.example.productmanagementfrontend.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
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

    public void setName(String name){
        this.name = name.trim();
    }

    public void setDescription(String description){
        this.description = description.trim();
    }
}
