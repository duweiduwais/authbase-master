package com.duwei.security.web;

import com.duwei.commonsspringbootstarter.base.MenuResource;
import com.duwei.commonsspringbootstarter.base.ResourceType;
import com.duwei.commonsspringbootstarter.vo.Menu;
import com.duwei.commonsspringbootstarter.vo.Resource;
import com.duwei.security.resource.*;
import com.duwei.security.service.AdminService;
import com.duwei.security.service.MenuServer;
import com.duwei.security.vo.ConvPoToVo;
import com.duwei.security.vo.User;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Predicate;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private  ResourceRepository resourceRepository;



   @Autowired
   private UserRepository userRepository;

   @Autowired
   private MenuServer menuServer;

    @GetMapping("users")
    @MenuResource
    public String getUsers(Model model)
    {
        List<User> users = adminService.getUsers();
        model.addAttribute("menuhead", Lists.newArrayList("user.userName","user.address"));

        model.addAttribute("users",users);
        return "users";
    }

    @GetMapping("roles")
    @MenuResource
    public String getRoles(Model model) {

        List<RoleEntity> roles = adminService.getRoles();
        model.addAttribute("menuhead", Lists.newArrayList("role.roleName","role.roleShowName"));
        model.addAttribute("roles",roles);
        return "roles";
    }

    @GetMapping("resources")
    @MenuResource
    public String getResources(Model model) {
        List<Resource> resources = adminService.getResources();
        model.addAttribute("menuhead", Lists.newArrayList("resource.name","resource.url","resource.method","resource.serverid"));
        model.addAttribute("resources",resources);
        return "resources";
    }

    @PostMapping("addResource")
    @ResponseBody
    public boolean addResource(@RequestBody(required = false)List<Resource> resources){
       return adminService.addResources(resources);
    }

    @PostMapping("getMenusByAppname")
    @ResponseBody
   public List<Menu> getMenusByAppname(@RequestBody(required = false)  String appName){
        List<ResourceEntity> resourceEntities = resourceRepository.findAll((root,cq,cb) -> {
            Predicate p1 = cb.equal(root.get("serverid").as(String.class),appName);
            Predicate p2 = cb.equal(root.get("resourceType").as(ResourceType.class),ResourceType.MENU);
            return  cb.and(p1,p2);
        });
        return ConvPoToVo.convResorceEntityToMenu(resourceEntities);
    }

    @PostMapping("getMenusByUsername")
    @ResponseBody
    public List<Menu> getMenusByUsername(@RequestBody(required = false)  String userName){

           return menuServer.getMenusByUserName(userName);
    }


}
