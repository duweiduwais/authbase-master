package com.duwei.commonsspringbootstarter.base;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;

public abstract class AbstractSpec<T> implements BaseSpec<T>{

    @Override
    public Specification<T> getSpec(String filedName, String opt, Object value) {
        return new Specification<T>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Predicate predicate = null;
                try {
                    Class<?> cls = getClassByfiledName(filedName);
                    if (opt.equals("eq"))
                        predicate = cb.equal(root.get(filedName).as(cls),value);
                    if(opt.equals("like"))
                        predicate = cb.like(root.get(filedName).as(String.class),"%"+value.toString()+"%");
                } catch (Exception ex){

                }
               return  predicate ;
           }
        };
    }

    abstract protected Class<T> getEntityClass();

    private Class<?> getClassByfiledName(String filedName) throws NoSuchFieldException {
        Class<T> cls = getEntityClass();
        Field f = cls.getDeclaredField(filedName);
        return f.getType();
    }
}
