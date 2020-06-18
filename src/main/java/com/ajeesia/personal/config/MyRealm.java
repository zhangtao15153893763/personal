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
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 自定义realm，实现授权与认证
 * @Author: ajeesia
 * @UpdateDate: 2019/10/27 19:54
 * @Version: 1.0
 */
//public class MyRealm extends AuthorizingRealm {
//
//}