package com.duwei.security.service;

import com.duwei.commonsspringbootstarter.vo.Resource;
import com.duwei.security.resource.ResourceEntity;
import com.duwei.security.resource.ResourceRepository;
import com.duwei.security.vo.ConvPoToVo;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServerImpl implements ResourceServer {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public boolean saveResource(List<Resource> resource) {

        List<ResourceEntity> resources = saveResourceR(resource);
        if(resources == null || resources.isEmpty()) return  false;
        return  true;
    }

    @Override
    public List<ResourceEntity> saveResourceR(List<Resource> resource) {
        List<ResourceEntity> res = ConvPoToVo.convResouceToEntity(resource);
        List<ResourceEntity> resources = resourceRepository.saveAll(res);
        return  resources;
    }

    @Override
    public List<ResourceEntity> findResourcesByServiceId(String serviceId) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(serviceId));
        return  resourceRepository.findAll((root,cq,cb) -> cb.equal(root.get("serverid").as(String.class),serviceId));
    }
}
