package com.ecom.userservice.controller;

import com.ecom.userservice.model.Role;
import com.ecom.userservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    RoleService roleService;


    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public ResponseEntity<Role> addRole(@RequestParam String roleName) {
        return ResponseEntity.ok(roleService.addRole(roleName));
    }

    @PostMapping("/update")
    public ResponseEntity<Role> updateRole(@RequestParam long roleId,@RequestParam String roleName) {
        return ResponseEntity.ok(roleService.updateRole(roleId, roleName));
    }
    @PostMapping("/deleteByName")
    public ResponseEntity<Role> deleteRoleByName(@RequestParam String roleName) {
        return ResponseEntity.ok(roleService.deleteRoleByName(roleName));
    }

    @PostMapping("/deleteById")
    public ResponseEntity<Role> deleteRoleById(@RequestParam long roleId) {
        return ResponseEntity.ok(roleService.deleteRoleById(roleId));
    }

}
