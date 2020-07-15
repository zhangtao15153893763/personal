package com.ajeesia.personal.service;

import com.ajeesia.personal.entity.SysUser;
import com.ajeesia.personal.mapper.SysUserMapper;
import com.ajeesia.utils.UUIDUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
    @Resource
    UUIDUtils uuidUtils;

    public SysUser getUserByUserName(String username) {
        return sysUserMapper.getUserByUserName(username);
    }

    @Override
    public boolean save(SysUser entity) {
        String uuid = uuidUtils.generate32UUID();
        String username = entity.getUserName();
        String password = entity.getPassword();

        // 用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(username);

        /*
         * MD5加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */
        String newPwd = new SimpleHash("MD5",password,salt,1024).toHex();

        // 判断是否已经有该账户
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (sysUser != null) { // 账户已存在
            return false;
        }

        //
        Integer i = 0;
        entity.setId(i++);
        entity.setPassword(newPwd);
        entity.setSalt(username);
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        sysUserMapper.insert(entity);
        return true;
    }
}
