package com.duwei.contract;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.duwei.securityspringbootstarter"})
@EnableEurekaClient
public class ContractApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContractApplication.class, args);
	}

	@Autowired
	private ContractRepository contractRepository;

	@PostConstruct
	public  void initData(){
		List<String> contractNames = Lists.newArrayList("aaa","bbb","cccc","dddd");
		List<Contract> contracts =contractNames.stream()
				.map(c -> new Contract(c))
				.collect(Collectors.toList());
		contractRepository.saveAll(contracts);
	}
}
