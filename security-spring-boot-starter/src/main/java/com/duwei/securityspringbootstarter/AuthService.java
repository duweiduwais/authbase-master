package com.duwei.securityspringbootstarter;

import com.duwei.commonsspringbootstarter.vo.Menu;
import com.duwei.commonsspringbootstarter.vo.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient("security")
public interface AuthService {

    @GetMapping("getToken")
    String getToken(@RequestParam("userName") String userName,@RequestParam("pwd") String pwd);


    @GetMapping("authToken")
    boolean authToken(@RequestParam("token") String token);


    @GetMapping("getUserByToken")
     String getUserByToken(@RequestParam("token") String token);


    @PostMapping("admin/addResource")
    boolean addResource(@RequestBody(required = false)List<Resource> resources);

    @PostMapping("admin/getMenusByAppname")
    List<Menu> getMenusByAppname(@RequestBody(required = false) String appName);

    @PostMapping("admin/getMenusByUsername")
   List<Menu> getMenusByUsername(@RequestBody(required = false)  String userName);
}
