package com.ecom.productservice.repo;

import com.ecom.productservice.models.Category;
import com.ecom.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);
    List<Product> findByCategory(Category category);
}
