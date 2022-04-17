package com.jt.lux.service.login;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @描述： 消息服务类
 * @作者： lux
 * @创建日期： 2021-4-15 23:19

 */
@Service
public interface MsgService {

    void sendMsg(HttpServletRequest request,String phoneNum,String sign);

}
