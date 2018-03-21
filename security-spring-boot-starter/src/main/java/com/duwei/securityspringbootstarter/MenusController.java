package com.duwei.securityspringbootstarter;


import com.duwei.commonsspringbootstarter.vo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MenusController {

    @Autowired
    private AuthService authService;

    @Value("${spring.application.name}")
    private String applicationName;


    @GetMapping("menu/list")
    public  String menuList(Model model){
        List<Menu> menus = authService.getMenusByAppname(applicationName);
        model.addAttribute("menus" , menus);
        return "menuList";
    }

}
