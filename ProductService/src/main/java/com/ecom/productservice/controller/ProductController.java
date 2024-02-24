package com.ecom.productservice.controller;

import com.ecom.productservice.exceptions.ProductNotFoundException;
import com.ecom.productservice.models.Category;
import com.ecom.productservice.models.Product;
import com.ecom.productservice.repo.CategoryRepo;
import com.ecom.productservice.repo.ProductRepo;
import com.ecom.productservice.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    CategoryRepo categoryRepo;
    ProductRepo productRepo;
    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("SelfProductServiceImpl") ProductService productService, CategoryRepo categoryRepo, ProductRepo productRepo) {
        this.productService = productService;
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    @Operation(summary = "Get a product by id", description = "Returns a product as per the id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully fetched"),
            @ApiResponse(responseCode = "404", description = "Not found- The product was not found")})
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @Operation(summary = "Get all products", description = "Returns all products")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully fetched all products")})
    @GetMapping("/all")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(defaultValue = "id") String sortBy) {
        return productService.getAllProducts(page, size, sortBy);
    }

    /**
     * public String getProductByCategory(String category){
     * return
     * }
     */

    @Operation(summary = "save a product", description = "save a product to database")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Successfully created the product")})
    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @Operation(summary = "Delete a product by id", description = "Deletes a product as per the id")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Successfully deleted the product"),
            @ApiResponse(responseCode = "404", description = "Not found- The product was not found")})
    @DeleteMapping("/{id}")
    public Product deleteProductById(@PathVariable long id) throws ProductNotFoundException {
        return productService.deleteProductById(id);
    }

    @Operation(summary = "Update a product by id", description = "Update a product as per the id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully updated the product"),
            @ApiResponse(responseCode = "404", description = "Not found- The product was not found")})
    @PutMapping("/{id}")
    public Product updateProductById(@PathVariable long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProductById(id, product);
    }

    @Operation(summary = "Get categories", description = "Returns list of categories associated with all products")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully retrieved the categories of all products")})
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<String> categories() {

        return productService.getAllCategory();
    }


    @Operation(summary = "Get Products", description = "Returns a product as per the particular category")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully retrieved the products " +
            " with particular category")})
    @GetMapping("/category/{category}")
    public ResponseEntity<Page<Product>> getInCategory(@PathVariable String category,
                                                       @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "id") String sortBy) throws ProductNotFoundException {
        return productService.getInCategory(category, page, size, sortBy);
    }

    @PostMapping("/addAll")
    public void addAllProducts(@RequestBody List<Product> products) {
        for (Product product : products) {

            Optional<Category> categoryOptional = categoryRepo.findByName(product.getCategory().getName());
            if (categoryOptional.isPresent()) {
                product.setCategory(categoryOptional.get());
            } else {
                product.setCategory(product.getCategory());
            }
            productRepo.save(product);
        }
    }
}


/**
 * 1. GetProductById(id)
 * 2. GetAllProducts()
 * 3. UpdateProductById()
 * 4. DeleteProduct()
 * 5. AddProduct()
 */
