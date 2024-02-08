package com.ecom.productservice.dtos;

import com.ecom.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeProductDto {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private String category;

}
