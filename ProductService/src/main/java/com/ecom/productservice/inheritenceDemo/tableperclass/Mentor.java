package com.ecom.productservice.inheritenceDemo.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mentor_tpc")
public class Mentor extends User {
    private int rating;
}
