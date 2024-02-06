package com.ecom.productservice.services;

public interface ProductService {
    String getProductById(Long id);
    void getAllProducts();
    void deleteProductById();
    void addProduct();
    void updateProductById();

}
