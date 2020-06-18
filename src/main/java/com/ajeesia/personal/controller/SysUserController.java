package com.ajeesia.personal.controller;

import com.ajeesia.personal.Result.Result;
import com.ajeesia.personal.entity.SysUser;
import com.ajeesia.personal.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Wrapper;
import java.util.Date;
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
        Result<List<SysUser>> result = new Result();
        queryWrapper.orderByDesc("create_time");
        List<SysUser> sysUserList =  sysUserService.list(queryWrapper);
        if (sysUserList.size() > 0) {
            result.setCode(200);
            result.setData(sysUserList);
        }
        return result;
    }

    @GetMapping("/queryById")
    public Result queryById(Integer id) {
        Result result = new Result();
        SysUser sysUser = sysUserService.getById(id);
        if (sysUser != null) {
            result.setData(sysUser);
            result.setCode(200);
        }
        return result;
    }

    @PostMapping("/add")
    public Result add(@RequestBody SysUser sysUser) {
        Result result = new Result();
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        if (sysUserService.save(sysUser)) {
            result.setCode(200);
            result.setMsg("操作成功");
        }
        return result;
    }

    @PostMapping("/editById")
    public Result editById(@RequestBody SysUser sysUser) {
        sysUserService.updateById(sysUser);
        return null;
    }

    @GetMapping("/del")
    public Result del(Integer id) {
        Result result = new Result();
        if (sysUserService.removeById(id)) {
            result.setCode(200);
            result.setMsg("操作成功");
        }
        return result;
    }
}
