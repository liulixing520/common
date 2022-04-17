package com.jt.lux.service.Wechat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.jt.lux.mapper.security.PersonMapper;
import com.jt.lux.service.register.RegisterService;
import com.jt.lux.util.GenericDataResponse;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @描述：
 * @作者： lux
 * @创建日期： 2020-12-18 15:49
 * @版权： lux
 */
@Service
public class WechatService {

    private static final Logger log = LoggerFactory.getLogger(WechatService.class);


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
    private PersonMapper personMapper;


    /**
     * 服务器第三方session有效时间，单位秒, 默认1天
     */
    private static final Long EXPIRES = 86400L;

    private RestTemplate wxAuthRestTemplate = new RestTemplate();


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseEntity<GenericDataResponse<String>> wechatLogin(String code) {
        WechatAuthCodeResponse response = getWxSession(code);

        String wxOpenId = response.getOpenid();
        String wxSessionKey = response.getSession_key();
        loginOrRegisterByOpenId(wxOpenId);

        String thirdSession = create3rdSession(wxOpenId, wxSessionKey, EXPIRES);
        return GenericDataResponse.okWithData(thirdSession);
    }

    public WechatAuthCodeResponse getWxSession(String code) {
        log.info(code);
        String urlString = "?appid={appid}&secret={srcret}&js_code={code}&grant_type={grantType}";
        String response = wxAuthRestTemplate.getForObject(urlString, String.class,APPID,SECRET,code,GRANTTYPE);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader reader = objectMapper.readerFor(WechatAuthCodeResponse.class);
        WechatAuthCodeResponse res;
        try {
            res = reader.readValue(response);
        } catch (IOException e) {
            res = null;
            log.error("反序列化失败", e);
        }
        log.info(response);
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

    }

}
