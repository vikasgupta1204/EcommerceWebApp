package com.ecom.productservice.inheritenceDemo.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Mentor extends User{
    private int rating;
}
