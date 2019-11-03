package com.ajeesia.personal.service;

import com.ajeesia.personal.entity.SysUser;
import com.ajeesia.personal.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: java类作用描述
 * @Author: ajeesia
 * @UpdateDate: 2019/9/1 19:59
 * @Version: 1.0
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser selectByPrimaryKey(String id){
        return sysUserMapper.selectByPrimaryKey(id);
    }

    public String queryById(String id){
        return sysUserMapper.queryById(id);
    }

    public SysUser findById(String userName){
        return sysUserMapper.findByName(userName);
    }

    public int setAccount(SysUser sysUser){
        return sysUserMapper.insert(sysUser);
    }
}
