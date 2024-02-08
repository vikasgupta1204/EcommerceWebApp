package com.ecom.productservice.services;

import com.ecom.productservice.exceptions.ProductNotFoundException;
import com.ecom.productservice.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<Product> getProductById(Long id) throws ProductNotFoundException;
    ResponseEntity<List<Product>> getAllProducts();
    void deleteProductById();
    Product addProduct(Product product);
    void updateProductById();

}
