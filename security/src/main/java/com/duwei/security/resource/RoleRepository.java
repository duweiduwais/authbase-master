package com.duwei.security.resource;

import com.duwei.commonsspringbootstarter.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public interface RoleRepository extends BaseRepository<RoleEntity,Long>{
}
