package com.ecom.userservice.repos;

import com.ecom.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{
    Role findByRoleName(String s);
}
