package com.example.zuul;

import com.duwei.securityspringbootstarter.AuthService;
import com.example.zuul.logging.AccessLogService;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

public class LonginFilter extends ZuulFilter {

    @Autowired
    private AuthService authService;

    @Autowired
    private AccessLogService accessLogService;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String userName = request.getParameter("username");
        String pwd = request.getParameter("password");
        return !Strings.isNullOrEmpty(userName) || !Strings.isNullOrEmpty(pwd);
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String userName = request.getParameter("username");
        String pwd = request.getParameter("password");
        boolean bol = false;
        String errorMsg = "";
        try {
            if(Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(pwd)){
                errorMsg = "usernamenull";
            };
            String token =authService.getToken(userName,pwd);
            if(Strings.isNullOrEmpty(token)) {
                errorMsg = "userpwd";

            }else {
                ctx.set("token", token);
                accessLogService.addAccessLog("登陆认证成功", userName);
                ctx.set("loginSuccess", "true");
                bol = true;
            }
        } catch (Exception e) {
            bol = false;
            errorMsg = "zuulerror";
        }finally {
            accessLogService.addAccessLog(errorMsg,userName);
        }
        if(!bol){
            ctx.set("sendRedirectToLogin", errorMsg);

        }
        return null;
    }


}
