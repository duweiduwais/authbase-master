package com.duwei.security.resource;

import com.duwei.commonsspringbootstarter.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "menus", path = "menus")
public interface MenuRepository extends BaseRepository<MenuEntity,Long>{
}
