package com.example.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableFeignClients(basePackages = {"com.duwei.securityspringbootstarter"})
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

	@Bean
	public LonginFilter longinFilter(){
		return  new LonginFilter();
	}

	@Bean
	public TokenFilter tokenFilter(){
		return new TokenFilter();
	}

	@Bean
	public TokenNoFilter tokenNoFilter(){
		return  new TokenNoFilter();
	}

	@Bean
	public LonginPostFilter longinPostFilter(){
		return  new LonginPostFilter();
	}

}
