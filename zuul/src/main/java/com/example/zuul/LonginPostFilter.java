package com.example.zuul;

import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LonginPostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String token = (String)ctx.get("token");
        String loginSuccess = (String)ctx.get("loginSuccess");

        return !Strings.isNullOrEmpty(token) && !Strings.isNullOrEmpty(loginSuccess) && loginSuccess.equals("true");
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        String token = (String)ctx.get("token");
         response.setHeader("token",token);
        return null;
    }
}
