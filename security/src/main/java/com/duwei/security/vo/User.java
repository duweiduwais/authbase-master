package com.duwei.security.vo;

import com.duwei.security.resource.RoleEntity;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class User {

    private String name;

    private String address;

    private Page<RoleEntity> roles;

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
