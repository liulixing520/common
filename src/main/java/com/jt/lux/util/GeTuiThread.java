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
 * 个推线程
 * @author
 *
 */
public class GeTuiThread implements Runnable {
    private static final Log log = LogFactory.getLog(GeTuiThread.class);

    private String clientId;
    private JSONObject message;
    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "LjjETodZZ16JPJIJAFh5C9";
    private static String appKey = "sRc1MqYpBl9IhPSqnYOTC3";
    private static String masterSecret = "aq6xjDflkP64VTpUNA0HKA";
    private static String host = "http://sdk.open.api.igexin.com/apiex.htm";

    /**
     * @param clientId
     * @param message  {title:"通知标题",content:"通知内容",payload:"通知去干嘛这里可以自定义"}
     */
    public GeTuiThread(String clientId, JSONObject message) {
        this.clientId = clientId;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            if (StringUtils.isEmpty(clientId) || message == null) {
                log.debug("客户端还不支持推送或消息为空");
                return;
            }

            //消息模板
            TransmissionTemplate template = new TransmissionTemplate();
            template.setAppId(appId);
            template.setAppkey(appKey);
            // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
            template.setTransmissionType(1);
            template.setTransmissionContent(message.toString());

            //消息体
            SingleMessage message = new SingleMessage();
            message.setOffline(true);
            // 离线有效时间，单位为毫秒，可选
            message.setOfflineExpireTime(7 * 24 * 3600 * 1000);
            message.setData(template);
            // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
            message.setPushNetWorkType(0);

            //目标对象
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
                log.debug("推送结果：" + ret.getResponse().toString());
            } else {
                log.debug("推送服务器响应异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {

        String ss = "{title:\"通知标题\",content:\"通知内容\",payload:\"通知去干嘛这里可以自定义\"}";
        GeTuiThread thread = new GeTuiThread("d3e1fae5a7caf4fb9f9cf97db9b3f2a4", JSONObject.parseObject(ss));
        thread.run();
    }

}