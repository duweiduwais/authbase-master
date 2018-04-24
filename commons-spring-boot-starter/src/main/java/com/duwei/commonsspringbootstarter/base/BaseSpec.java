package com.duwei.commonsspringbootstarter.base;

import org.springframework.data.jpa.domain.Specification;

public interface BaseSpec<T> {
    Specification<T> getSpec(String filedName,String opt,Object value);
}
