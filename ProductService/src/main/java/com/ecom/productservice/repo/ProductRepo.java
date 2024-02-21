package com.ecom.productservice.repo;

import com.ecom.productservice.models.Category;
import com.ecom.productservice.models.Product;
import com.ecom.productservice.projections.ProductWithIdAndTitle;
import org.hibernate.annotations.SortComparator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);
    List<Product> findByCategory(Category category);

    @Query("select p.id as id,p.title as title from Product  p where p.title =:title")
    List<ProductWithIdAndTitle> getProductIdAndTitle(@Param("title") String title);
}
