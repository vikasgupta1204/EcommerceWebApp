package com.ecom.productservice.dtos;

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
