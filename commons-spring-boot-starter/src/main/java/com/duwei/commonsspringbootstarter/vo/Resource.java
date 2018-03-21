package com.duwei.commonsspringbootstarter.vo;

import lombok.Data;


public class Resource {

    private String name;

    private String url;

    private String method;

    private String serverid;

    private String menuIf;

    public Resource() {
    }

    public Resource(String name, String url, String method, String serverid) {
        this.name = name;
        this.url = url;
        this.method = method;
        this.serverid = serverid;
    }

    public Resource(String name, String url, String method, String serverid, String menuIf) {
        this.name = name;
        this.url = url;
        this.method = method;
        this.serverid = serverid;
        this.menuIf = menuIf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getServerid() {
        return serverid;
    }

    public void setServerid(String serverid) {
        this.serverid = serverid;
    }

    public String getMenuIf() {
        return menuIf;
    }

    public void setMenuIf(String menuIf) {
        this.menuIf = menuIf;
    }
}
