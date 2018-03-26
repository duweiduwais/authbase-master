package com.duwei.securityspringbootstarter;

import com.duwei.commonsspringbootstarter.vo.Menu;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import org.springframework.context.MessageSourceAware
@ControllerAdvice
public class AdviceController {


    @Autowired
    private AuthService authService;



    @ModelAttribute
    public void setUserName(Model model,String token){
         model.addAttribute("token",token);
    }

    @ModelAttribute
    public void setMenu(Model model, String token){


        if(!Strings.isNullOrEmpty(token)){
            String userName = authService.getUserByToken(token);
            List<Menu> menus = authService.getMenusByUsername(userName);
            model.addAttribute("menus", menus);
        }
    }

}
