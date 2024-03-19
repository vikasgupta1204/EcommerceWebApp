package com.ecom.productservice.serviceImpl;

import com.ecom.productservice.exceptions.CategoryNotFoundException;
import com.ecom.productservice.models.Category;
import com.ecom.productservice.repo.CategoryRepo;
import com.ecom.productservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category findByCategoryId(long categoryId) {
        Optional<Category> categoryOptional = categoryRepo.findById(categoryId);
        if (!categoryOptional.isPresent()) {
            throw new CategoryNotFoundException("Category not found  with id " + categoryId);
        }
        return categoryOptional.get();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category updateCategory(long id, String categoryName) {
        Category category = categoryRepo.findById(id).
                orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        category.setName(categoryName);
        Category updatedCategory = categoryRepo.save(category);
        return updatedCategory;
    }

    @Override
    public Category deleteCategory(long id) {
        Category categoryToDelete = categoryRepo.findById(id).
                orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        categoryRepo.delete(categoryToDelete);
        return categoryToDelete;
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        Optional<Category> categoryOptional = categoryRepo.findByName(categoryName);
        if (!categoryOptional.isPresent()) {
            throw new CategoryNotFoundException("Category not found  with name " + categoryName);
        }
        return categoryOptional.get();
    }
}
