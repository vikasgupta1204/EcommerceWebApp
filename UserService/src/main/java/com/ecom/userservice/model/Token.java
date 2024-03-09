package com.ecom.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Token extends BaseModel{
    private String value;
    @ManyToOne
    private User user;
    private Date expiryAt;
    private boolean isDeleted;

}
