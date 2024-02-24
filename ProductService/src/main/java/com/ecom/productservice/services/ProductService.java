package com.ecom.productservice.services;

import com.ecom.productservice.exceptions.ProductNotFoundException;
import com.ecom.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<Product> getProductById(Long id) throws ProductNotFoundException;
    ResponseEntity<Page<Product>> getAllProducts(int page, int size, String sortBy);
    Product deleteProductById(long id) throws ProductNotFoundException;
    Product addProduct(Product product);
    Product updateProductById(long id,Product product) throws ProductNotFoundException;
    List<String> getAllCategory();
    ResponseEntity<Page<Product>> getInCategory(String category,int page,int size,String sortBy) throws ProductNotFoundException;
}
