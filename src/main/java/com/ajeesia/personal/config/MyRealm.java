package com.ajeesia.personal.config;

import com.ajeesia.personal.entity.SysMenu;
import com.ajeesia.personal.entity.SysRole;
import com.ajeesia.personal.entity.SysUser;
import com.ajeesia.personal.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 自定义realm，实现授权与认证
 * @Author: ajeesia
 * @UpdateDate: 2019/10/27 19:54
 * @Version: 1.0
 */
public class MyRealm extends AuthorizingRealm {
    @Resource
    private SysUserService sysUserService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
      return new SimpleAuthorizationInfo();
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        // 1.获取用户输入的账号
        String username = (String)token.getPrincipal();
        // 2.通过username从数据库中查找到user实体
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",username);
        SysUser sysUser = sysUserService.getOne(queryWrapper);
        if(sysUser == null){
            return null;
        }
        // 3.通过SimpleAuthenticationInfo做身份处理
        // 第一个参数是从数据库中获取SysUser对象
        // 第二个参数是数据库获取的密码
        // 第三个参数是当前Realm的名称
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),getName());
        // 4.返回身份处理对象
        return simpleAuthenticationInfo;
    }

    private SysUser getUserByUserName(String username){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",username);
        SysUser sysUser = null;
        try {
            sysUser = sysUserService.getOne(queryWrapper);
        } catch (Exception e) {
        }
//        if (sysUser == null || "".equals(sysUser)) {
//            return null; // 不存在该用户
//        }
//
//        SysUser sysUser1 = new SysUser();
//        sysUser1.setUserName(sysUser.getUserName());


        return sysUser;
    }
}