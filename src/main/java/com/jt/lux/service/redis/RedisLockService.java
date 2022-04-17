package com.jt.lux.service.redis;

import org.springframework.stereotype.Service;

@Service
public interface RedisLockService {

    Boolean lock(String key, String value);

    void unLock(String key, String value);
}
