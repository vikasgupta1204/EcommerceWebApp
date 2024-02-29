package com.ecom.userservice.serviceImpl;

import com.ecom.userservice.exceptions.RoleNotFoundException;
import com.ecom.userservice.model.Role;
import com.ecom.userservice.repos.RoleRepo;
import com.ecom.userservice.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    RoleRepo roleRepo;
    Logger logger= LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role addRole(String roleName) {
        Role role=roleRepo.findByRoleName(roleName);
        if(role==null){
            Role newRole=new Role();
            newRole.setRoleName(roleName);
            return roleRepo.save(newRole);
        }
        logger.info("Role already exists:"+roleName);
        return role;
    }

    @Override
    public Role updateRole(long roleId, String roleName) {
        Role role=roleRepo.findById(roleId).orElseThrow(()->new RoleNotFoundException("Role not found with id:"+roleId));
        Role roleByName=roleRepo.findByRoleName(roleName);
        if(roleByName!=null&&roleByName.getId()!=roleId){
            logger.error("Role already exists with name:"+roleName);
            throw new RoleNotFoundException("Role already exists with name:"+roleName);
        }
        role.setRoleName(roleName);
        Role updatedRole= roleRepo.save(role);
        logger.info("Role updated:"+roleName);
        return updatedRole;
    }

    @Override
    public Role deleteRoleByName(String roleName) {
        Role role=roleRepo.findByRoleName(roleName);
        if(role==null){
            logger.error("Role not found:"+roleName);
            throw new RoleNotFoundException("Role not found:"+roleName);
        }
        roleRepo.delete(role);
        return role;
    }

    @Override
    public Role deleteRoleById(long roleId) {
        Optional<Role> role=roleRepo.findById(roleId);
        if(!role.isPresent()) {
            logger.error("Role not found with id:"+roleId);
            throw new RoleNotFoundException("Role not found with id:"+roleId);
        }
        roleRepo.delete(role.get());
        return role.get();
    }
}
