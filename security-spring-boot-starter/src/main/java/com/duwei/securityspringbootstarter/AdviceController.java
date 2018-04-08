package com.duwei.securityspringbootstarter;

import com.duwei.commonsspringbootstarter.base.BusinessException;
import com.duwei.commonsspringbootstarter.vo.Menu;
import com.google.common.base.Strings;
import com.thoughtworks.xstream.core.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@ControllerAdvice
public class AdviceController {


    @Autowired
    private AuthService authService;

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<String> handleBusinessException(HttpServletRequest request, Exception ex,@ModelAttribute Model model) {
        String message = ex.getMessage();
        model.addAttribute("exMessage",message);
        if(isAjax(request)){
        return  new ResponseEntity(message,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        throw new RuntimeException(message);
    }

    boolean isAjax(HttpServletRequest request){
        return  (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString())) ;
    }


    @ModelAttribute
    public void setUserName(Model model,String token){
         model.addAttribute("token",token);
    }

    @ModelAttribute
    public void setMenu(Model model, String token){


        if(!Strings.isNullOrEmpty(token)){
            String userName = authService.getUserByToken(token);
            List<Menu> menus = authService.getMenusByUsername(userName);
            model.addAttribute("menus", menus);
        }
    }

}
