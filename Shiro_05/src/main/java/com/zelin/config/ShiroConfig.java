package com.zelin.config;

import com.zelin.filter.CustomFormFilter;
import com.zelin.realm.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: Feng.Wang
 * @Company: Zelin.ShenZhen
 * @Description: 配置ShiroFactoryFilterBean
 * @Date: Create in 2019/4/17 09:28
 */
@Configuration
public class ShiroConfig {
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        //1.定义ShiroFilterFactoryBean对象
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //2.将securityManager与ShiroFilterFactoryBean对象绑定
        shiroFilter.setSecurityManager(securityManager);
        //3.设置一些其它的属性
        shiroFilter.setLoginUrl("/login");
        shiroFilter.setSuccessUrl("/user/listmenu");
        shiroFilter.setUnauthorizedUrl("/refuse.jsp");
        //4.设置自定义的过滤器
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("authc", new CustomFormFilter());
        shiroFilter.setFilters(filters);
        //5.定义当前工程中使用的一系列的过滤器链
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

//        filterChainDefinitionMap.put("/**/*.js", "anon");
//        filterChainDefinitionMap.put("/**/*.png", "anon");
//        filterChainDefinitionMap.put("/**/*.jpg", "anon");
//        filterChainDefinitionMap.put("/**/*.css", "anon");
//        filterChainDefinitionMap.put("/**/*.map", "anon");
        filterChainDefinitionMap.put("/bootstrap-3.3.7-dist/**/**","anon");

        filterChainDefinitionMap.put("/validatecode.jsp", "anon");
        filterChainDefinitionMap.put("/refuse.jsp", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        //添加“记住我”要放行的资源
        filterChainDefinitionMap.put("/user/**","user");
        filterChainDefinitionMap.put("/student/**","user");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }
    //配置安全管理器
    @Bean
    public SecurityManager securityManager(CustomRealm customRealm,RememberMeManager rememberMeManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //添加自定义realm
        securityManager.setRealm(customRealm);
        //添加缓存管理器
        securityManager.setCacheManager(cacheManager());
        //添加rememberMeManager
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }
    //配置自定义realm
    @Bean
    public CustomRealm customRealm(HashedCredentialsMatcher matcher){
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(matcher);
        return customRealm;
    }
    //配置凭证匹配器
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        return matcher;
    }
    //配置缓存管理器
    @Bean
    public CacheManager cacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:cache/shiro-cache.xml");
        return cacheManager;
    }
    //自定义过滤器完成验证码验证
    @Bean
    public CustomFormFilter customFormFilter(){
        return new CustomFormFilter();
    }
    /**
     * 配置shiro跟spring的关联
     * 以下AuthorizationAttributeSourceAdvisor,DefaultAdvisorAutoProxyCreator两个类是为了支持shiro注解
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
    /**
     * Spring的一个bean , 由Advisor决定对哪些类的方法进行AOP代理
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
    /**
     * lifecycleBeanPostProcessor是负责生命周期的 , 初始化和销毁的类
     * (可选)
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        //定义Properties对象，用于存放处理简单异常的属性
        Properties mappings = new Properties();
        mappings.setProperty("UnauthorizedException","refuse");   //参数1：要处理的异常 参数2：跳转的视图
        mappings.setProperty("UnauthenticatedException","refuse");
        exceptionResolver.setExceptionMappings(mappings);  // None by default
        exceptionResolver.setDefaultErrorView("refuse");    // No default
        exceptionResolver.setExceptionAttribute("ex");     // Default is "exception"
        return exceptionResolver;
    }
    //定义SimpleCookie用于定义rememberMeManager
    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setMaxAge(2592000);
        simpleCookie.setName("rememberMe");
        return simpleCookie;
    }
    //定义rememberMeManager
    @Bean
    public RememberMeManager rememberMeManager(SimpleCookie simpleCookie){
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(simpleCookie);
        return rememberMeManager;
    }
}
