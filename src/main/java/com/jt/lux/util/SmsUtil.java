package com.jt.lux.util;

/**
 * @������
 * @���ߣ� lux
 * @�������ڣ� 2021-4-16 9:01

 */
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Copyright (C), 2016-2019
 * @FileName: SmsUtil
 * @Author: hf
 * @Date: 2019/9/10 0:27
 * @Description: �����ƶ��ŷ��͹�����
 */
@Component
public class SmsUtil {

    static final String product = "Dysmsapi";

    //��Ʒ����,�����������滻
    static final String domain = "dysmsapi.aliyuncs.com";

    //��ȡaccessKey
    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;
    @Value(("${aliyun.sms.accessKeySecret}"))
    private String accessKeySecret;

    /**
     * ��������:
     * ��
     * �����ֻ�����
     * ��
     *
     * @className: SmsUtil
     * @author: hf
     * @version: 1.0.0
     * @date: 2019/9/10 0:36
     * @param: [mobile �ֻ���, template_code ģ���, sign_name ǩ��, param ����]
     * @return: com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse
     */
    public SendSmsResponse sendSms(String mobile, String template_code, String sign_name, String param) throws ClientException {


        //��ȡ�ͻ��˶���
        IAcsClient acsClient = getACSClient();

        //��װ�������-��������������̨-�ĵ���������
        SendSmsRequest request = new SendSmsRequest();

        //����:�������ֻ���
        request.setPhoneNumbers(mobile);

        //����:����ǩ��-���ڶ��ſ���̨���ҵ�
        request.setSignName(sign_name);

        //����:����ģ��-���ڶ��ſ���̨���ҵ�
        request.setTemplateCode(template_code);

        //��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�װ���${name},������֤��Ϊ${code}"ʱ,�˴���ֵΪ
        request.setTemplateParam(param);

        //hint �˴����ܻ��׳��쳣��ע��catch
        SendSmsResponse sendSmsResponse = null;

        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sendSmsResponse;
    }

    public QuerySendDetailsResponse querySendDetails(String mobile, String bizId) throws ClientException {

        //��ȡ�ͻ��˶���
        IAcsClient acsClient = getACSClient();

        //��װ�������
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();

        //����-����
        request.setPhoneNumber(mobile);

        //��ѡ-��ˮ��
        request.setBizId(bizId);

        //����-�������� ֧��30���ڼ�¼��ѯ����ʽyyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));

        //����-ҳ��С
        request.setPageSize(10L);

        //����-��ǰҳ���1��ʼ����
        request.setCurrentPage(1L);

        //hint �˴����ܻ��׳��쳣��ע��catch
        QuerySendDetailsResponse querySendDetailsResponse = null;

        try {
            querySendDetailsResponse = acsClient.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return querySendDetailsResponse;
    }

    private IAcsClient getACSClient() {
        //������ʱʱ��
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //��ʼ��acsClient,�ݲ�֧��region��
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return new DefaultAcsClient(profile);
    }
}
