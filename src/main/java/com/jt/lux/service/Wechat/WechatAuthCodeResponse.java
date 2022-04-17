package com.jt.lux.service.Wechat;

import lombok.Data;

/**
 * @描述： 微信授权返回对象
 * @作者： lux
 * @创建日期： 2020-12-18 16:24
 * @版权： lux
 */

@Data
public class WechatAuthCodeResponse {

    /**
     *   用户唯一标识
     */
    private String openid;
    /**
     * 会话密钥
     */
    private String session_key;
    /**
     * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
     */
    private String unionid;
    /**
     * 错误码
     */
    private String errcode;
    /**
     * 错误信息
     */
    private String errmsg;


}
