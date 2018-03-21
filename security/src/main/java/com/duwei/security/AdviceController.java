package com.duwei.security;

import com.duwei.commonsspringbootstarter.vo.Menu;
import com.duwei.security.resource.UserRepository;
import com.duwei.security.service.MenuServer;
import com.duwei.security.token.TokenServer;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class AdviceController {


    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuServer menuServer;

    @Autowired
    private TokenServer tokenServer;

    @ModelAttribute
    public void setUserName(Model model,String token){
         model.addAttribute("token",token);
    }

    @ModelAttribute
    public void setMenu(Model model, String token){
        if(!Strings.isNullOrEmpty(token)){
            String userName = tokenServer.getUserName(token);
            List<Menu> menus = menuServer.getMenusByUserName(userName);
            model.addAttribute("menus", menus);
        }
    }

}
