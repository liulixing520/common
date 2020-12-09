package com.jt.lux.controller.login;

import com.jt.lux.entity.security.UserLogin;
import com.jt.lux.service.login.LoginService;
import com.jt.lux.util.GenericDataResponse;
import com.jt.lux.vo.common.LoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


/**
 * @描述：用户登录
 * @作者：
 * @创建日期： 2018-7-25 14:39
 * @版权：
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;


    /**
     * 用户登录
     */
    @PostMapping(value = "/v1/userLogin", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<GenericDataResponse<UserLogin>> userLogin(@Valid @RequestBody LoginVO loginVO, HttpServletRequest request) {
        return loginService.userLogin(loginVO, request);
    }

    /**
     * 获取用户权限
     */
    @PostMapping(value = "/v1/userSecurityPermissions", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<GenericDataResponse<UserLogin>> userLogin(HttpServletRequest request) {
        return null;
    }

}
