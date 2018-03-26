package com.duwei.pay.web;

import com.duwei.commonsspringbootstarter.base.MenuResource;
import com.duwei.pay.PayPo;
import com.duwei.pay.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Locale;

@Controller
public class PayController {

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("pay/list")
    @MenuResource
    public String payList(Model model){
        String token = messageSource.getMessage("security.getToken",null, Locale.SIMPLIFIED_CHINESE);
        List<PayPo> payPos = payRepository.findAll();
        model.addAttribute("payPos",payPos);
        return "payList";
    }
}
