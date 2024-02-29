package com.ecom.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
public class Token extends BaseModel{
    private String token;

    @ManyToOne
    private User user;
    private Timestamp expiryDate;

}
