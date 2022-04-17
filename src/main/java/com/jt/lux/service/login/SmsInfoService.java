package com.jt.lux.service.login;

import com.jt.lux.entity.security.SmsInfo;
import com.jt.lux.mapper.security.SmsInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
import java.util.List;

/**
 * @Description: ���Žӿڷ�����
 * @Date: 2018/6/22 15:41
 */
@Service
@Transactional
@Slf4j
public class SmsInfoService {

    @Autowired
    SmsInfoMapper smsInfoMapper;

    /**
     * ��ʱ��ѯ����5���ӵĶ���
     *
     * @return
     */
    public void findByStatusAndCreateDateIsLessThanEqual() {
        Calendar now = Calendar.getInstance();
        Example example = new Example(SmsInfo.class);
        example.createCriteria().andEqualTo("sendStatus", "1").andLessThanOrEqualTo("deadTime", now.getTime());
        List<SmsInfo> smsInfos = smsInfoMapper.selectByExample(example);
        if (smsInfos != null && smsInfos.size() > 0) {
            for (SmsInfo smsInfo : smsInfos) {
                smsInfo.setSendStatus("0");
                smsInfoMapper.updateByPrimaryKeySelective(smsInfo);
            }
        }
    }

    /**
     * ÿ��0��10�ֶ�ʱ����û����ŷ��ʹ���
     */
    public void clean(){
        smsInfoMapper.updateSendNumber();
    }
}
