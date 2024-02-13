package com.ecom.productservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
//@MappedSuperclass
public class BaseModel {
  //  @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
