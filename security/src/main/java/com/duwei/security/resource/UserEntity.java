package com.duwei.security.resource;

import com.duwei.commonsspringbootstarter.base.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "DW_USER")
public class UserEntity extends AbstractEntity{
    private String name;

    private String address;

    @JsonIgnore
    private String password;

    @ManyToMany(mappedBy = "users" ,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<RoleEntity> roles = Sets.newHashSet();

    public UserEntity(String name){
        this.name = name;
        this.password = "123456";
        this.address = "湖北宜昌市东山大道127号";
    }

    public UserEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
