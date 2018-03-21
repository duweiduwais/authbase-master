package com.duwei.security.service;

import com.duwei.commonsspringbootstarter.base.BusinessException;
import com.duwei.commonsspringbootstarter.base.ResourceType;
import com.duwei.commonsspringbootstarter.vo.Menu;
import com.duwei.security.resource.*;
import com.duwei.security.token.Token;
import com.duwei.security.token.TokenServer;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MenuServerImpl implements MenuServer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private TokenServer tokenServer;

    @Override
    public List<Menu> getMenusByUserName(String userName) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(userName));
        Optional<UserEntity> userEntity = userRepository.findOne((root, cq, cb) -> cb.equal(root.get("name").as(String.class),userName));
        List<Menu> menus = null;
        if(userEntity.isPresent()) {
             menus = getMenusByUserId(userEntity.get().getId());
        }
        return menus;
    }

    @Override
    public List<Menu> getMenusByUserId(Long userId) {
        Preconditions.checkArgument(userId != null);
        if(!userRepository.existsById(userId)) throw new BusinessException("getMenus userId not found");
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        Set<RoleEntity> roles = userEntity.get().getRoles();
        final Set<ResourceEntity> resources = Sets.newHashSet();
        roles.forEach(role -> {
            resources.addAll(role.getResources());
        });
        String userName = userEntity.get().getName();
        Optional<Token> token = tokenServer.getTokenByUserName(userName);
        String tokenURI = "";
        if(token.isPresent()){
            tokenURI = "?token="+token.get().getToken();
        }
        final String tokenURL = tokenURI;

        return resources.stream()
                .filter(resource -> resource.getResourceType().equals(ResourceType.MENU))
                .map(resource -> new Menu(resource.getServerid()+"."+resource.getName(),"/"+resource.getServerid()+"/"+resource.getUrl()+tokenURL))
                .sorted(Comparator.comparing(Menu::getName))
                .collect(Collectors.toList());
//        List<MenuEntity> menuEntities = menuRepository.findAll();
//        return menuEntities.stream()
//        .filter(menuEntity -> menuEntity.getDirectory().equals(TrueAndFalse.NO))
//        .map(menuEntity -> new MenuResource(menuEntity.getMenuNameZH(),"/"+menuEntity.+"/"+menuEntity.getMenu().getUrl()))
//        .collect(Collectors.toList());
    }
}
