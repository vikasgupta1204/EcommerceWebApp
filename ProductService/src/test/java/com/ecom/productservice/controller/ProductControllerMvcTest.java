//package com.ecom.productservice.controller;
//
//import com.ecom.productservice.models.Product;
//import com.ecom.productservice.services.ProductService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//@WebMvcTest(controllers = ProductController.class)
//public class ProductControllerMvcTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Qualifier("SelfProductServiceImpl")
//    @MockBean
//    private ProductService productService;
//
//
//    @Test
//    public void getProductById() throws Exception {
//        Product dummyProduct = new Product();
//        dummyProduct.setId(1L);
//        dummyProduct.setTitle("Test product");
//        dummyProduct.setDescription("Test Description");
//        dummyProduct.setPrice(100L);
//        when(productService.getProductById(1L)).thenReturn(ResponseEntity.ok(dummyProduct));
//        mockMvc.perform(get("/products/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$.id").value(1L))
//                .andExpect(jsonPath("$.title").value("Test product"))
//                .andExpect(jsonPath("$.description").value("Test Description"))
//                .andExpect(jsonPath("$.price").value(100L));
//
//    }
//}
