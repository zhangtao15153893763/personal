package com.ajeesia.personal.service;

import com.ajeesia.personal.entity.SysUser;
import com.ajeesia.personal.mapper.SysUserMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: java类作用描述
 * @Author: ajeesia
 * @UpdateDate: 2019/9/1 19:59
 * @Version: 1.0
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper,SysUser> {
    @Autowired
    private SysUserMapper sysUserMapper;

}
