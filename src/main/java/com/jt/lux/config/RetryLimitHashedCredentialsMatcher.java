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
            log.warn("�������������" + retryMax + "�Σ���10���Ӻ�����");
            throw new ExcessiveAttemptsException("�������������" + retryMax + "�Σ���10���Ӻ�����");
        }

        if (cache.getClass().getName().contains("RedisCache")) {
            cache.put(this.keyPrefix+username, ++retryCount);
        }
        //���ø����У�鷽��
        boolean matches = super.doCredentialsMatch(token, info);
        log.info("���ø����У�鷽��:"+matches);
        if (matches) {
            cache.remove(this.keyPrefix+username);
        } else {
            log.warn("��������Ѵ���" + retryCount + "�Σ�������" + retryMax + "��");
            throw new IncorrectCredentialsException("��������Ѵ���" + retryCount + "�Σ�������" + retryMax + "��");

        }
        return true;
    }


}
