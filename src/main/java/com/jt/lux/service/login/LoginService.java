package com.jt.lux.service.login;

import com.alibaba.fastjson.JSONObject;
import com.jt.lux.entity.security.UserLogin;
import com.jt.lux.exception.BizException;
import com.jt.lux.exception.ParamException;
import com.jt.lux.exception.PassWordExpiredException;
import com.jt.lux.exception.ServiceException;
import com.jt.lux.mapper.security.UserLoginMapper;
import com.jt.lux.util.Constants;
import com.jt.lux.util.GenericDataResponse;
import com.jt.lux.vo.common.LoginVO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @描述： 登录
 * @作者： lux
 * @创建日期： 2020-8-31 15:28
 * @版权： 江泰保险经纪股份有限公司
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

    private static final String ACCESS_TOKEN = "access_token";

    private static final String REFRESH_TOKEN = "refresh_token";

    private static final String BEARER = "Bearer ";

    private static final String AUTHORIZATION = "authorization";
    @Autowired
    private UserLoginMapper userLoginMapper;

    /**
     * 登录
     * @param vo
     * @param request
     * @return
     */
    public ResponseEntity<GenericDataResponse<UserLogin>> userLogin(LoginVO vo, HttpServletRequest request){
        log.info("logininfo:{}", JSONObject.toJSON(vo).toString());
        if(Constants.LOGIN_TYPE_0.equals(vo.getLoginType())){
            return  userLogin(vo.getUserName(),vo.getPassword(),vo.getImageVerifCode());
        }

        if(Constants.LOGIN_TYPE_1.equals(vo.getLoginType())){
            return userLogin(vo.getPhoneNum(),vo.getSmsCode());
        }

        if(Constants.LOGIN_TYPE_2.equals(vo.getLoginType())){
            //TODO 接入第三方登录
            return null;
        }

        return null;
    }


    /**
     * 用户名密码登录
     * @param userName
     * @param password
     * @param imageVerifCode
     * @return
     */
    public ResponseEntity<GenericDataResponse<UserLogin>> userLogin(String userName,String password,String imageVerifCode){
        if(StringUtils.isEmpty(userName)){
            throw new ServiceException(Constants.USER_NAME_IS_NULL);
        }
        if(StringUtils.isEmpty(password)){
            throw new ServiceException(Constants.PASSWORD_IS_NULL);
        }
        UserLogin userLogin = userLoginMapper.selectByPrimaryKey(userName);
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

        return GenericDataResponse.okWithData(userLogin);
    }

    /**
     * 手机号验证码登录
     * @param phone
     * @param smsCode
     * @return
     */
    public ResponseEntity<GenericDataResponse<UserLogin>> userLogin(String phone,String smsCode){
        return null;
    }

}
