package com.ecom.productservice;

import com.ecom.productservice.exceptions.CategoryNotFoundException;
import com.ecom.productservice.exceptions.ProductNotFoundException;
import com.ecom.productservice.models.Category;
import com.ecom.productservice.models.Product;
import static org.junit.jupiter.api.Assertions.*;
import com.ecom.productservice.repo.CategoryRepo;
import com.ecom.productservice.repo.ProductRepo;
import com.ecom.productservice.serviceImpl.ProductServiceImpl;
import com.ecom.productservice.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {
	@Mock
	ProductRepo productRepo;
	@Mock
	CategoryRepo categoryRepo;
	private ProductService productService;

	@BeforeEach
	public void setUp(){
		MockitoAnnotations.openMocks(this);
		productService=new ProductServiceImpl(productRepo,categoryRepo);
	}

	@Test
	public void testSaveProduct_CategoryExists(){
		Product product=new Product();
		product.setTitle("Test product");
		product.setDescription("Test Description");
		product.setPrice(100L);
		Category category=new Category();
		category.setName("Test Category");
		List<Product> productList=new ArrayList<>();
		productList.add(product);
		category.setProducts(productList);
		product.setCategory(category);
		when(categoryRepo.findByName("Test Category")).thenReturn(Optional.of(category));
		when(productRepo.save(product)).thenReturn(product);
		Product savedProduct=productService.addProduct(product);
		assertNotNull(savedProduct);
		assertEquals("Test product",savedProduct.getTitle());
		assertEquals("Test Description",savedProduct.getDescription());
		assertEquals(100L,savedProduct.getPrice());
		assertEquals("Test Category",savedProduct.getCategory().getName());
		verify(categoryRepo,times(1)).findByName("Test Category");
		verify(productRepo,times(1)).save(product);
	}

	@Test
	public void testSaveProduct_CategoryNotExists(){
		Product product=new Product();
		product.setId(1l);
		product.setTitle("Test product");
		product.setDescription("Test Description");
		product.setPrice(100L);
		when(categoryRepo.findByName("Test Category")).thenReturn(Optional.empty());
		when(productRepo.save(any(Product.class))).thenAnswer(invocation-> {
			Product savedProduct=invocation.getArgument(0);
			assertEquals("Test Category",savedProduct.getCategory().getName());
			return savedProduct;
		});
		Product savedProduct=productService.addProduct(product);
		verify(categoryRepo,times(1)).findByName("Test Category");
		verify(productRepo,times(1)).save(product);
		assertEquals("Test Product",savedProduct.getTitle());
		assertEquals("Test Description",savedProduct.getDescription());
		assertEquals(100L,savedProduct.getPrice());
		assertEquals("Test Category",savedProduct.getCategory().getName());
	}

	@Test
	public  void testFindProductById_ProductFound() throws ProductNotFoundException {
		Long productId=1L;
		Product mockProduct=new Product();
		mockProduct.setId(productId);
		mockProduct.setTitle("Test Product");
		when(productRepo.findById(productId)).thenReturn(Optional.of(mockProduct));
		ResponseEntity<Product> product=productService.getProductById(productId);
		assertEquals(HttpStatus.OK,product.getStatusCode());
		assertEquals(mockProduct,product.getBody());
	}

	@Test
	public  void testFindProductById_ProductNotFound() throws ProductNotFoundException {
		Long productId=1L;
		when(productRepo.findById(productId)).thenReturn(Optional.empty());
		try {
			productService.getProductById(productId);
		}
		catch (ProductNotFoundException e){
			assertEquals("Product not found with id : "+productId,e.getMessage());
		}
	}

	@Test
	public  void testGetAllTheProducts(){
		List<Product> mockProducts=new ArrayList<>();
		mockProducts.add(new Product());
		mockProducts.add(new Product());
		when(productRepo.findAll()).thenReturn(mockProducts);
		ResponseEntity<List<Product>> responseEntity=productService.getAllProducts();
		assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		assertEquals(mockProducts,responseEntity.getBody());
	}

		@Test
		public void testDeleteProductById_ProductFound() throws ProductNotFoundException {
			Long productId=1L;
			Product mockProduct=new Product();
			mockProduct.setId(productId);
			when(productRepo.findById(productId)).thenReturn(Optional.of(mockProduct));
			Product deletedProduct=productService.deleteProductById(productId);
			assertEquals(mockProduct,deletedProduct);
			verify(productRepo,times(1)).delete(mockProduct);

		}

	@Test
	public void testDeleteProductById_ProductNotFound() throws ProductNotFoundException {
		Long productId=1L;
		when(productRepo.findById(any(Long.class))).thenReturn(Optional.empty());
		assertThrows(ProductNotFoundException.class,()->productService.deleteProductById(productId));

	}

	@Test
	public void testGetAllCategory(){
		Product product1=new Product();
		Product product2=new Product();
		product1.setCategory(new Category());
		product2.setCategory(new Category());
		product1.getCategory().setName("Category1");
		product2.getCategory().setName("Category2");
		List<Product> mockProducts=new ArrayList<>();
		mockProducts.add(product1);
		mockProducts.add(product2);
		when(productRepo.findAll()).thenReturn(mockProducts);
		List<String> categories=productService.getAllCategory();
		assertNotNull(categories);
		assertEquals(2,categories.size());
		assertEquals("Category1",categories.get(0));
		assertEquals("Category2",categories.get(1));

 	}

	 @Test
	 public void testGetInCategory_CategoryExists() throws ProductNotFoundException {
		String categoryName="Test Category";
		Category mockCategory=new Category();
		mockCategory.setName(categoryName);
		List<Product> mockProducts=new ArrayList<>();
		Product product1=new Product();
		product1.setTitle("Product1");
		 Product product2=new Product();
		 product2.setTitle("Product2");
		mockProducts.add(product1);
		mockProducts.add(product2);
		when(categoryRepo.findByName(categoryName)).thenReturn(Optional.of(mockCategory));
		when(productRepo.findByCategory(mockCategory)).thenReturn(mockProducts);
		ResponseEntity<List<Product>> productResponseEntity=productService.getInCategory(mockCategory.getName());
		assertEquals(HttpStatus.OK,productResponseEntity.getStatusCode());
		assertEquals(mockProducts,productResponseEntity.getBody());

	 }
	@Test
	public void testGetInCategory_CategoryNotFound() throws ProductNotFoundException {
		String categoryName="Non-existent category";
		when(categoryRepo.findByName(categoryName)).thenReturn(Optional.empty());
		assertThrows(CategoryNotFoundException.class,()->productService.getInCategory(categoryName));

	}

	}
