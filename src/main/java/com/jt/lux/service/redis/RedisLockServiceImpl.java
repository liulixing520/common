package com.jt.lux.service.redis;

import com.jt.lux.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisLockServiceImpl implements RedisLockService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public Boolean lock(String key, String value) {
        if (stringRedisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        //���������
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //��ȡ��һ����ʱ��
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            return !StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue);
        }
        return false;
    }

    @Override
    public void unLock(String key, String value) {
        try {
            String redisValue = stringRedisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(redisValue) && redisValue.equals(value)) {
                stringRedisTemplate.delete(key);
            }
        } catch (Exception e) {
            log.error("��redis�ֲ�ʽ���������쳣:", e);
            throw new ServiceException("��redis�ֲ�ʽ���������쳣:");
        }


    }
}
