package com.ecom.productservice.serviceImpl;

import com.ecom.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeProductServiceImpl")
public class FakeProductServiceImpl implements ProductService {
    private String getProductUrl="https://fakestoreapi.com/products/1";
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public String getProductById(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<String> responseEntity= restTemplate.getForEntity(getProductUrl,String.class);
        return "Calling fake product service "+responseEntity.toString();
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
