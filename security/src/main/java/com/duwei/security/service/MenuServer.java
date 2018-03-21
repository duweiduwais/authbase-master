package com.duwei.security.service;

import com.duwei.commonsspringbootstarter.vo.Menu;

import java.util.List;

public interface MenuServer {

    List<Menu>  getMenusByUserId(Long userId);

    List<Menu> getMenusByUserName(String userName);
}
