package com.jt.lux.service.login;

import com.alibaba.fastjson.JSONObject;
import com.jt.lux.entity.security.*;
import com.jt.lux.exception.BizException;
import com.jt.lux.exception.ParamException;
import com.jt.lux.exception.PassWordExpiredException;
import com.jt.lux.exception.ServiceException;
import com.jt.lux.mapper.security.*;
import com.jt.lux.service.auth.AuthService;
import com.jt.lux.service.register.RegisterService;
import com.jt.lux.util.Constants;
import com.jt.lux.util.GenericDataResponse;
import com.jt.lux.vo.common.LoginVO;
import com.jt.lux.vo.common.RegisterVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @描述： 登录
 * @作者： lux
 * @创建日期： 2020-8-31 15:28

 */
@Service
public class LoginService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String ATTR_KEY_CTX = "_ctx";

    private static final String ATTR_KEY_TOKEN = "_token";

    private static final String ATTR_KEY_URI = "_uri";

    private static final String ATTR_KEY_SERVICE = "_service";

    private static final String UN_KNOWN = "unKnown";

    private static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";

    private static final String PARTY_ID = "a"; //当事人id

    private static final String MOBILE_NUM = "b"; //手机号

    private static final String ACCESS_TOKEN = "access_token";

    private static final String REFRESH_TOKEN = "refresh_token";

    private static final String BEARER = "Bearer ";

    private static final String AUTHORIZATION = "authorization";


    private static final String PREFIX_ACC = "AUTH:ACC-TOKEN:";
    private static final String PREFIX_REF = "AUTH:REF-TOKEN:";

    @Value("${jtpf.token.redisExpiresInMinutes}")
    private int redisExpiresInMinutes;

    @Value("${jtpf.token.accessTokenExpiresInMinutes}")
    private int accessTokenExpiresInMinutes;

    @Value("${jtpf.token.refreshTokenExpiresInMinutes}")
    private int refreshTokenExpiresInMinutes;

    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private SmsInfoMapper smsInfoMapper;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private AuthService authService;
    @Autowired
    private PartyTypeMapper partyTypeMapper;
    @Autowired
    private PartyMapper partyMapper;
    @Autowired
    private StringRedisTemplate redis;

    @Autowired
    private MenuTreeMapper menuTreeMapper;

    /**
     * 登录
     * @param vo
     * @param request
     * @return
     */
    public ResponseEntity<GenericDataResponse<UserLogin>> userLogin(LoginVO vo, HttpServletRequest request, HttpServletResponse response){
        log.info("logininfo:{}", JSONObject.toJSON(vo).toString());
        if(StringUtils.isBlank(vo.getLoginType())){
            throw new ServiceException("loginType 缺失");
        }
        UserLogin userLogin = new UserLogin();
        if(Constants.LOGIN_TYPE_PASSWORD.equals(vo.getLoginType())){
            userLogin =  passwordUserLogin(vo.getUserName(),vo.getPassword(),vo.getImageVerifCode());
        }

        if(Constants.LOGIN_TYPE_PHONE.equals(vo.getLoginType())){
            userLogin =  smsUserLogin(vo.getPhoneNum(),vo.getSmsCode());
        }

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put(PARTY_ID,userLogin.getPartyId());
        tokenData.put(MOBILE_NUM, userLogin.getMobileNum());
        //生成
        String access_token =authService.createToken(UUID.randomUUID().toString(),"yg-jwt","test",accessTokenExpiresInMinutes,tokenData);
        String refresh_token =authService.createToken(UUID.randomUUID().toString(),"yg-jwt","test",refreshTokenExpiresInMinutes,tokenData);
        response.setHeader(ACCESS_TOKEN,BEARER+access_token);
        response.setHeader(REFRESH_TOKEN,refresh_token);

        //基于redis控制
        storeRedisValues(refresh_token, access_token);

        return GenericDataResponse.okWithData(userLogin);
    }


    /**
     * 用户名密码登录
     * @param userName
     * @param password
     * @param imageVerifCode
     * @return
     */
    public UserLogin passwordUserLogin(String userName,String password,String imageVerifCode){
        if(StringUtils.isEmpty(userName)){
            throw new ServiceException(Constants.USER_NAME_IS_NULL);
        }
        if(StringUtils.isEmpty(password)){
            throw new ServiceException(Constants.PASSWORD_IS_NULL);
        }

        UserLogin userLogin = userLoginMapper.findByMobileNum(userName);
        if(userLogin == null){
            log.error(Constants.USER_IS_NOT_EXIST+"：{}",userName);
            throw new ServiceException(Constants.USER_IS_NOT_EXIST);
        }

        UsernamePasswordToken token = new UsernamePasswordToken(userName, password, false);
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();

        try {
            currentUser.login(token);
            //此步将调用realm的认证方法
        } catch (ExcessiveAttemptsException e) {
            throw new ParamException(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            throw new ParamException(Constants.LOGIN_FAIL);
        } catch (PassWordExpiredException e) {
            throw new PassWordExpiredException(e.getMessage());
        } catch (AuthenticationException e) {
            throw new BizException(e.getMessage());
        }catch (Exception e){
            throw new BizException(e.getMessage());
        }

        return userLogin;
    }

    /**
     * 手机号验证码登录
     * @param phone
     * @param smsCode
     * @return
     */
    public UserLogin smsUserLogin(String phone,String smsCode){
        if(StringUtils.isBlank(phone)){
            throw new ServiceException("手机号不能为空");
        }
        //手机号+验证码，查授权， 校验验证码
        SmsInfo smsInfo = smsInfoMapper.findByPhoneNumAndStatus(phone);
        if (smsInfo != null) {
            //验证手机验证码
            if (!smsInfo.getSmsCode().equals(smsCode)) {
                throw new ParamException("无效的验证码");
            }
            //验证码是否过期
            if(new Date().compareTo(smsInfo.getDeadTime())>0){
                throw new ParamException("验证码已过期");
            }
            UserLogin userLogin = userLoginMapper.findByMobileNum(phone);
            if (null == userLogin){
                userLogin = registerService.register(RegisterVO.builder().phoneNum(phone).build(),null);
            }
            return userLogin;
        } else {
            //无效的验证码
            throw new ParamException("无效的验证码");
        }
    }



    /**
     * 登出
     * @param authorization
     * @param partyId
     * @return
     */
    public void logout(String authorization,String partyId){
        if(StringUtils.isBlank(authorization)){
            throw new ServiceException("authorization不能为空");
        }

        Party  party = partyMapper.selectByPrimaryKey(partyId);
        party.setClientId(null);
        partyMapper.updateByPrimaryKeySelective(party);
        clearToken(PREFIX_ACC,authorization);
    }


    private void storeRedisValues(String refreshToken, String accessToken) {
        Claims claims=authService.parseJWT(refreshToken);
        String partyId = (String) claims.get("a");
        String redisKey = PREFIX_ACC + partyId;

        String[] parts2 = refreshToken.split("\\.");
        String refreshRedisKey = PREFIX_REF + parts2[2];
        try {
            doRedisAction(redisKey, refreshRedisKey);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            // retry once?
            try {
                doRedisAction(redisKey, refreshRedisKey);
            } catch (Exception e2) {
                log.error(e.getMessage(), e2);
            }
        }
    }

    private void doRedisAction(String redisKey, String refreshRedisKey) {
        // access token 入redis，以refresh token的签名作为value
        redis.opsForValue().set(redisKey, refreshRedisKey);
        redis.expire(redisKey, redisExpiresInMinutes, TimeUnit.MINUTES);

        // refresh token 入redis
        redis.opsForValue().set(refreshRedisKey, "0");
        redis.expire(refreshRedisKey, refreshTokenExpiresInMinutes, TimeUnit.MINUTES);
    }

    private void clearToken(String prefix, String token) {

        String[] parts = token.split("\\.");
        if (parts.length < 3) {
            return;
        }
        String redisKey = prefix + parts[2];
        try {
            redis.delete(redisKey);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 获取当事人类型
     * @return
     */
    public List<PartyType> partyTypeList(){
        List<PartyType> list = partyTypeMapper.selectAll();
        return  list;
    }

    /**
     * 修改当事人信息
     * @return
     */
    public int updateParty(Party party){
        int i = partyMapper.updateByPrimaryKeySelective(party);
        return i;
    }



    public ResponseEntity<?> refreshToken(String refreshToken,HttpServletResponse response) {
        if (org.apache.commons.lang3.StringUtils.isBlank(refreshToken)) {
            throw new ServiceException("refreshToken无效");
        }

        String[] parts = refreshToken.split("\\.");
        String partyId = "";
        String mobumleNm = "";
        try{

            Claims claims = null;
            claims=authService.parseJWT(refreshToken);
            partyId = (String) claims.get(PARTY_ID);
            mobumleNm = (String) claims.get(MOBILE_NUM);
        }catch (ExpiredJwtException e){
            throw e;
        }catch (SignatureException e) {
            throw e;
        }catch (Exception e){
            throw e;
        }

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put(PARTY_ID,partyId);
        tokenData.put(MOBILE_NUM, mobumleNm);
        //生成
        String access_token =authService.createToken(UUID.randomUUID().toString(),"yg-jwt","test",accessTokenExpiresInMinutes,tokenData);
        String refresh_token =authService.createToken(UUID.randomUUID().toString(),"yg-jwt","test",refreshTokenExpiresInMinutes,tokenData);

        //基于redis控制
        storeRedisValues(refresh_token, access_token);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(ACCESS_TOKEN, BEARER+access_token);
        httpHeaders.set(REFRESH_TOKEN, refreshToken);

        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }


}
