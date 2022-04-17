package com.jt.lux.entity.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 手机短信记录表
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "user_sms_send_info")
public class SmsInfo {
    /**
     * 序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 手机号
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 用户id
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * 发送次数,默认0
     */
    @Column(name = "send_number")
    private Integer sendNumber;

    /**
     * 短信发送内容
     */
    @Column(name = "send_text")
    private String sendText;

    /**
     * 短信验证码
     */
    @Column(name = "sms_code")
    private String smsCode;

    /**
     * 发送时间
     */
    @Column(name = "send_time")
    private Date sendTime;

    /**
     * 失效时间
     */
    @Column(name = "dead_time")
    private Date deadTime;

    /**
     * 发送状态:0-未发送，1-已发送;将短信验证码跟短信模板发送给短信平台发送至用户手机,发送成功后更新短信验证码状态为以发送
     */
    @Column(name = "send_status")
    private String sendStatus;

    @Column(name = "return_msg")
    private String returnMsg;

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    /**
     * 获取序号
     *
     * @return id - 序号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置序号
     *
     * @param id 序号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取手机号
     *
     * @return phone_number - 手机号
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置手机号
     *
     * @param phoneNumber 手机号
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取用户id
     *
     * @return user_code - 用户id
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置用户id
     *
     * @param userCode 用户id
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * 获取发送次数,默认0
     *
     * @return send_number - 发送次数,默认0
     */
    public Integer getSendNumber() {
        return sendNumber;
    }

    /**
     * 设置发送次数,默认0
     *
     * @param sendNumber 发送次数,默认0
     */
    public void setSendNumber(Integer sendNumber) {
        this.sendNumber = sendNumber;
    }

    /**
     * 获取短信发送内容
     *
     * @return send_text - 短信发送内容
     */
    public String getSendText() {
        return sendText;
    }

    /**
     * 设置短信发送内容
     *
     * @param sendText 短信发送内容
     */
    public void setSendText(String sendText) {
        this.sendText = sendText;
    }

    /**
     * 获取短信验证码
     *
     * @return sms__code - 短信验证码
     */
    public String getSmsCode() {
        return smsCode;
    }

    /**
     * 设置短信验证码
     *
     * @param smsCode 短信验证码
     */
    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    /**
     * 获取发送时间
     *
     * @return send_time - 发送时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 设置发送时间
     *
     * @param sendTime 发送时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取失效时间
     *
     * @return dead_time - 失效时间
     */
    public Date getDeadTime() {
        return deadTime;
    }

    /**
     * 设置失效时间
     *
     * @param deadTime 失效时间
     */
    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    /**
     * 获取发送状态:0-未发送，1-已发送;将短信验证码跟短信模板发送给短信平台发送至用户手机,发送成功后更新短信验证码状态为以发送
     *
     * @return send_status - 发送状态:0-未发送，1-已发送;将短信验证码跟短信模板发送给短信平台发送至用户手机,发送成功后更新短信验证码状态为以发送
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置发送状态:0-未发送，1-已发送;将短信验证码跟短信模板发送给短信平台发送至用户手机,发送成功后更新短信验证码状态为以发送
     *
     * @param sendStatus 发送状态:0-未发送，1-已发送;将短信验证码跟短信模板发送给短信平台发送至用户手机,发送成功后更新短信验证码状态为以发送
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }
}