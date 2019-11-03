package com.ajeesia.personal.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Durid配置类
 * @Author: ajeesia
 * @UpdateDate: 2019/9/2 22:23
 * @Version: 1.0
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        return new DruidDataSource();
    }

    /**
     *  配置监控服务器，
     *  不配置将会连接不上druid后台
     *  http://localhost:8080/druid/
     **/
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(
                new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();
        // druid后台管理员用户
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        // 是否能够重置数据
        initParams.put("resetEnable", "false");

        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     *  配置web监控的过滤器
     *  如果不配置这个类，在页面的Web模块功能不会开启
     **/
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(
                new WebStatFilter());
        // 添加过滤规则
        bean.addUrlPatterns("/*");
        Map<String,String> initParams = new HashMap<>();
        // 忽略过滤格式
        initParams.put("exclusions","*.js,*.css,*.icon,*.png,*.jpg,/druid/*");
        bean.setInitParameters(initParams);
        return  bean;
    }
}

