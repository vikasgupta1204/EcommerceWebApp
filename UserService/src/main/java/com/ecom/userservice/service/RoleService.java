package com.ecom.userservice.service;

import com.ecom.userservice.model.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role addRole(String roleName);
    Role updateRole(long roleId, String roleName);
    Role deleteRoleByName(String roleName);
    Role deleteRoleById(long roleId);

}
