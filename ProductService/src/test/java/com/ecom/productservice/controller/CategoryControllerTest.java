//package com.ecom.productservice.controller;
//
//import com.ecom.productservice.exceptions.CategoryNotFoundException;
//import com.ecom.productservice.models.Category;
//import com.ecom.productservice.services.CategoryService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class CategoryControllerTest {
//
//    @Autowired
//    private CategoryController categoryController;
//    @MockBean
//    private CategoryService categoryService;
//
//    @Test
//    void saveCategory() {
//        Category category = new Category();
//        category.setId(1L);
//        category.setName("Test category");
//        when(categoryService.saveCategory(category)).thenReturn(category);
//        Category savedCategory = categoryController.saveCategory(category);
//        assertNotNull(savedCategory);
//        assertEquals(1L, savedCategory.getId());
//        assertEquals("Test category", savedCategory.getName());
//        verify(categoryService).saveCategory(category);
//    }
//
//    @Test
//    void findByCategoryId() {
//        long id = 1L;
//        Category category = new Category();
//        category.setId(id);
//        category.setName("Test category");
//        when(categoryService.findByCategoryId(id)).thenReturn(category);
//        Category foundCategory = categoryController.findByCategoryId(id);
//        assertNotNull(foundCategory);
//        assertEquals(1L, foundCategory.getId());
//        assertEquals("Test category", foundCategory.getName());
//        verify(categoryService).findByCategoryId(id);
//    }
//
//    @Test
//    void findByCategoryId_CategoryNotExist() {
//        long id = 1L;
//        when(categoryService.findByCategoryId(id)).thenThrow(new CategoryNotFoundException("Category not found with id " + id));
//        assertThrows(CategoryNotFoundException.class, () -> categoryController.findByCategoryId(id));
//        verify(categoryService).findByCategoryId(id);
//    }
//
//
//    @Test
//    void getAllCategories() {
//        categoryController.getAllCategories();
//        verify(categoryService).getAllCategories();
//    }
//
//    @Test
//    void updateCategory() {
//        long id = 1L;
//        String categoryName = "Test category";
//        Category category = new Category();
//        category.setId(id);
//        category.setName(categoryName);
//        when(categoryService.updateCategory(id, categoryName)).thenReturn(category);
//        Category updatedCategory = categoryController.updateCategory(id, categoryName);
//        assertNotNull(updatedCategory);
//        assertEquals(1L, updatedCategory.getId());
//        assertEquals("Test category", updatedCategory.getName());
//        verify(categoryService).updateCategory(id, categoryName);
//    }
//
//    @Test
//    void deleteCategory() {
//        long id = 1L;
//        Category category = new Category();
//        category.setId(id);
//        category.setName("Test category");
//        when(categoryService.deleteCategory(id)).thenReturn(category);
//        Category deletedCategory = categoryController.deleteCategory(id);
//        assertNotNull(deletedCategory);
//        assertEquals(1L, deletedCategory.getId());
//        assertEquals("Test category", deletedCategory.getName());
//        verify(categoryService).deleteCategory(id);
//    }
//
//    @Test
//    void findByCategoryName() {
//        String categoryName = "Test category";
//        Category category = new Category();
//        category.setId(1L);
//        category.setName(categoryName);
//        when(categoryService.findByCategoryName(categoryName)).thenReturn(category);
//        Category foundCategory = categoryController.findByCategoryName(categoryName);
//        assertNotNull(foundCategory);
//        assertEquals(1L, foundCategory.getId());
//        assertEquals("Test category", foundCategory.getName());
//        verify(categoryService).findByCategoryName(categoryName);
//    }
//}