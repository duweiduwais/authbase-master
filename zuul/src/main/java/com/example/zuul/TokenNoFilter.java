package com.example.zuul;

import com.example.zuul.logging.AccessLogService;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TokenNoFilter extends ZuulFilter {

    @Autowired
    private AccessLogService accessLogService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public boolean shouldFilter() {

        RequestContext ctx = RequestContext.getCurrentContext();
        String loginSuccess = (String) ctx.get("loginSuccess");
        boolean bol = !Strings.isNullOrEmpty(loginSuccess) && "true".equals(loginSuccess);
        return !bol;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String token = request.getParameter("token");
        String tokenR = (String) ctx.get("token");
        String method = request.getMethod();
        String requestURI = request.getRequestURI();


        boolean ignorePath = method.equals("GET") && requestURI.equals("/security/login");
        if (ignorePath) return null;
        String errorMsg = (String)ctx.get("sendRedirectToLogin");


            try {
                ctx.getResponse().sendRedirect("http://localhost:1111/security/login?errorMsg=login");
            } catch (IOException e1) {
                accessLogService.addAccessLog("转发登录页面异常", "nousername");
                e1.printStackTrace();
            }


        return null;
    }
}
