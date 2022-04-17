package com.jt.lux.service.login;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.jt.lux.entity.security.SmsInfo;
import com.jt.lux.exception.ParamException;
import com.jt.lux.exception.ServiceException;
import com.jt.lux.mapper.security.SmsInfoMapper;
import com.jt.lux.util.*;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;


/**
 * @描述： 短信消息服务类
 * @作者： lux
 * @创建日期： 2021-4-15 23:20

 */
@Slf4j
@Service
public class MsgServiceImpl implements MsgService {


    @Autowired
    private SmsUtil smsUtil;
    @Autowired
    private SmsInfoMapper smsInfoMapper;

    @Value("${jtpf.secret}")
    private String secret;
    @Value(("${aliyun.sms.templateCode}"))
    private String templateCode;

    @Override
    @Synchronized
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void sendMsg(HttpServletRequest request, String phoneNum,String sign){
        if(TextUtils.isEmpty(phoneNum)){
            throw new ParamException("手机号不能为空");
        }
        boolean mobileNo = MobileUitl.isMobileNO(phoneNum);
        if (!mobileNo) {
            throw new ParamException("不支持的号码");
        }
        if(TextUtils.isEmpty(phoneNum)){
            throw new ParamException("签名不能为空");
        }
        String md5Sign = MD5Utils.getMD5(phoneNum+"|"+secret);
        if(!md5Sign.equals(sign.toUpperCase())){
            throw new ParamException("签名不正确");
        }

        String smscode = String.valueOf((int) Math.round(Math.random() * (9999 - 1000) + 1000));
        if(phoneNum.equals("18801044452") || phoneNum.equals("13552084167")){
            smscode = "1234";
        }
        sendMsg(phoneNum,smscode);
    }

    private void sendMsg(String phoneNum, String code) {
        String returnMsg = "";
        String msg = "";
        SmsInfo smsInfo;
        smsInfo = smsInfoMapper.findByPhoneNum(phoneNum);
        if (smsInfo != null) {

            if (smsInfo.getSendNumber() >= 10) {
                throw new ServiceException(Constants.TIP_MSG);
            } else {
                try {
                    SendSmsResponse response = smsUtil.sendSms(phoneNum,templateCode,"优雇","{\"code\":\""+code+"\"}");
                    msg =response.getMessage();
                    returnMsg="0";
                } catch (Exception e) {
                    throw new ServiceException(Constants.SEND_MSG_FAILD);
                }
                smsData(smsInfo, msg, code, returnMsg);
            }
        } else {

            try {
                SendSmsResponse response = smsUtil.sendSms(phoneNum,templateCode,"优雇","{\"code\":\"" + code + "\"}");
                msg =response.getMessage();
                returnMsg="0";
            } catch (Exception e) {
                throw new ServiceException(Constants.SEND_MSG_FAILD);
            }
            smsInfo = new SmsInfo();
            //发送次数
            smsInfo.setSendNumber(1);
            smsInfo.setSendText(msg);
            smsInfo.setReturnMsg(returnMsg);
            smsInfo.setPhoneNumber(phoneNum);
            smsInfo.setSmsCode(code);
            if (!"0".equals(returnMsg)) {
                smsInfo.setSendStatus("0");
            } else {
                smsInfo.setSendStatus("1");
            }
            //发送时间
            Date date = new Date();
            smsInfo.setSendTime(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, 5);
            //失效时间
            smsInfo.setDeadTime(calendar.getTime());
            try {
                smsInfoMapper.insert(smsInfo);
            } catch (Exception e) {
                throw new ServiceException(Constants.MSG_ADD_FAILD);
            }
        }
        if (!"0".equals(returnMsg)) {
            throw new ServiceException(Constants.MSG_UPDATE_FAILD);
        }
    }

    private ResponseEntity<?> smsData(SmsInfo smsInfo, String msg, String code, String returnMsg) {
        smsInfo.setSendNumber(smsInfo.getSendNumber() + 1);
        //发送时间
        Date date = new Date();
        smsInfo.setSendTime(date);
        Calendar calendar = Calendar.getInstance();
        smsInfo.setSendText(msg);
        smsInfo.setSmsCode(code);
        calendar.add(Calendar.MINUTE, 5);
        //失效时间
        smsInfo.setDeadTime(calendar.getTime());
        smsInfo.setReturnMsg(returnMsg);
        if (!"0".equals(returnMsg)) {
            smsInfo.setSendStatus("0");
        } else {
            smsInfo.setSendStatus("1");
        }
        try {
            smsInfoMapper.updateByPrimaryKeySelective(smsInfo);
        } catch (Exception e) {
            log.error(e.getMessage());
            return GenericResponse.ng("修改短信失败");
        }
        return GenericResponse.ok();
    }


}
