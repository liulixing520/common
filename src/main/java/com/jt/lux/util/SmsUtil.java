package com.jt.lux.util;

/**
 * @描述：
 * @作者： lux
 * @创建日期： 2021-4-16 9:01

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
 * @Description: 阿里云短信发送工具类
 */
@Component
public class SmsUtil {

    static final String product = "Dysmsapi";

    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    //获取accessKey
    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;
    @Value(("${aliyun.sms.accessKeySecret}"))
    private String accessKeySecret;

    /**
     * 功能描述:
     * 〈
     * 发送手机短信
     * 〉
     *
     * @className: SmsUtil
     * @author: hf
     * @version: 1.0.0
     * @date: 2019/9/10 0:36
     * @param: [mobile 手机号, template_code 模板号, sign_name 签名, param 参数]
     * @return: com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse
     */
    public SendSmsResponse sendSms(String mobile, String template_code, String sign_name, String param) throws ClientException {


        //获取客户端对象
        IAcsClient acsClient = getACSClient();

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();

        //必填:待发送手机号
        request.setPhoneNumbers(mobile);

        //必填:短信签名-可在短信控制台中找到
        request.setSignName(sign_name);

        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(template_code);

        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(param);

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = null;

        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sendSmsResponse;
    }

    public QuerySendDetailsResponse querySendDetails(String mobile, String bizId) throws ClientException {

        //获取客户端对象
        IAcsClient acsClient = getACSClient();

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();

        //必填-号码
        request.setPhoneNumber(mobile);

        //可选-流水号
        request.setBizId(bizId);

        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));

        //必填-页大小
        request.setPageSize(10L);

        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = null;

        try {
            querySendDetailsResponse = acsClient.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return querySendDetailsResponse;
    }

    private IAcsClient getACSClient() {
        //调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return new DefaultAcsClient(profile);
    }
}
