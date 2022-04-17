package com.jt.lux.entity.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * �ֻ����ż�¼��
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "user_sms_send_info")
public class SmsInfo {
    /**
     * ���
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * �ֻ���
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * �û�id
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * ���ʹ���,Ĭ��0
     */
    @Column(name = "send_number")
    private Integer sendNumber;

    /**
     * ���ŷ�������
     */
    @Column(name = "send_text")
    private String sendText;

    /**
     * ������֤��
     */
    @Column(name = "sms_code")
    private String smsCode;

    /**
     * ����ʱ��
     */
    @Column(name = "send_time")
    private Date sendTime;

    /**
     * ʧЧʱ��
     */
    @Column(name = "dead_time")
    private Date deadTime;

    /**
     * ����״̬:0-δ���ͣ�1-�ѷ���;��������֤�������ģ�巢�͸�����ƽ̨�������û��ֻ�,���ͳɹ�����¶�����֤��״̬Ϊ�Է���
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
     * ��ȡ���
     *
     * @return id - ���
     */
    public Long getId() {
        return id;
    }

    /**
     * �������
     *
     * @param id ���
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * ��ȡ�ֻ���
     *
     * @return phone_number - �ֻ���
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * �����ֻ���
     *
     * @param phoneNumber �ֻ���
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * ��ȡ�û�id
     *
     * @return user_code - �û�id
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * �����û�id
     *
     * @param userCode �û�id
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * ��ȡ���ʹ���,Ĭ��0
     *
     * @return send_number - ���ʹ���,Ĭ��0
     */
    public Integer getSendNumber() {
        return sendNumber;
    }

    /**
     * ���÷��ʹ���,Ĭ��0
     *
     * @param sendNumber ���ʹ���,Ĭ��0
     */
    public void setSendNumber(Integer sendNumber) {
        this.sendNumber = sendNumber;
    }

    /**
     * ��ȡ���ŷ�������
     *
     * @return send_text - ���ŷ�������
     */
    public String getSendText() {
        return sendText;
    }

    /**
     * ���ö��ŷ�������
     *
     * @param sendText ���ŷ�������
     */
    public void setSendText(String sendText) {
        this.sendText = sendText;
    }

    /**
     * ��ȡ������֤��
     *
     * @return sms__code - ������֤��
     */
    public String getSmsCode() {
        return smsCode;
    }

    /**
     * ���ö�����֤��
     *
     * @param smsCode ������֤��
     */
    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    /**
     * ��ȡ����ʱ��
     *
     * @return send_time - ����ʱ��
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * ���÷���ʱ��
     *
     * @param sendTime ����ʱ��
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * ��ȡʧЧʱ��
     *
     * @return dead_time - ʧЧʱ��
     */
    public Date getDeadTime() {
        return deadTime;
    }

    /**
     * ����ʧЧʱ��
     *
     * @param deadTime ʧЧʱ��
     */
    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    /**
     * ��ȡ����״̬:0-δ���ͣ�1-�ѷ���;��������֤�������ģ�巢�͸�����ƽ̨�������û��ֻ�,���ͳɹ�����¶�����֤��״̬Ϊ�Է���
     *
     * @return send_status - ����״̬:0-δ���ͣ�1-�ѷ���;��������֤�������ģ�巢�͸�����ƽ̨�������û��ֻ�,���ͳɹ�����¶�����֤��״̬Ϊ�Է���
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * ���÷���״̬:0-δ���ͣ�1-�ѷ���;��������֤�������ģ�巢�͸�����ƽ̨�������û��ֻ�,���ͳɹ�����¶�����֤��״̬Ϊ�Է���
     *
     * @param sendStatus ����״̬:0-δ���ͣ�1-�ѷ���;��������֤�������ģ�巢�͸�����ƽ̨�������û��ֻ�,���ͳɹ�����¶�����֤��״̬Ϊ�Է���
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }
}