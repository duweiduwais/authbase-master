package com.duwei.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private TokenServer tokenServer;

    @GetMapping("getToken")
    public String getToken(@RequestParam String userName,@RequestParam String pwd){
        if(!tokenServer.authUser(userName,pwd)) return null;
        Token token = tokenServer.getToken(userName,pwd,"","","");
        Token token1 = tokenServer.saveToken(token);
        return token1.getToken();
    }

    @GetMapping("authToken")
    public boolean authToken(@RequestParam String token){
        return tokenServer.authToken(token);
    }

    @GetMapping("getUserByToken")
    public String getUserByToken(@RequestParam String token){
        return tokenServer.getUserName(token);
    }
}
