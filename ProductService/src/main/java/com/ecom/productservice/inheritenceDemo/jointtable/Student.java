package com.ecom.productservice.inheritenceDemo.jointtable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "jt_student")
@Getter
@Setter
public class Student extends User {
    private int psp;
    private String batch;
}
