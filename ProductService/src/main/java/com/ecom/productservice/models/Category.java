package com.ecom.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;
    /*The infinite recursion issue typically occurs when
     there is a bidirectional relationship between entities and
      Jackson (the JSON library used by Spring Boot) tries to
      serialize them. In this case, the Product entity has a Category field
      and the Category entity has a collection of Product entities.
      When Spring Boot tries to serialize a Product,
       it also serializes the associated Category, and since the
       Category has a collection of Products
     , it serializes them as well, leading to an infinite loop.*/
    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Product> products;
}
