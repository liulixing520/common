package com.jt.lux.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;


@Data
@Slf4j
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, Integer> cache;

    private Integer retryMax;

   private String keyPrefix = "retryMax";

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        cache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws ExcessiveAttemptsException {
        String username = (String) token.getPrincipal();
        Integer retryCount = cache.get(this.keyPrefix+username);
        if (retryCount == null) {
            retryCount = new Integer(1);
            cache.put(this.keyPrefix+username, retryCount);
        }


        if (retryCount > retryMax) {
            log.warn("您已连续错误达" + retryMax + "次！请10分钟后再试");
            throw new ExcessiveAttemptsException("您已连续错误达" + retryMax + "次！请10分钟后再试");
        }

        if (cache.getClass().getName().contains("RedisCache")) {
            cache.put(this.keyPrefix+username, ++retryCount);
        }
        //调用父类的校验方法
        boolean matches = super.doCredentialsMatch(token, info);
        log.info("调用父类的校验方法:"+matches);
        if (matches) {
            cache.remove(this.keyPrefix+username);
        } else {
            log.warn("密码错误，已错误" + retryCount + "次，最多错误" + retryMax + "次");
            throw new IncorrectCredentialsException("密码错误，已错误" + retryCount + "次，最多错误" + retryMax + "次");

        }
        return true;
    }


}
