package com.duwei.security.service;

import com.duwei.commonsspringbootstarter.vo.Resource;
import com.duwei.security.resource.*;
import com.duwei.security.vo.ConvPoToVo;
import com.duwei.security.vo.User;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ResourceServer resourceServer;

    @Override
    public List<RoleEntity> getRoles() {
        return roleRepository.findAll();

    }

    @Override
    public List<Resource> getResources() {
        return ConvPoToVo.convResourceEntityToVo(resourceRepository.findAll());

    }

    @Override
    public List<User> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return ConvPoToVo.convUserEntityToUser(userEntities);
    }

    @Override
    public Optional<RoleEntity> getAdminRole() {
        final String admin = "Admin";
        return roleRepository.findOne((root, cq, cb) -> cb.equal(root.get("roleName").as(String.class), admin));

    }

    @Override
    public boolean addResources(List<Resource> resources) {

        Preconditions.checkArgument(resources!=null && !resources.isEmpty());
        List<ResourceEntity> resourceEntities = ConvPoToVo.convResouceToEntity(resources);
        String serviceId = resourceEntities.get(0).getServerid();
        List<ResourceEntity> resourceEntityQuery = resourceServer.findResourcesByServiceId(serviceId);

        List<ResourceEntity> list = getSaveResourceEntityList(resourceEntities,resourceEntityQuery);

       return doSaveResourceEntitys(list);
    }

    private boolean doSaveResourceEntitys(List<ResourceEntity> list ){

        if(list != null && !list.isEmpty()) {
            List<ResourceEntity> rs = resourceRepository.saveAll(list);
            Optional<RoleEntity> adminRole = getAdminRole();
            if(adminRole.isPresent()){
                RoleEntity roleAdmin = adminRole.get();
                roleAdmin.addResources(rs);
                roleRepository.save(roleAdmin);
            }
            return rs != null ? rs.isEmpty() ? false : true : false;
        }
        return false;
    }

    private List<ResourceEntity> getSaveResourceEntityList(List<ResourceEntity> resourceEntities,List<ResourceEntity> resourceEntityQuery){
        Map<String,ResourceEntity> resMap = Maps.newHashMap();

        resourceEntities.forEach(re -> {
            String key = re.getMethod()+re.getName()+re.getServerid()+re.getUrl();
            resMap.put(key,re);
        });

        resourceEntityQuery.forEach(req -> {
            String key = req.getMethod()+req.getName()+req.getServerid()+req.getUrl();
            if(resMap.containsKey(key)){
            ResourceEntity r1 = resMap.get(key);
                if(r1 != null){
                    req.setResourceType(r1.getResourceType());
                }
            }
            resMap.put(key,req);
        });

        List<ResourceEntity> list = Lists.newArrayList(resMap.values());
        return list;

    }

    @Override
    public RoleEntity allotResourceToRole(Long roleId, Long resourceId) {
        Preconditions.checkArgument(roleId != null && resourceId != null);
        Optional<ResourceEntity> resource = resourceRepository.findById(resourceId);
        Optional<RoleEntity> role1 = roleRepository.findById(roleId);
        if (role1.isPresent()) {
            role1.get().addResource(resource);
            return roleRepository.save(role1.get());
        }
        return null;
    }
}
