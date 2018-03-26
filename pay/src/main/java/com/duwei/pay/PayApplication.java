package com.duwei.pay;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.UrlResource;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.duwei.securityspringbootstarter"})
@EnableEurekaClient
public class PayApplication {

	@Autowired
	private PayRepository payRepository;



	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(PayApplication.class, args);

	}

//	@Bean
//	public MessageSource dwMessageSource(){
//		return new DwMessageSource();
//	}

	@PostConstruct
	public void initData(){
//        String path = "classpath:il8n/message*.properties";
//		try {
//			Resource[] resources = configurableApplicationContext.getResources(path);
//			System.out.println(resources.length);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		String[] payName = {"合同支付","基础建设支付","水电支付","房租支付"};
		List<PayPo> payPos = Lists.newArrayList();

		for (String str:payName){

			PayPo payPo = new PayPo(str, BigDecimal.valueOf(5000));
			payPos.add(payPo);
		}

		payRepository.saveAll(payPos);
	}
}
