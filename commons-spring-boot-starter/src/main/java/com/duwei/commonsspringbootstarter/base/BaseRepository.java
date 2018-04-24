package com.duwei.commonsspringbootstarter.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T> {



}
