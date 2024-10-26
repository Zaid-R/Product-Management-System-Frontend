package com.example.productmanagementfrontend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.productmanagementfrontend.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${api.base.url}")
    private String API_BASE_URL;
    private final RestTemplate restTemplate;

    public ProductServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<Product> getProducts() {
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                API_BASE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                });
        return response.getBody();
    }

    @Override
    public Product getProductById(Long id) {
        String url = API_BASE_URL + "/" + id;
        return restTemplate.getForObject(url, Product.class);
    }

    @Override
    public void addProduct(Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);
        restTemplate.postForEntity(API_BASE_URL, requestEntity, Void.class);
    }

    @Override
    public void updateProduct(Product product) {
        String url = API_BASE_URL + "/" + product.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);
        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class);
    }

    @Override
    public void deleteProduct(Long id) {
        String url = API_BASE_URL + "/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class);
    }
}