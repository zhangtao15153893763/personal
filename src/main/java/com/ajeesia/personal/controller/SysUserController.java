package com.ajeesia.personal.controller;

import com.ajeesia.personal.entity.SysUser;
import com.ajeesia.personal.service.SysUserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;

/**
 * @Description: java类作用描述
 * @Author: ajeesia
 * @UpdateDate: 2019/9/1 20:02
 * @Version: 1.0
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("/say")
    public String say(){
        return "hello world";
    }

    @GetMapping("/selectByPrimaryKey")
    public SysUser selectByPrimaryKey(@RequestParam("id") String id){
        return sysUserService.selectByPrimaryKey(id);
    }

    @GetMapping("/queryById")
    public String queryById(String id){
        return sysUserService.queryById(id);
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(
            @RequestParam("username")String username,@RequestParam("password")String password){
        // 1、获取subject
        Subject subject = SecurityUtils.getSubject();
        // 2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        // 3、执行登录方法
        try{
            subject.login(token);
            return "登录成功";
//            return "redirect:/main";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "aa";
//        return "login";
    }
}
