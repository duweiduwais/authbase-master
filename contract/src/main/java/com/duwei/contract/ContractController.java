package com.duwei.contract;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContractController {

    @Autowired
    private ContractRepository contractRepository;


    @GetMapping("contract/list")
    public List<Contract> getContractList(){

        return contractRepository.findAll();
    }

    @PostMapping("list")
    public List<String> getList(){
        return Lists.newArrayList("aaa","sdfasdf","sdes");
    }

}
