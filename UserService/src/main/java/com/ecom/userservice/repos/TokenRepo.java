package com.ecom.userservice.repos;

import com.ecom.userservice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {

    Optional<Token> findByValue(String value);


    Optional<Token> findByValueAndIsDeletedEquals(String token, boolean b);
}
