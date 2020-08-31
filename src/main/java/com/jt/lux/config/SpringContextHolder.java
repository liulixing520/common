package com.jt.lux.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringContextHolder.applicationContext = context;
    }

    /**
     * @Description: 获取 ApplicationContext 容器
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @Description: 获取 bean
     */
    public static Object getBean(String name) {
        return applicationContext.getBean (name);
    }

}
