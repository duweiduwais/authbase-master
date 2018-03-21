package com.duwei.commonsspringbootstarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass(AutoResourcesLoad.class)
public class AutoResourceLoadConfiguration {

    @Bean
    @ConditionalOnMissingBean(AutoResourcesLoad.class)
    public AutoResourcesLoad autoResourcesLoad() {
        return new AutoResourcesLoad();
    }

}
