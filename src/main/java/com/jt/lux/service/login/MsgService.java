package com.jt.lux.service.login;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @������ ��Ϣ������
 * @���ߣ� lux
 * @�������ڣ� 2021-4-15 23:19

 */
@Service
public interface MsgService {

    void sendMsg(HttpServletRequest request,String phoneNum,String sign);

}
