package com.ajeesia.personal.controller;

import com.ajeesia.personal.Result.Result;
import com.ajeesia.personal.entity.SysUser;
import com.ajeesia.personal.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.util.List;

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
        SysUser sysUser = sysUserService.getById(1);
        System.err.println(sysUser);
        return "hello world";
    }

    @GetMapping("/list")
    public Result sysUserList() {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        Result result = new Result();
        List<SysUser> sysUserList =  sysUserService.list();
        if (sysUserList.size() > 0) {
            result.setCode(200);
            result.setData(sysUserList);
            result.setMsg("暂无数据");
        }
        return result;
    }



    @GetMapping("/setAccount")
    public void setAccount(){

    }
}
