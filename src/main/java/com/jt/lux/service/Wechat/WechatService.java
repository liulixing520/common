package com.jt.lux.service.Wechat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.jt.lux.entity.security.UserLogin;
import com.jt.lux.mapper.security.UserLoginMapper;
import com.jt.lux.service.register.RegisterService;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @描述：
 * @作者： lux
 * @创建日期： 2020-12-18 15:49
 * @版权： 江泰保险经纪股份有限公司
 */
@Service
public class WechatService {


    private static final Logger LOGGER = LoggerFactory.getLogger(WechatService.class);

    @Autowired
    private ConsumerMapper consumerMapper;

    @Value("${jtpf.wx.APPID}")
    private  String APPID ;
    @Value("${jtpf.wx.SECRET}")
    private  String SECRET;
    @Value("${jtpf.wx.grantType}")
    private  String GRANTTYPE;


    @Value("${jtpf.wx.jscode2session}")
    private String jsurl;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserLoginMapper userLoginMapper;


    /**
     * 服务器第三方session有效时间，单位秒, 默认1天
     */
    private static final Long EXPIRES = 86400L;

    private RestTemplate wxAuthRestTemplate = new RestTemplate();

    @Autowired
    private WechatAuthProperties wechatAuthProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public WechatAuthenticationResponse wechatLogin(String code) {
        WechatAuthCodeResponse response = getWxSession(code);

        String wxOpenId = response.getOpenid();
        String wxSessionKey = response.getSession_key();
        loginOrRegisterByOpenId(wxOpenId);

        String thirdSession = create3rdSession(wxOpenId, wxSessionKey, EXPIRES);
        return new WechatAuthenticationResponse(thirdSession);
    }

    public WechatAuthCodeResponse getWxSession(String code) {
        LOGGER.info(code);

        String url=jsurl+"?appid="+APPID+
                "&secret="+SECRET+"&js_code="+ code +"&grant_type=authorization_code";
        String response = wxAuthRestTemplate.getForObject(
                jsurl, String.class,
                APPID,
                SECRET,
                code,
                GRANTTYPE);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader reader = objectMapper.readerFor(WechatAuthCodeResponse.class);
        WechatAuthCodeResponse res;
        try {
            res = reader.readValue(response);
        } catch (IOException e) {
            res = null;
            LOGGER.error("反序列化失败", e);
        }
        LOGGER.info(response);
        if (null == res) {
            throw new RuntimeException("调用微信接口失败");
        }
        if (res.getErrcode() != null) {
            throw new RuntimeException(res.getErrmsg());
        }
        return res;
    }

    public String create3rdSession(String wxOpenId, String wxSessionKey, Long expires) {
        String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);
        StringBuffer sb = new StringBuffer();
        sb.append(wxSessionKey).append("#").append(wxOpenId);

        stringRedisTemplate.opsForValue().set(thirdSessionKey, sb.toString(), expires, TimeUnit.SECONDS);
        return thirdSessionKey;
    }

    private void loginOrRegisterByOpenId(String openId) {
        List<UserLogin> userLoginList = userLoginMapper.select(UserLogin.builder().openid(openId).build());
        if (userLoginList.size()>0) {
            userLoginMapper.insertConsumer(consumer);
        }
    }

    public void updateConsumerInfo(Consumer consumer) {
        Consumer consumerExist = consumerMapper.findConsumerByWechatOpenid(AppContext.getCurrentUserWechatOpenId());
        consumerExist.setUpdatedBy(1L);
        consumerExist.setUpdatedAt(System.currentTimeMillis());
        consumerExist.setGender(consumer.getGender());
        consumerExist.setAvatarUrl(consumer.getAvatarUrl());
        consumerExist.setWechatOpenid(consumer.getWechatOpenid());
        consumerExist.setEmail(consumer.getEmail());
        consumerExist.setNickname(consumer.getNickname());
        consumerExist.setPhone(consumer.getPhone());
        consumerExist.setUsername(consumer.getUsername());
        consumerMapper.updateConsumer(consumerExist);
    }

}
