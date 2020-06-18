package com.ajeesia.personal.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(
            @RequestParam("username")String username, @RequestParam("password")String password){
//        // 1、获取subject
//        Subject subject = SecurityUtils.getSubject();
//        // 2、封装用户数据
//        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
//        // 3、执行登录方法
//        try{
//            subject.login(token);
//            return "登录成功";
////            return "redirect:/main";
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return "aa";
        return null;
    }
}
