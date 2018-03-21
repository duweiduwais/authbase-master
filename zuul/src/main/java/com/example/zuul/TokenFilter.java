package com.example.zuul;

import com.duwei.securityspringbootstarter.AuthService;
import com.example.zuul.logging.AccessLogService;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TokenFilter extends ZuulFilter {

    @Autowired
    private AccessLogService accessLogService;


    @Autowired
    private AuthService authService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getParameter("token");
        return !Strings.isNullOrEmpty(token);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getParameter("token");
        boolean bol = authService.authToken(token);
        String username = authService.getUserByToken(token);

        if(bol){
            accessLogService.addAccessLog("Token认证成功",username);
            ctx.set("token",token);
            ctx.set("loginSuccess","true");
        }else {
            accessLogService.addAccessLog("Token认证不成功，转发错误页面",username);
            try {
                ctx.getResponse().sendRedirect("http://localhost:1111/security/error");
            } catch (IOException e1) {
                accessLogService.addAccessLog("Token认证不成功，转发错误页面异常",username);
                e1.printStackTrace();
            }

        }

        return null;
    }
}
