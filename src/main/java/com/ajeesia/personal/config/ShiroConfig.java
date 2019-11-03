package com.ajeesia.personal.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: ajeesia
 * @UpdateDate: 2019/9/8 13:23
 * @Version: 1.0
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilter(
            @Qualifier("securityManager") DefaultWebSecurityManager securityManager
    ) {

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
        bean.setSecurityManager(securityManager);
        // 设置登录成功跳转Url
        bean.setSuccessUrl("/success");
        // 设置登录跳转Url
        bean.setLoginUrl("/login");
        // 设置未授权提示Url
        bean.setUnauthorizedUrl("/unauthorizedurl");

        /**
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * role：对应角色权限可访问
         **/
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/sys/user/login","anon");
        filterMap.put("/user/index","authc");
        filterMap.put("/vip/index","roles[vip]");
        filterMap.put("/druid/**", "anon");
        filterMap.put("/static/**","anon");

        // 直接访问接口需要在这里放行
        filterMap.put("/sys/user/say","anon");

        filterMap.put("/**","authc");
        filterMap.put("/logout", "logout");

        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }


    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(
            HashedCredentialsMatcher hashedCredentialsMatcher
    ){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager ();
        //关联realm
        securityManager.setRealm(myRealm(hashedCredentialsMatcher));
        return securityManager;
    }


    @Bean("myRealm")
    public MyRealm myRealm(@Qualifier("hashedCredentialsMatcher")
                           HashedCredentialsMatcher matcher){
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(matcher);
        return myRealm;
    }

    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher =
                new HashedCredentialsMatcher();
        // 指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        // 加密次数
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }


}
