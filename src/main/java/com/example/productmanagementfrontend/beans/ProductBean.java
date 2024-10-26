package com.example.productmanagementfrontend.beans;

import com.example.productmanagementfrontend.model.Product;
import com.example.productmanagementfrontend.service.ProductService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Component
@ViewScoped
@Getter
@Setter
public class ProductBean implements Serializable {

    private final ProductService productService;
    private List<Product> products;
    private Product selectedProduct;
    private Product newProduct;
    private Product productBeforeEdit;

    @Autowired
    public ProductBean(ProductService productService) {
        this.productService = productService;
        this.newProduct = new Product();
    }

    @PostConstruct
    public void init() {
        loadProducts();
    }

    public void saveProduct() throws JsonMappingException, JsonProcessingException {
        try {
            productService.addProduct(newProduct);
            addMessage(FacesMessage.SEVERITY_INFO, "Success", "Product added successfully");
            newProduct = new Product();
            loadProducts();
        } catch (HttpClientErrorException e) {
            getValidationErrors(e);
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to add product: " + e.getMessage());
        }
    }

    private void getValidationErrors(HttpClientErrorException e) throws JsonProcessingException {
        if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
            String responseBody = e.getResponseBodyAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> errorMap = objectMapper.readValue(responseBody,
                    new TypeReference<Map<String, String>>() {
                    });

            for (Map.Entry<String, String> entry : errorMap.entrySet()) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", entry.getValue());
            }
        } else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to add product: " + e.getMessage());
        }
    }

    public void updateProduct(Product product) throws JsonProcessingException{
        try {
            productService.updateProduct(product);
            addMessage(FacesMessage.SEVERITY_INFO, "Success", "Product updated successfully");
            loadProducts();
        } catch (HttpClientErrorException e) {
            getValidationErrors(e);
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to update product: " + e.getMessage());
        }
    }

    public void deleteProduct(Long id) {
        try {
            productService.deleteProduct(id);
            addMessage(FacesMessage.SEVERITY_INFO, "Success", "Product deleted successfully");
            loadProducts();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to delete product: " + e.getMessage());
        }
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void loadProducts() {
        try {
            products = productService.getProducts();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to load products: " + e.getMessage());
        }
    }

    private int findProductIndex(Long productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(productId)) {
                return i;
            }
        }
        return -1;
    }

    public void onRowEdit(RowEditEvent<Product> event) {
        Product editedProduct = event.getObject();
        try {
            updateProduct(editedProduct);
        } catch (Exception e) {
            // Restore the original state if update fails
            int index = findProductIndex(editedProduct.getId());
            if (index != -1 && productBeforeEdit != null) {
                products.set(index, productBeforeEdit.clone());
            }
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to update product: " + e.getMessage());
        } finally {
            productBeforeEdit = null;
            loadProducts(); // Load products after the edit to reflect changes
        }
    }

    public void onRowEditInit(RowEditEvent<Product> event) {
        event.getObject();
    }

    public void onRowEditCancel(RowEditEvent<Product> event) {
        // Restore the original product state if editing was canceled
        Product cancelledProduct = event.getObject();
        int index = findProductIndex(cancelledProduct.getId());
        if (index != -1 && productBeforeEdit != null) {
            products.set(index, productBeforeEdit.clone());
        }
        addMessage(FacesMessage.SEVERITY_INFO, "Cancelled", "Edit cancelled");
        productBeforeEdit = null; // Reset to prevent unintended restorations
    }
}