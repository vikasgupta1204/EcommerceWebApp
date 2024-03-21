//package com.ecom.productservice.controller;
//
//import com.ecom.productservice.exceptions.CategoryNotFoundException;
//import com.ecom.productservice.exceptions.ProductNotFoundException;
//import com.ecom.productservice.models.Category;
//import com.ecom.productservice.models.Product;
//import com.ecom.productservice.services.ProductService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.ResponseEntity;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class ProductControllerTest {
//    @Autowired
//    private ProductController productController;
//
//    @MockBean
//    @Qualifier("SelfProductServiceImpl")
//    private ProductService productService;
//
//    /*   public void setUp() {
//           MockitoAnnotations.openMocks(this);
//       }
//   */
//    @Test
//    void getProductById() throws ProductNotFoundException, JsonProcessingException {
////        long id = 1L;
////        Product product = new Product();
////        product.setId(id);
////        product.setTitle("Test product");
////        product.setDescription("Test Description");
////        product.setPrice(100L);
////        when(productService.getProductById(id)).thenReturn(ResponseEntity.ok(product));
////        ResponseEntity<Product> responseEntity = productController.getProductById("",id);
////        assertNotNull(responseEntity);
////        assertEquals(1L, responseEntity.getBody().getId());
////        assertEquals(200, responseEntity.getStatusCodeValue());
////        assertEquals("Test product", responseEntity.getBody().getTitle());
////        assertEquals("Test Description", responseEntity.getBody().getDescription());
////        assertEquals(100L, responseEntity.getBody().getPrice());
//    }
//
//    @Test
//    void getProductById_ProductNotFound() throws ProductNotFoundException {
////        long id = 1L;
////        when(productService.getProductById(id)).thenThrow(new ProductNotFoundException("Product not found with id : " + id));
////        assertThrows(ProductNotFoundException.class, () -> productController.getProductById("",id));
//    }
//
//    @Test
//    void getAllProducts() {
//        int page = 0;
//        int size = 10;
//        String sortBy = "id";
//        Page<Product> productPage = mock(Page.class);
//        when(productService.getAllProducts(page, size, sortBy)).thenReturn(ResponseEntity.ok(productPage));
//        ResponseEntity<Page<Product>> responseEntity = productController.getAllProducts(page, size, sortBy);
//        assertNotNull(responseEntity);
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        assertEquals(productPage, responseEntity.getBody());
//    }
//
//    @Test
//    void createProduct() {
//        Product mockProduct = new Product();
//        mockProduct.setId(1L);
//        Category category = new Category();
//        category.setName("Test Category");
//        mockProduct.setCategory(category);
//        mockProduct.setDescription("Test Description");
//        mockProduct.setPrice(100L);
//        mockProduct.setTitle("Test product");
//        when(productService.addProduct(mockProduct)).thenReturn(mockProduct);
//        Product savedProduct = productController.createProduct(mockProduct);
//        assertNotNull(savedProduct);
//        assertEquals(1L, savedProduct.getId());
//        assertEquals("Test product", savedProduct.getTitle());
//        assertEquals("Test Description", savedProduct.getDescription());
//        assertEquals(100L, savedProduct.getPrice());
//        assertEquals("Test Category", savedProduct.getCategory().getName());
//    }
//
//    @Test
//    void deleteProductById() throws ProductNotFoundException {
//        long id = 1L;
//        Product mockProduct = new Product();
//        mockProduct.setId(id);
//        mockProduct.setTitle("Test product");
//        mockProduct.setDescription("Test Description");
//        mockProduct.setPrice(100L);
//        when(productService.deleteProductById(id)).thenReturn(mockProduct);
//        Product deletedProduct = productController.deleteProductById(id);
//        assertNotNull(deletedProduct);
//        assertEquals(1L, deletedProduct.getId());
//        assertEquals("Test product", deletedProduct.getTitle());
//        assertEquals("Test Description", deletedProduct.getDescription());
//        assertEquals(100L, deletedProduct.getPrice());
//    }
//
//    @Test
//    void updateProductById() throws ProductNotFoundException {
//        long id = 1L;
//        Product mockProduct = new Product();
//        mockProduct.setId(id);
//        mockProduct.setTitle("Test product");
//        mockProduct.setDescription("Test Description");
//        mockProduct.setPrice(100L);
//        when(productService.updateProductById(id, mockProduct)).thenReturn(mockProduct);
//        Product updatedProduct = productController.updateProductById(id, mockProduct);
//        assertNotNull(updatedProduct);
//        assertEquals(1L, updatedProduct.getId());
//        assertEquals("Test product", updatedProduct.getTitle());
//        assertEquals("Test Description", updatedProduct.getDescription());
//        assertEquals(100L, updatedProduct.getPrice());
//    }
//
//    @Test
//    void getAllCategories() {
//        Product product1 = new Product();
//        Product product2 = new Product();
//        Product product3 = new Product();
//        Product product4 = new Product();
//        Category category1 = new Category();
//        Category category2 = new Category();
//        Category category3 = new Category();
//        category1.setName("Test Category1");
//        category2.setName("Test Category2");
//        category3.setName("Test Category3");
//        product1.setCategory(category1);
//        product2.setCategory(category2);
//        product3.setCategory(category3);
//        product4.setCategory(category1);
//        when(productService.getAllCategory()).thenReturn(java.util.List.of("Test Category1", "Test Category2", "Test Category3"));
//        java.util.List<String> categories = productController.categories();
//        assertNotNull(categories);
//        assertEquals(3, categories.size());
//        assertEquals("Test Category1", categories.get(0));
//        assertEquals("Test Category2", categories.get(1));
//        assertEquals("Test Category3", categories.get(2));
//        verify(productService).getAllCategory();
//    }
//
//    @Test
//    void getInCategory() throws CategoryNotFoundException {
//
//        int page = 0;
//        int size = 10;
//        String sortBy = "id";
//        Product product1 = new Product();
//        Product product2 = new Product();
//        Product product3 = new Product();
//        Product product4 = new Product();
//        Category category1 = new Category();
//        Category category2 = new Category();
//        Category category3 = new Category();
//        category1.setName("Test Category1");
//        category2.setName("Test Category2");
//        category3.setName("Test Category3");
//        product1.setCategory(category1);
//        product2.setCategory(category2);
//        product3.setCategory(category3);
//        product4.setCategory(category1);
//        List<Product> products = java.util.List.of(product1, product2, product3, product4);
//        Page<Product> productPage = new PageImpl<>(products, PageRequest.of(0, 10, Sort.by(sortBy)), products.size());
//        when(productService.getInCategory("Test Category1", page, size, sortBy)).thenReturn(ResponseEntity.ok(productPage));
//        ResponseEntity<Page<Product>> responseEntity = productController.getInCategory("Test Category1", page, size, sortBy);
//        assertNotNull(responseEntity);
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        assertEquals(productPage, responseEntity.getBody());
//        verify(productService).getInCategory("Test Category1", page, size, sortBy);
//    }
//
//    @Test
//    public void getInCategory_CategoryNotFound() throws ProductNotFoundException {
//        int page = 0;
//        int size = 10;
//        String sortBy = "id";
//        Product product1 = new Product();
//        Product product2 = new Product();
//        Product product3 = new Product();
//        Product product4 = new Product();
//        Category category1 = new Category();
//        Category category2 = new Category();
//        Category category3 = new Category();
//        category1.setName("Test Category1");
//        category2.setName("Test Category2");
//        category3.setName("Test Category3");
//        product1.setCategory(category1);
//        product2.setCategory(category2);
//        product3.setCategory(category3);
//        product4.setCategory(category1);
//        List<Product> products = java.util.List.of(product1, product2, product3, product4);
//        Page<Product> productPage = new PageImpl<>(products, PageRequest.of(0, 10, Sort.by(sortBy)), products.size());
//        when(productService.getInCategory("Test Category4", page, size, sortBy)).thenThrow(new CategoryNotFoundException("Category not found with name : Test Category4"));
//        assertThrows(CategoryNotFoundException.class, () -> productController.getInCategory("Test Category4", page, size, sortBy));
//    }
//}