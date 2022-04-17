package com.jt.lux.util;

import com.alibaba.fastjson.JSONObject;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

/**
 * �����߳�
 * @author
 *
 */
public class GeTuiThread implements Runnable {
    private static final Log log = LogFactory.getLog(GeTuiThread.class);

    private String clientId;
    private JSONObject message;
    //���峣��, appId��appKey��masterSecret ���ñ��ĵ� "�ڶ��� ��ȡ����ƾ֤ "�л�õ�Ӧ������
    private static String appId = "LjjETodZZ16JPJIJAFh5C9";
    private static String appKey = "sRc1MqYpBl9IhPSqnYOTC3";
    private static String masterSecret = "aq6xjDflkP64VTpUNA0HKA";
    private static String host = "http://sdk.open.api.igexin.com/apiex.htm";

    /**
     * @param clientId
     * @param message  {title:"֪ͨ����",content:"֪ͨ����",payload:"֪ͨȥ������������Զ���"}
     */
    public GeTuiThread(String clientId, JSONObject message) {
        this.clientId = clientId;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            if (StringUtils.isEmpty(clientId) || message == null) {
                log.debug("�ͻ��˻���֧�����ͻ���ϢΪ��");
                return;
            }

            //��Ϣģ��
            TransmissionTemplate template = new TransmissionTemplate();
            template.setAppId(appId);
            template.setAppkey(appKey);
            // ͸����Ϣ���ã�1Ϊǿ������Ӧ�ã��ͻ��˽��յ���Ϣ��ͻ���������Ӧ�ã�2Ϊ�ȴ�Ӧ������
            template.setTransmissionType(1);
            template.setTransmissionContent(message.toString());

            //��Ϣ��
            SingleMessage message = new SingleMessage();
            message.setOffline(true);
            // ������Чʱ�䣬��λΪ���룬��ѡ
            message.setOfflineExpireTime(7 * 24 * 3600 * 1000);
            message.setData(template);
            // ��ѡ��1Ϊwifi��0Ϊ���������绷���������ֻ����ڵ���������������Ƿ��·�
            message.setPushNetWorkType(0);

            //Ŀ�����
            Target target = new Target();
            target.setAppId(appId);
            target.setClientId(clientId);

            IGtPush push = new IGtPush(host, appKey, masterSecret);

            IPushResult ret = null;
            try {
                ret = push.pushMessageToSingle(message, target);
            } catch (RequestException e) {
                e.printStackTrace();
                ret = push.pushMessageToSingle(message, target, e.getRequestId());
            }
            if (ret != null) {
                log.debug("���ͽ����" + ret.getResponse().toString());
            } else {
                log.debug("���ͷ�������Ӧ�쳣");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {

        String ss = "{title:\"֪ͨ����\",content:\"֪ͨ����\",payload:\"֪ͨȥ������������Զ���\"}";
        GeTuiThread thread = new GeTuiThread("d3e1fae5a7caf4fb9f9cf97db9b3f2a4", JSONObject.parseObject(ss));
        thread.run();
    }

}