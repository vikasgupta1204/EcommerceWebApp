package com.ecom.productservice.controller;

import com.ecom.productservice.exceptions.CategoryNotFoundException;
import com.ecom.productservice.models.Category;
import com.ecom.productservice.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService)
    {
        this.categoryService=categoryService;
    }

    @Operation(summary = "Add category", description = "saves a category")
    @ApiResponses(value = {@ApiResponse(responseCode = "201" ,description = "Successfully saved the category")})
    @PostMapping("/save")
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @Operation(summary = "Get a category by id", description = "Returns a category as per the id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200" ,description = "Get a category per the id"),
            @ApiResponse(responseCode = "404", description = "Not found- The category was not found")})
    @GetMapping("/{categoryId}")
    public Category findByCategoryId(@PathVariable("categoryId") long categoryId) {
      return categoryService.findByCategoryId(categoryId);
    }


    @Operation(summary = "Get all categories", description = "Returns a  list of categories")
    @ApiResponses(value = {@ApiResponse(responseCode = "200" ,description = "Get all categories")})
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }


    public Category updateCategory(long id, Category category) {
        return null;
    }


    public Category deleteCategory(long id) {
        return null;
    }

    @Operation(summary = "Get a category by name", description = "Returns a category as per the name")
    @ApiResponses(value = {@ApiResponse(responseCode = "200" ,description = "Get a category per the name"),
            @ApiResponse(responseCode = "404", description = "Not found- The category was not found")})
    @GetMapping("getBy/{name}")
    public Category findByCategoryName(@PathVariable("name") String categoryName) {
       return categoryService.findByCategoryName(categoryName);
    }
}
