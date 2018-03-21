package com.duwei.pay;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.duwei.securityspringbootstarter"})
@EnableEurekaClient
public class PayApplication {

	@Autowired
	private PayRepository payRepository;

	public static void main(String[] args) {
		SpringApplication.run(PayApplication.class, args);
	}

	@PostConstruct
	public void initData(){
		String[] payName = {"合同支付","基础建设支付","水电支付","房租支付"};
		List<PayPo> payPos = Lists.newArrayList();

		for (String str:payName){

			PayPo payPo = new PayPo(str, BigDecimal.valueOf(5000));
			payPos.add(payPo);
		}

		payRepository.saveAll(payPos);
	}
}
