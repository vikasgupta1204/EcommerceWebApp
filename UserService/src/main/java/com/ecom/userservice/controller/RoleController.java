package com.ecom.userservice.controller;

import com.ecom.userservice.model.Role;
import com.ecom.userservice.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    RoleService roleService;


    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "Add a new role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Role already exists")
    })
    @PostMapping("/add")
    public ResponseEntity<Role> addRole(@RequestParam String roleName) {
        return ResponseEntity.ok(roleService.addRole(roleName));
    }

    @Operation(summary = "Update a role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @PostMapping("/update")
    public ResponseEntity<Role> updateRole(@RequestParam long roleId,@RequestParam String roleName) {
        return ResponseEntity.ok(roleService.updateRole(roleId, roleName));
    }

    @Operation(summary = "Delete a role by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Role not found")})

    @PostMapping("/deleteByName")
    public ResponseEntity<Role> deleteRoleByName(@RequestParam String roleName) {
        return ResponseEntity.ok(roleService.deleteRoleByName(roleName));
    }

    @Operation(summary = "Delete a role by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Role not found")})
    @PostMapping("/deleteById")
    public ResponseEntity<Role> deleteRoleById(@RequestParam long roleId) {
        return ResponseEntity.ok(roleService.deleteRoleById(roleId));
    }

}
