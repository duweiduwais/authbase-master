package com.duwei.security.resource;

import com.duwei.commonsspringbootstarter.base.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name="DW_ROLE")
public class RoleEntity extends AbstractEntity{

    private String roleName;

    private String roleShowName;

    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinTable(name = "role_user", joinColumns = {@JoinColumn(name = "roles_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")})
    @JsonIgnore
    private Set<UserEntity> users = Sets.newHashSet();

    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(name = "role_resource", joinColumns = {@JoinColumn(name = "roles_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "resources_id", referencedColumnName = "id")})
    @JsonIgnore
    private Set<ResourceEntity> resources = Sets.newHashSet();

    public RoleEntity(String roleName, String roleShowName) {
        this.roleName = roleName;
        this.roleShowName = roleShowName;
    }

    public RoleEntity() {

    }

    public void addResources(List<ResourceEntity> rs){
        this.resources.addAll(rs);
    }

    public void addResource(Optional<ResourceEntity> resourceEntity){
        if(resourceEntity.isPresent()){
            this.resources.add(resourceEntity.get());
        }
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleShowName() {
        return roleShowName;
    }

    public void setRoleShowName(String roleShowName) {
        this.roleShowName = roleShowName;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public Set<ResourceEntity> getResources() {
        return resources;
    }

    public void setResources(Set<ResourceEntity> resources) {
        this.resources = resources;
    }
}


