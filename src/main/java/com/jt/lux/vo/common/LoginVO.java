package com.jt.lux.vo.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginVO {

    /**
     * 登录账号
     */
    @ApiModelProperty("登录账号")
    private String userName;

    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    private String password;
    /**
     * 登录类型 0-普通登录， 1-手机验证码登录
     */
    @ApiModelProperty("登录类型 0-普通登录， 1-手机验证码登录")
    private String loginType;

    /**
     *手机号
     */
    @ApiModelProperty("手机号")
    private String phoneNum;

    /**
     * 短信验证码
     */
    @ApiModelProperty("短信验证码")
    private String smsCode;

    /**
     * 图片验证码
     */
    @ApiModelProperty("图片验证码")
    private String imageVerifCode;


}
