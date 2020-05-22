package com.ajeesia.personal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: java类作用描述
 * @Author: ajeesia
 * @UpdateDate: 2020/4/1 15:48
 * @Version: 1.0
 */
@Controller
public class LoginController {
    @RequestMapping("/test")
    public String test(){
//        System.out.println("hello world");
        return "index";
    }
}
