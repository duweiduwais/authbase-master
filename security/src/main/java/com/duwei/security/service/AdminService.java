package com.duwei.security.service;


import com.duwei.commonsspringbootstarter.vo.Resource;
import com.duwei.security.resource.RoleEntity;
import com.duwei.security.vo.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {


    List<RoleEntity> getRoles();

    List<Resource> getResources();

    List<User> getUsers();

    Optional<RoleEntity> getAdminRole();

    boolean addResources(List<Resource> resources);

    RoleEntity allotResourceToRole(Long roleId, Long resourceId);
}
