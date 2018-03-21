package com.duwei.security.vo;

import com.duwei.commonsspringbootstarter.base.ResourceType;
import com.duwei.commonsspringbootstarter.vo.Menu;
import com.duwei.commonsspringbootstarter.vo.Resource;
import com.duwei.security.resource.ResourceEntity;
import com.duwei.security.resource.UserEntity;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class ConvPoToVo {

     public static List<ResourceEntity> convResouceToEntity(List<Resource> resource){
         Preconditions.checkArgument(resource != null && !resource.isEmpty()) ;

         List<ResourceEntity> res = Lists.newArrayList();
         resource.forEach(resourceV1 -> res.add(ConvPoToVo.convResourceToEntity(resourceV1)));
         return  res;
     }

     public static List<Resource> convResourceEntityToVo(List<ResourceEntity> resourceEntities){
         return resourceEntities.stream()
                 .map(r -> new Resource(r.getName(),r.getUrl(),r.getMethod(),r.getServerid()))
                 .collect(Collectors.toList());
     }

     public static  User convUserEntityToUser(UserEntity userEntity){
          return  new User(userEntity.getName(),userEntity.getAddress());
     }

    public static List<User> convUserEntityToUser(List<UserEntity> userEntitys) {

         return userEntitys.stream()
               .map(userEntity -> new User(userEntity.getName(),userEntity.getAddress()))
               .collect(Collectors.toList());
    }

     public static  ResourceEntity convResourceToEntity(Resource resource){
         ResourceEntity resourceEntity = new ResourceEntity();
         resourceEntity.setMethod(resource.getMethod());
         resourceEntity.setName(resource.getName());
         resourceEntity.setServerid(resource.getServerid());
         resourceEntity.setUrl(resource.getUrl());
         String ifMenu = resource.getMenuIf();
         if(!Strings.isNullOrEmpty(ifMenu) && "1".equals(ifMenu)) {
             resourceEntity.setResourceType(ResourceType.MENU);
         }else {
             resourceEntity.setResourceType(ResourceType.LINK);
         }
         return resourceEntity;
     }

     public  static List<Menu> convResorceEntityToMenu(List<ResourceEntity> resourceEntities){
         Preconditions.checkArgument(resourceEntities != null);

         return resourceEntities.stream()
                 .map(re -> new Menu(re.getName(),re.getUrl()))
                 .collect(Collectors.toList());
     }
}
