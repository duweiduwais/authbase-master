package com.duwei.commonsspringbootstarter;

import com.duwei.commonsspringbootstarter.base.AbstractEntity;
import com.duwei.commonsspringbootstarter.base.BaseRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RestController
public class AutoCrudController {

    private static  final String repository = "Repository";
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ApplicationContext applicationContext;

    private BaseRepository baseDao;

    //得到所有记录
    @GetMapping("{tablename}s")
    public <T extends AbstractEntity>  List<T> getListByTableName(@PathVariable("tablename")String tablename) {
        return baseDao.findAll();
    }
    //得到一条记录
    @GetMapping("{tablename}s/{id}")
    public Optional<Object> getEntityById(@PathVariable("id")Long id){
        return baseDao.findById(id);
    }

    //得到所有记录（带分页）
    @GetMapping("{tablename}s/page")
    public <T extends AbstractEntity> Page<T> getListByTableNamePage(Pageable pageable){
        return baseDao.findAll(pageable);
    }

    @GetMapping("{tablename}s/query/{fieldName}/{opt}/{value}")
    public void getEntityByConditon(@PathVariable("tablename")String tablename,
                                    @PathVariable("fieldName")String fieldName,
                                    @PathVariable("opt")String opt,
                                    @PathVariable("value")Object value){
       // applicationContext.getBean(tablename+"Spec");
    }

    //删除所有
    @DeleteMapping("{tablename}s")
    public void deleteEntityAll(){
            baseDao.deleteAll();
    }

    //删除一条记录
   @DeleteMapping("{tablename}s/{id}")
   public void deleteEntity(@PathVariable("id")Long id){
           baseDao.deleteById(id);
   }
    @ModelAttribute
    private void getBaseRepositoryByTableName(@PathVariable("tablename")String tablename){
        if(Strings.isEmpty(tablename)) throw new NullPointerException("tablename is null");
        String tname = tablename + repository;
        this.baseDao = (BaseRepository) applicationContext.getBean(tname);
    }


}
