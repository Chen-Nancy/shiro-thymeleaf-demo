package com.nancy.shirothymeleafdemo.config;

import com.nancy.shirothymeleafdemo.shiro.UserRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chen
 * @date 2020/6/3 0:47
 */
@Configuration
public class ShiroConfig {
    /**
     * 注册cookie管理器
     *
     * @return
     */
    private CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //记住我cookie生效时间6小时,单位秒
        simpleCookie.setMaxAge(6 * 60 * 60);
        cookieRememberMeManager.setCookie(simpleCookie);
        //设置AES加密密钥
        cookieRememberMeManager.setCipherKey(Base64.decode("GhrF5zLfq1Dtadd1jlohhA=="));
        return cookieRememberMeManager;
    }

    /**
     * 注册安全管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //将自己的realm加入安全管理器
        manager.setRealm(userRealm);
        //加入rememberMe的cookie管理器
        manager.setRememberMeManager(cookieRememberMeManager());
        return manager;
    }

    /**
     * 注册权限管理通知类（开启shiro权限验证注解）
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(defaultWebSecurityManager);
        return advisor;
    }

    /**
     * 注册控制层代理对象
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * 注册shiro生命周期处理器
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 定义shiroFilter规则
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //注册securityManager
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //拦截器+配置登录和登录成功之后的url
        //LinkHashMap是有序的，shiro会根据添加的顺序进行拦截，匹配到过滤器后就执行该过滤器不会在继续向下查找过滤器
        Map<String, String> ruleMap = new LinkedHashMap<>();
        //配置不会被拦截地址规则
        //anon：所有的url都可以不登陆的情况下访问
        //authc：所有url都必须认证通过才可以访问
        //user：所有url都可以在rememberMe下访问
        ruleMap.put("/static/**", "anon");
        ruleMap.put("/favicon.ico", "anon");
        ruleMap.put("/page/login", "anon");
        ruleMap.put("/user/login", "anon");
        ruleMap.put("/logout", "logout");
        //如果不满足上方所有的规则，则需要进行登录验证
        ruleMap.put("/**", "user");
        //未登录时重定向的网页地址
        shiroFilterFactoryBean.setLoginUrl("/page/login");
        //将地址过滤规则设置到Filter工厂中
        shiroFilterFactoryBean.setFilterChainDefinitionMap(ruleMap);
        //返回
        return shiroFilterFactoryBean;
    }
}
