package com.ecom.productservice.inheritenceDemo.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "student_tpc")
@Getter
@Setter
public class Student extends User {
    private int psp;
    private String batch;
}
