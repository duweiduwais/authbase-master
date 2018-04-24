package com.duwei.security.resource;

import com.duwei.commonsspringbootstarter.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "resources", path = "resources")
public interface ResourceRepository extends BaseRepository<ResourceEntity,Long>{
}
