package com.duwei.security.resource;

import com.duwei.commonsspringbootstarter.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
@RepositoryRestResource(collectionResourceRel = "roles", path = "roles")
public interface RoleRepository extends BaseRepository<RoleEntity,Long>{
}
