package com.duwei.security.resource;

import com.duwei.commonsspringbootstarter.base.AbstractEntity;
import com.duwei.commonsspringbootstarter.base.ResourceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DW_Resource")
public class ResourceEntity extends AbstractEntity{
    private String name;

    private String url;

    private String method;

    private String serverid;

    @Enumerated(EnumType.STRING)
    private ResourceType resourceType = ResourceType.MENU;

    @ManyToMany(mappedBy = "resources")
    @JsonIgnore
    private Set<RoleEntity> roles = Sets.newHashSet();

    public ResourceEntity(String name, String url, String method, String serverid, ResourceType resourceType, Set<RoleEntity> roles) {
        this.name = name;
        this.url = url;
        this.method = method;
        this.serverid = serverid;
        this.resourceType = resourceType;
        this.roles = roles;
    }

    public ResourceEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getServerid() {
        return serverid;
    }

    public void setServerid(String serverid) {
        this.serverid = serverid;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
