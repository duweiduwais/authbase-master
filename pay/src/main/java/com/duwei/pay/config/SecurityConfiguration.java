package com.duwei.pay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers("/**")
               .permitAll();

        //允许所有用户访问”/”和”/home”
//        http.authorizeRequests().
//                antMatchers( "/css/**", "/js/**", "/images/**",
//                        "/lib/**", "/skin/**"
//                        , "/bootstrap/**"
//                        , "/build/**"
//                        , "/documentation/**"
//                        , "/pages/**"
//                )
//                .permitAll()
//                //其他地址的访问均需验证权限
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                //指定登录页是”/login”
//                .loginPage("/security/login")
//                .permitAll()
//                //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
//               // .successHandler(loginSuccessHandler())//code3
//                .and()
//                .logout()
//               // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                //退出登录后的默认网址是”/home”
//                .logoutSuccessUrl("/security/login")
//                .permitAll()
//                .invalidateHttpSession(true);
//                .and()
                //登录后记住用户，下次自动登录
                //数据库中必须存在名为persistent_logins的表
                //建表语句见code15

                // 这里是核心
//                .rememberMe()
//                .tokenValiditySeconds(1209600)
                //指定记住登录信息所使用的数据源
//                .tokenRepository(tokenRepository());//code4

    }
}
