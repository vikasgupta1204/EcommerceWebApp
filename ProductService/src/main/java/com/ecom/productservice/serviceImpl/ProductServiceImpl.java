package com.ecom.productservice.serviceImpl;

import com.ecom.productservice.services.ProductService;
import org.springframework.stereotype.Service;

@Service("selfProductServiceImpl")
public class ProductServiceImpl implements ProductService {
    @Override
    public String getProductById(Long id) {
        return "product fetched with id "+id;
    }

    @Override
    public void getAllProducts() {

    }

    @Override
    public void deleteProductById() {

    }

    @Override
    public void addProduct() {

    }

    @Override
    public void updateProductById() {

    }
}
