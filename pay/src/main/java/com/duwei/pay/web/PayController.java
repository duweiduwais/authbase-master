package com.duwei.pay.web;

import com.duwei.commonsspringbootstarter.base.MenuResource;
import com.duwei.pay.PayPo;
import com.duwei.pay.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PayController {

    @Autowired
    private PayRepository payRepository;

    @GetMapping("pay/list")
    @MenuResource
    public String payList(Model model){
        List<PayPo> payPos = payRepository.findAll();
        model.addAttribute("payPos",payPos);
        return "payList";
    }
}
