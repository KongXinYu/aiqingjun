package com.aiqingjun.framework.config;

import com.aiqingjun.framework.shiro.realm.SystemUserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * shiro配置类
 *
 * @author: WuZhenYu
 * @create: 2019-08
 */
@Configuration
public class ShiroConfig {

    @Value("${shiro.session.expireTime}")
    private String expireTime;

    @Value("${shiro.session.validationInterval}")
    private String validationInterval;

    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    @Value("${shiro.user.unauthorizedUrl}")
    private String unauthorizeUrl;

    @Value("${shiro.user.indexUrl}")
    private String indexUrl;

    /**
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // shiro核心安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 身份认证失败跳转到登录页
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        // 权限认证失败
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizeUrl);
        // Shiro连接约束配置，即过滤链定义
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断，因为前端模板采用了thymeleaf，这里不能直接使用 ("/static/**", "anon")来配置匿名访问，必须配置到每个静态目录
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/aiqingjun/**", "anon");
        // 退出logout地址，shiro去清除session
        filterChainDefinitionMap.put("/logout", "logout");
        // 不需要拦截的访问
        filterChainDefinitionMap.put(loginUrl, "anon");
        // 所有请求需要认证
        filterChainDefinitionMap.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * shiro核心安全管理器
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(systemUserRealm());
        return securityManager;
    }

    /**
     * 自定义Realm
     */
    @Bean
    public SystemUserRealm systemUserRealm() {
        SystemUserRealm systemUserRealm = new SystemUserRealm();
        return systemUserRealm;
    }

}
