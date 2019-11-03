package com.ajeesia.personal.config;

import com.ajeesia.personal.entity.SysMenu;
import com.ajeesia.personal.entity.SysRole;
import com.ajeesia.personal.entity.SysUser;
import com.ajeesia.personal.service.SysUserService;
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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 自定义realm，实现授权与认证
 * @Author: ajeesia
 * @UpdateDate: 2019/10/27 19:54
 * @Version: 1.0
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("==执行授权==");
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        if(sysUser!=null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 角色与权限字符串集合
            Collection<String> roleCollection = new HashSet<>();
            // 权限与字符春串集合
            Collection<String> permissionCollection = new HashSet<>();
            // 读取并赋值用户角色与权限
            Set<SysRole> roles = sysUser.getSysRoles();

            for (SysRole role:roles){
                roleCollection.add(role.getRoleName());
                Set<SysMenu> menus = role.getSysMenus();
                for (SysMenu menu:menus){
                    permissionCollection.add(menu.getMenuName());
                }
                info.addStringPermission(String.valueOf(permissionCollection));
            }
            info.addStringPermission(String.valueOf(roleCollection));
            return info;
        }
        return null;
    }

    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("==执行认证==");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser sysUser = sysUserService.findById(token.getUsername());
        if(sysUser == null){
            throw new UnknownAccountException();
        }
        ByteSource credentialsSalt = ByteSource.Util.bytes(sysUser.getUserName());
        return new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),
                credentialsSalt,getName());
    }

    // 模拟Shiro用户加密，假设用户密码为123456
    public static void main(String[] args){
        // 用户名
        String username = "admin";
        // 用户密码
        String password = "123456";
        // 加密方式
        String hashAlgorithName = "MD5";
        // 加密次数
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        Object obj = new SimpleHash(hashAlgorithName, password,
                credentialsSalt, hashIterations);
        System.out.println(obj);
    }
}
