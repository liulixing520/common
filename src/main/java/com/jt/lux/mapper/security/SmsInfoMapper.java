package com.jt.lux.mapper.security;

import com.jt.lux.entity.security.SmsInfo;
import com.jt.lux.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
@Mapper
@Repository
public interface SmsInfoMapper extends MyMapper<SmsInfo> {


   SmsInfo findByPhoneNum(String phoneNum);

   SmsInfo findByPhoneNumAndStatus(String phoneNum);

   void updateSendNumber();


   void insertSmsInfo(SmsInfo smsInfo);





   //List<SmsInfo> findByMsgstatusAndCreateDateIsLessThanEqual()
}