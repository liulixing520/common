package com.jt.lux.controller.register;

import com.jt.lux.entity.security.UserLogin;
import com.jt.lux.exception.ServiceException;
import com.jt.lux.mapper.security.UserLoginMapper;
import com.jt.lux.service.register.RegisterService;
import com.jt.lux.util.Constants;
import com.jt.lux.util.GenericDataResponse;
import com.jt.lux.vo.common.RegisterVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @描述： 注册
 * @作者： lux
 * @创建日期： 2020-9-25 14:31
 * @版权： lux
 */
@RestController
@RequestMapping("/api")
public class RegisterController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserLoginMapper userLoginMapper;


    /**
     * 用户注册
     */
    @PostMapping(value = "/v1/register", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<GenericDataResponse<UserLogin>> register(@Valid @RequestBody RegisterVO loginVO, HttpServletRequest request) {
        UserLogin userLogin = userLoginMapper.selectOne(UserLogin.builder().mobileNum(loginVO.getPhoneNum()).build());
        if (null != userLogin){
            log.error(Constants.PHONENUM_ISEXIST,loginVO.getPhoneNum());
            throw new ServiceException(Constants.PHONENUM_ISEXIST);
        }
        userLogin = registerService.register(loginVO,request);
        return GenericDataResponse.okWithData(userLogin);
    }


}
