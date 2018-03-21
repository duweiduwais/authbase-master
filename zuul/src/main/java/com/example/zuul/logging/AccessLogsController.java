package com.example.zuul.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccessLogsController {

    @Autowired
    private AccessLogsRepository accessLogsRepository;

    @GetMapping("logs")
    public void logs(@RequestParam String context,@RequestParam String accessUser){
        AccessLogs accessLogs = new AccessLogs(context,accessUser);
        accessLogsRepository.save(accessLogs);
    }
}
