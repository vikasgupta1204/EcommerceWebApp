package com.ecom.productservice.controller;

import com.ecom.productservice.dtos.ExceptionDto;
import com.ecom.productservice.exceptions.ProductNotFoundException;
import com.ecom.productservice.models.Product;
import com.ecom.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(@Qualifier("fakeProductServiceImpl") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return productService.getAllProducts();
    }
/**
    public String getProductByCategory(String category){
        return
    }
 */

@PostMapping("/create")
public Product createProduct(@RequestBody Product product){
    return productService.addProduct(product);
}

}

/**
 * 1. GetProductById(id)
 * 2. GetAllProducts()
 * 3. UpdateProductById()
 * 4. DeleteProduct()
 * 5. AddProduct()
 */
