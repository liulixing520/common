package com.jt.lux.config;


import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    /**
     * ShiroFilterFactoryBean ����������Դ�ļ����⡣
     * ע�⣺����һ��ShiroFilterFactoryBean�����ǻ򱨴�ģ���Ϊ��
     * ��ʼ��ShiroFilterFactoryBean��ʱ����Ҫע�룺SecurityManager
     * <p>
     * Filter Chain����˵�� 1��һ��URL�������ö��Filter��ʹ�ö��ŷָ� 2�������ö��������ʱ��ȫ����֤ͨ��������Ϊͨ��
     * 3�����ֹ�������ָ����������perms��roles
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager, @Value("${spring.redis.host}") String host, @Value("${spring.redis.password}") String password) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // �������� SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // ���������Ĭ�ϻ��Զ�Ѱ��Web���̸�Ŀ¼�µ�"/login.jsp"ҳ��
        //���ʵ��Ǻ��url��ַΪ /login�Ľӿ�
        shiroFilterFactoryBean.setLoginUrl("/login");
        // ��¼�ɹ���Ҫ��ת������
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // δ��Ȩ����;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        // ������.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // ���ò��ᱻ���ص����� ˳���ж�
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html#", "anon");
        filterChainDefinitionMap.put("/api/v1/**", "anon");
        filterChainDefinitionMap.put("/api/v2/**", "anon");

        // �����˳�������,���еľ�����˳�����Shiro�Ѿ�������ʵ����,��������ᵼ��302���������ã��ݲ�����ԭ��
        //filterChainDefinitionMap.put("/logout", "logout");
        //����ĳ��url��Ҫĳ��Ȩ����
        //filterChainDefinitionMap.put("/hello", "perms[how_are_you]");
        // ���������壬��������˳��ִ�У�һ�㽫 /**������Ϊ�±�
        // <!-- authc:����url��������֤ͨ���ſ��Է���; anon:����url����������������;user:remember me�Ŀ��Է���-->
        filterChainDefinitionMap.put("/fine", "user");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * �Զ���Realm����
     *
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm(CredentialsMatcher credentialsMatcher) {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //���Զ��������set����Realm11
        myShiroRealm.setCredentialsMatcher(credentialsMatcher);
        return myShiroRealm;
    }

    /**
     * ����SecurityManage����
     *
     * @return
     */
    @Bean
    @DependsOn("credentialsMatcher")
    public SecurityManager securityManager(CredentialsMatcher credentialsMatcher, @Value("${spring.redis.host}") String host, @Value("${spring.redis.password}") String password) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm(credentialsMatcher));
        // �Զ��建��ʵ�� ʹ��redis
        securityManager.setCacheManager(cacheManager(host, password));
        // �Զ���session���� ʹ��redis
        securityManager.setSessionManager(sessionManager(host, password));
        //ע���ס�ҹ�����;
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * ������ǿ
     *
     * @param cacheManager
     * @return
     */
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher(CacheManager cacheManager) {
        RetryLimitHashedCredentialsMatcher credentialsMatcher = new RetryLimitHashedCredentialsMatcher(cacheManager);
        //���ܷ�ʽ
        credentialsMatcher.setHashAlgorithmName("MD5");
        //���ܵ�������
        credentialsMatcher.setHashIterations(1024);
        //true�����õ�hex���룬false�õ�base64����
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        //���³��ԵĴ������Լ�����ģ�
        credentialsMatcher.setRetryMax(5);
        return credentialsMatcher;
    }


    /**
     * ����shiro redisManager
     * ���ϵ�һ�� shiro-redis �����ʵ����shiro��cache�ӿڡ�CacheManager�ӿھ�
     *
     * @return
     */
    @Bean
    public RedisManager redisManager(@Value("${spring.redis.host}") String host, @Value("${spring.redis.password}") String password) {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(6379);
        redisManager.setExpire(18000);// ���ù���ʱ��
        redisManager.setPassword(password);
        // redisManager.setTimeout(timeout);
        return redisManager;
    }

    /**
     * cacheManager ���� redisʵ��
     * ���ϵ�һ�� shiro-redis ���
     *
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(@Value("${spring.redis.host}") String host, @Value("${spring.redis.password}") String password) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager(host, password));
        return redisCacheManager;
    }


    /**
     * RedisSessionDAO shiro sessionDao���ʵ�� ͨ��redis
     */
    public RedisSessionDAO redisSessionDAO(@Value("${spring.redis.host}") String host, @Value("${spring.redis.password}") String password) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager(host, password));
        return redisSessionDAO;
    }

    /**
     * shiro session�Ĺ���
     */
    public DefaultWebSessionManager sessionManager(@Value("${spring.redis.host}") String host, @Value("${spring.redis.password}") String password) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO(host, password));
        return sessionManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {

        System.out.println("ShiroConfiguration.rememberMeCookie()");
        //���������cookie�����ƣ���Ӧǰ�˵�checkbox��name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- ��ס��cookie��Чʱ��30�� ,��λ��;-->
        simpleCookie.setMaxAge(259200);
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }

    /**
     * cookie�������;
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {

        System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    /**
     * Shiro�������ڴ�����
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * ����Shiro��ע��(��@RequiresRoles,@RequiresPermissions),�����SpringAOPɨ��ʹ��Shiroע�����,���ڱ�Ҫʱ���а�ȫ�߼���֤
     * ������������bean(DefaultAdvisorAutoProxyCreator(��ѡ)��AuthorizationAttributeSourceAdvisor)����ʵ�ִ˹���
     *
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


    /*@Bean
    public KickoutSessionControlFilter kickoutSessionFilter(@Value("${spring.redis.host}") String host, @Value("${spring.redis.password}") String password) {
        KickoutSessionControlFilter kickoutSessionFilter = new KickoutSessionControlFilter();
        kickoutSessionFilter.setCacheManager(cacheManager(host, password));
        kickoutSessionFilter.setSessionManager(sessionManager(host, password));
        kickoutSessionFilter.setKickoutAfter(false);
        kickoutSessionFilter.setMaxSession(1);
        //���߳����ض��򵽵ĵ�ַ��
        //kickoutSessionFilter.setKickoutUrl("/toLogin?kickout=1");
        return kickoutSessionFilter;
    }*/


}