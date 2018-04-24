package com.duwei.pay.web;

import com.duwei.commonsspringbootstarter.base.BusinessException;
import com.duwei.commonsspringbootstarter.base.MenuResource;
import com.duwei.pay.PayPo;
import com.duwei.pay.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Locale;

@Controller
public class PayController {

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private MessageSource messageSource;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("pay/list")
    @MenuResource
    public String payList(Model model,Locale locale){

        String token = messageSource.getMessage("security.getToken",null, Locale.SIMPLIFIED_CHINESE);
        String token1 = messageSource.getMessage("security.getToken",null, locale);
        model.addAttribute("payToken",token);
        model.addAttribute("payToken1",token1);
        model.addAttribute("welcomeKey","welcome");
        List<PayPo> payPos = payRepository.findAll();
        model.addAttribute("payPos",payPos);
        System.out.println("serverPort------------------"+serverPort);
        return "payList";
    }

    @GetMapping("pay/list1")
    @MenuResource
    public String payList1(Model model){

        String token = messageSource.getMessage("security.getToken",null, Locale.SIMPLIFIED_CHINESE);
        List<PayPo> payPos = payRepository.findAll();
        model.addAttribute("payPos",payPos);
        return "payList";
    }

    @GetMapping("exception/json")
    @ResponseBody
    public String demoJsonException(){

        throw new BusinessException("aadfae");
    }

    @GetMapping("exception/page")
    public String demoPageException(){
        throw new BusinessException("aadfae");
    }
}
