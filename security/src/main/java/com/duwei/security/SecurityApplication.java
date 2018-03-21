package com.duwei.security;

import com.duwei.commonsspringbootstarter.base.ParseController;
import com.duwei.commonsspringbootstarter.vo.Resource;
import com.duwei.security.resource.*;
import com.duwei.security.service.ResourceServer;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Value("${spring.application.name}")
	private String applicationName;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResourceServer resourceServer;

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void initData1() {
		List<Resource> resource = ParseController.getResourceList(applicationContext,applicationName);

		List<ResourceEntity> resourceEntities = resourceServer.saveResourceR(resource);
		List<UserEntity> userList = Lists.newArrayList();

		for(int i=0;i<10;i++){
			userList.add(new UserEntity("duwei"+i));

		}
		List<UserEntity> userEntities = userRepository.saveAll(userList);
		RoleEntity roleEntity = new RoleEntity("Admin","系统管理员");
		RoleEntity roleEntity1 = new RoleEntity("User","普通用户");
		List<RoleEntity> roles = Lists.newArrayList(roleEntity,roleEntity1);
		List<RoleEntity> roleEntities = roleRepository.saveAll(roles);

		if(roleEntities != null ){
			RoleEntity role = roleEntities.get(0);
			role.setUsers(Sets.newHashSet(userEntities));
			role.setResources(Sets.newHashSet(resourceEntities));
			roleRepository.save(role);
		}

		ResourceEntity  roleR = getResourceEntityByName("getRoles",resourceEntities);
		ResourceEntity  userR = getResourceEntityByName("getUsers",resourceEntities);
		ResourceEntity  resourceR = getResourceEntityByName("getResources",resourceEntities);

		MenuEntity menuEntity = new MenuEntity("admin","管理","admin");
		MenuEntity mR = menuRepository.save(menuEntity);
		MenuEntity roleM = new MenuEntity(roleR,"role","角色","role",mR.getId());
		MenuEntity userM = new MenuEntity(userR,"user","用户","user",mR.getId());
		MenuEntity resourceM = new MenuEntity(resourceR,"resource","资源","resource",mR.getId());
        List<MenuEntity> ms = Lists.newArrayList(roleM,userM,resourceM);
        menuRepository.saveAll(ms);
	}

	private ResourceEntity getResourceEntityByName(String resourceName,List<ResourceEntity> resourceEntities){
		List<ResourceEntity> rs = resourceEntities.stream()
				.filter(r -> r.getName().equals(resourceName))
				.collect(Collectors.toList());
		return rs.get(0);
	}

}
