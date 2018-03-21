package com.duwei.commonsspringbootstarter.base;

import com.duwei.commonsspringbootstarter.vo.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParseController {

    public static List<Resource> getResourceList(ApplicationContext applicationContext, String applicationName) {
        String[] controllerName = applicationContext.getBeanNamesForAnnotation(Controller.class);
        List<Resource> resourceList = new ArrayList();
        for (String name : controllerName) {
            Object controllerBean = applicationContext.getBean(name);
            Annotation[] annotations = controllerBean.getClass().getAnnotations();

            RequestMapping requestMapping = controllerBean.getClass().getAnnotation(RequestMapping.class);
            Method[] methods = controllerBean.getClass().getMethods();
            for (Method m : methods) {
                Annotation[] annotationsMethod = m.getAnnotations();
                MenuResource menuResource = m.getAnnotation(MenuResource.class);

                for (Annotation annotation : annotationsMethod) {
                    Map map = AnnotationUtils.getAnnotationAttributes(annotation);
                    String[] values = (String[]) map.get("value");
                    if (values != null && values.length >= 1) {
                        String value = values[0];
                        String  separator = "";
                        if(value.indexOf("/") == -1) separator = "/";
                        if(requestMapping != null){
                            String[] pareatAnnotation = requestMapping.value();
                            if(pareatAnnotation != null && pareatAnnotation.length >= 1){
                                value = pareatAnnotation[0] + separator + value;
                            }
                        }
                        if(value.indexOf("/") == 0){
                            value = value.substring(1);
                        }
                        if(annotation instanceof GetMapping){
                            String ifMenu = menuResource == null ? "0" : "1";
                            Resource resource = new Resource(m.getName(),value,"GET",applicationName,ifMenu);
                            resourceList.add(resource);
                        }

                    }
                }
            }

        }
        return resourceList;

    }
}
