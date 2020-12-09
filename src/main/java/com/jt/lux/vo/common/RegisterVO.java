package com.jt.lux.vo.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


@Data
public class RegisterVO {
    /**
     *手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phoneNum;

    /**
     * 图片验证码
     */
    @NotBlank(message = "图片验证码不能为空")
    private String imageVerificationCode;

    /**
     * 短信验证码
     */
    @NotBlank(message = "短信验证码不能为空")
    private String smsVerificationCode;

    /**
     * 用户密码
     */
    @NotBlank(message = "用户密码不能为空")
    private String passWord;

}
