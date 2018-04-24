package com.duwei.commonsspringbootstarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(EntityManager.class)
public class WebMvcConfig extends ThymeleafAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(AutoCrudController.class)
    public AutoCrudController autoCrudController(){
        return new AutoCrudController();
    }
}