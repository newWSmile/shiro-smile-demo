package com.smile.shirosmiledemo.configuration;

import com.smile.shirosmiledemo.filter.JWTFilter;
import com.smile.shirosmiledemo.realm.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-04-09
 * @Time 16:56
 */
@Configuration
public class ShiroConfiguration {



    /**
     * 先走 filter ，然后 filter 如果检测到请求头存在 token，则用 token 去 login，走 Realm 去验证
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        //设置我们自定义的JWT过滤器
        filterMap.put("jwt", new JWTFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);
        // 设置无权限时跳转的 url;
        factoryBean.setUnauthorizedUrl("/unauthorized/无权限");
        Map<String, String> filterRuleMap = new HashMap<>();
        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/**", "jwt");
        // 访问 /unauthorized/** 不通过JWTFilter
        filterRuleMap.put("/unauthorized/**", "anon");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }

    /**
     * 注入 securityManager
     * 此处可以自定义cachemanager 使用redis保存权限和角色信息
     */
    @Bean
    public DefaultWebSecurityManager securityManager(CustomRealm customRealm, DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置自定义 realm.
        securityManager.setRealm(customRealm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * <p>
     *  回话管理器,这里可以自定义会话管理
     * </p>
     * @author luffy
     * @since 2019-08-22 18:12:32
     * @return org.apache.shiro.web.session.mgt.DefaultWebSessionManager
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        // 禁止Shiro重写URL,导致URL携带JSESSIONID=xxxxxxxxxxxx
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        // 删除超时Session
        sessionManager.setDeleteInvalidSessions(true);
        // 定时扫描超时Session
        sessionManager.setSessionValidationSchedulerEnabled(true);
        return sessionManager;
    }

    /**
     * <p>
     *  注册凭证匹配器
     * </p>
     * @author luffy
     * @since 2019-08-22 17:58:27
     * @return org.apache.shiro.authc.credential.HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher loginCredentialsMatcher() {
        HashedCredentialsMatcher loginCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法
        loginCredentialsMatcher.setHashAlgorithmName("MD5");
        //散列的次数，比如散列两次，相当于 md5(md5(""))
        loginCredentialsMatcher.setHashIterations(1);
        return loginCredentialsMatcher;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * <p>
     *  Shiro生命周期处理器:
     *  用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调(例如:CustomRealm)
     *  在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调(例如:DefaultSecurityManager)
     * </p>
     * @author luffy
     * @since 2019-08-22 18:00:04
     * @return org.apache.shiro.spring.LifecycleBeanPostProcessor
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }



    /**
     * <p>
     *     添加注解支持
     *  更改代理模式,如果不更改代理模式,Shiro不基于接口注入的Bean无法使用
     * </p>
     * @author luffy
     * @since 2019-08-22 18:01:44
     * @return org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
