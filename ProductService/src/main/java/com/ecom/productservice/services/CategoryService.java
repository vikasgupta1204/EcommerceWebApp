package com.ecom.productservice.services;

import com.ecom.productservice.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category saveCategory(Category category);
    Category findByCategoryId(long categoryId);
    List<Category> getAllCategories();
    Category updateCategory(long id,Category category);
    Category deleteCategory(long id);
    Category findByCategoryName(String categoryName);
}
