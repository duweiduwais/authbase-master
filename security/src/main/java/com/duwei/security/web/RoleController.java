package com.duwei.security.web;

import com.duwei.security.resource.RoleEntity;
import com.duwei.security.resource.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("role/list")
    public List<RoleEntity> getRoleList(){
        return roleRepository.findAll();
    }
}
