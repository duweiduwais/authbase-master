package com.duwei.security.web;

import com.duwei.commonsspringbootstarter.base.AbstractEntity;
import com.duwei.commonsspringbootstarter.base.BaseRepository;
import com.duwei.security.resource.RoleEntity;
import com.duwei.security.resource.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("role/list")
    public List<RoleEntity> getRoleList(){
        return roleRepository.findAll();
    }

//    @GetMapping("{tablename}/list/all")
//    public <T extends AbstractEntity>  List<T> getListByTablename(@PathVariable("tablename")String tablename){
//        BaseRepository baseDao = (BaseRepository) applicationContext.getBean(tablename+"Repository");
//        return baseDao.findAll();
//    }
}
