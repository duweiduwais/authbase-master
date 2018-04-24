package com.duwei.security.token;


import com.duwei.commonsspringbootstarter.base.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(collectionResourceRel = "tokens", path = "tokens")
public interface TokenRepository extends BaseRepository<Token, Long> {


}
