package com.duwei.security.resource;

import com.duwei.commonsspringbootstarter.base.BaseRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends BaseRepository<UserEntity,Long>{

    List<UserEntity> findByName(@Param("name") String name);
}
