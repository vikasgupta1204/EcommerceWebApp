package com.ecom.productservice.serviceImpl;

import com.ecom.productservice.models.Product;
import com.ecom.productservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImpl")
public class ProductServiceImpl implements ProductService {
    @Override
    public ResponseEntity<Product> getProductById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {

        return null;
    }

    @Override
    public void deleteProductById() {

    }

    @Override
    public Product addProduct(Product product) {
      return null;
    }

    @Override
    public void updateProductById() {

    }
}
