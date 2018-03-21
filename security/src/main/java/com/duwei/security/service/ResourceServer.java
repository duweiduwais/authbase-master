package com.duwei.security.service;

import com.duwei.commonsspringbootstarter.vo.Resource;
import com.duwei.security.resource.ResourceEntity;

import java.util.List;

public interface ResourceServer {

    boolean saveResource(List<Resource> resource);

    List<ResourceEntity> saveResourceR(List<Resource> resource);

    List<ResourceEntity> findResourcesByServiceId(String serviceId);
}
