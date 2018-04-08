package com.duwei.security.web;

import com.duwei.commonsspringbootstarter.base.MenuResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EurekaClientController {

    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("discovery/servers")
    @MenuResource
    public String getServersAll(Model model){
        List<String> list = discoveryClient.getServices();
        model.addAttribute("servers",list);
        return "servers";
    }
}
