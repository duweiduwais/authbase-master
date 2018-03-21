package com.duwei.security.web;

import com.duwei.commonsspringbootstarter.vo.Menu;
import com.duwei.security.resource.RoleEntity;
import com.duwei.security.resource.UserRepository;
import com.duwei.security.service.AdminService;
import com.duwei.security.service.MenuServer;
import com.duwei.security.token.Token;
import com.duwei.security.token.TokenServer;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TokenServer tokenServer;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuServer menuServer;

    @GetMapping("login")
    public String login(Model model, String errorMsg) {
        if (!Strings.isNullOrEmpty(errorMsg))
            model.addAttribute("errorMsg", "errormsg." + errorMsg);
        return "login";
    }

    @PostMapping("login")
    public String loginPost(Model model, HttpServletRequest request) {
        List<RoleEntity> roles = adminService.getRoles();
        model.addAttribute("menuhead", Lists.newArrayList("role.roleName", "role.roleShowName"));
        model.addAttribute("roles", roles);

        String userName = request.getParameter("username");
        String pwd = request.getParameter("password");
        Token token = null;
        try {
            token = tokenServer.getToken(userName, pwd, "", "", "");
        } catch (Exception e) {
            token = null;
        }

        if (token != null) {
            model.addAttribute("token", token.getToken());
            List<Menu> menus = menuServer.getMenusByUserName(userName);
            model.addAttribute("menus", menus);
        }
        return "roles";
    }

    @GetMapping("table")
    public String table(Model model) {

        return "table";
    }

    @GetMapping("error")
    public String error(Model model) {
        return "error";
    }

}
