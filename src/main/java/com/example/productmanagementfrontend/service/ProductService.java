package com.example.productmanagementfrontend.service;

import java.util.List;

import com.example.productmanagementfrontend.model.Product;

public interface ProductService {
    List<Product> getProducts();
    Product getProductById(Long id);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
}