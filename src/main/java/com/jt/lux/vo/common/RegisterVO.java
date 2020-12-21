package com.jt.lux.vo.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注册vo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterVO {
    /**
     *手机号
     */
    private String phoneNum;

    /**
     * 图片验证码
     */
    private String imageVerificationCode;

    /**
     * 短信验证码
     */
    private String smsVerificationCode;

    /**
     * 用户密码
     */
    private String passWord;

    /**
     * openid
     */
    private String openid;



}
