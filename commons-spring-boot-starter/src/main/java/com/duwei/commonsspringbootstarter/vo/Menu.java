package com.duwei.commonsspringbootstarter.vo;



import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String name;
    private String href;
    private boolean hasSubmenu = false;
    private boolean isActive = false;
    private List<Menu> subMenu =new ArrayList();

    public Menu() {
    }

    public Menu(String name, String href) {
        this.name = name;
        this.href = href;

        this.isActive=true;
    }


    public Menu(String name, String href, boolean isActive) {
        this.name = name;
        this.href = href;
        this.isActive = isActive;
    }

    public void addSubMenu(Menu... menus){
        for (Menu menu : menus){
            this.subMenu.add(menu);
        }
        this.hasSubmenu = true;
    }

    public List<Menu> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<Menu> subMenu) {
        this.subMenu = subMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isHasSubmenu() {
        return this.hasSubmenu;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
