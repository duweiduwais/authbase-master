package com.duwei.securityspringbootstarter;

import com.duwei.commonsspringbootstarter.base.ParseController;
import com.duwei.commonsspringbootstarter.vo.Menu;
import com.duwei.commonsspringbootstarter.vo.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;


@Configuration
@ConditionalOnClass({EnableFeignClients.class, AuthService.class})
public class AuthServerAutoConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AuthService authService;

    @Value("${spring.application.name}")
    private String applicationName;



    @Bean
    @ConditionalOnMissingBean(Menu.class)
    public Menu menu(){
        return  new Menu();
    }

    @PostConstruct
    public void initData1() {
        if(!"gateway".equals(applicationName)) {
            List<Resource> resourceList = ParseController.getResourceList(applicationContext, applicationName);
            if (!resourceList.isEmpty() && !"auth".equals(applicationName)) {
                authService.addResource(resourceList);
            }
        }
    }


}

