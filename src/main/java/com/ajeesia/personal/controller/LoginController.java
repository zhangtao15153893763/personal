package com.ajeesia.personal.controller;

import com.ajeesia.personal.Result.Result;
import com.ajeesia.personal.entity.SysUser;
import com.ajeesia.personal.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description: java类作用描述
 * @Author: ajeesia
 * @UpdateDate: 2020/4/1 15:48
 * @Version: 1.0
 */
@Controller
public class LoginController {

    @Resource
    private SysUserService sysUserService;

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
            @RequestParam("username")String username, @RequestParam("password")String password, Model model){
        // 1、获取subject
        Subject subject = SecurityUtils.getSubject();
//        // 2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        // 3、执行登录方法
        try {
            subject.login(token);
//            return "redirect:/main";
        } catch (IncorrectCredentialsException ice){
            // 密码不正确
            model.addAttribute("msg","密码不正确");
        } catch (UnknownAccountException uae) {
            // 账号不存在
            model.addAttribute("msg","账号不存在");
        } catch (AuthenticationException ae) {
            // 状态不正常
            model.addAttribute("msg","状态不正常");
        }

        if (subject.isAuthenticated()) {
            // 认证成功
            model.addAttribute("msg","认证成功");
            model.addAttribute("username",username);
            return "success";
        } else {
            token.clear();
            return "login";
        }
    }

    @RequestMapping("/regist")
    public String regist(){
//        System.out.println("hello world");
        return "regist";
    }


    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result<String> add(SysUser sysUser) {
        Result result = new Result();
        boolean isSuccess =  sysUserService.save(sysUser);
        if (isSuccess) { // 成功
            result.setMsg("success");
            result.setCode(200);
            return result;
        }
        result.setCode(500);
        return result;
    }
}
