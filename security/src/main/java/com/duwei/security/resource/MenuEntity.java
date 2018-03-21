package com.duwei.security.resource;


import com.duwei.commonsspringbootstarter.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="DW_Menu")
public class MenuEntity extends AbstractEntity {

    @ManyToOne
    private ResourceEntity menu;

    private String menuName;

    private String menuNameZH;

    private String menuNameEN;

    private TrueAndFalse directory = TrueAndFalse.NO;

    private Long parentMenu;

    public MenuEntity() {
    }

    public MenuEntity(String menuName, String menuNameZH, String menuNameEN) {
        this.menu = null;
        this.menuName = menuName;
        this.menuNameZH = menuNameZH;
        this.menuNameEN = menuNameEN;
        this.directory = TrueAndFalse.YES;
        this.parentMenu = 0L;}

    public MenuEntity(ResourceEntity menu, String menuName, String menuNameZH, String menuNameEN, Long parentMenu) {
        this.menu = menu;
        this.menuName = menuName;
        this.menuNameZH = menuNameZH;
        this.menuNameEN = menuNameEN;
        this.directory = TrueAndFalse.NO;
        this.parentMenu = parentMenu;
    }

    public ResourceEntity getMenu() {
        return menu;
    }

    public TrueAndFalse getDirectory() {
        return directory;
    }

    public void setDirectory(TrueAndFalse directory) {
        this.directory = directory;
    }

    public void setMenu(ResourceEntity menu) {
        this.menu = menu;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuNameZH() {
        return menuNameZH;
    }

    public void setMenuNameZH(String menuNameZH) {
        this.menuNameZH = menuNameZH;
    }

    public String getMenuNameEN() {
        return menuNameEN;
    }

    public void setMenuNameEN(String menuNameEN) {
        this.menuNameEN = menuNameEN;
    }

    public Long getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Long parentMenu) {
        this.parentMenu = parentMenu;
    }
}
