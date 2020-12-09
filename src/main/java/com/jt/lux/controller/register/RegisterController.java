package com.jt.lux.controller.register;

import com.jt.lux.entity.security.UserLogin;
import com.jt.lux.service.register.RegisterService;
import com.jt.lux.util.GenericDataResponse;
import com.jt.lux.vo.common.LoginVO;
import com.jt.lux.vo.common.RegisterVO;
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
 * @版权： 江泰保险经纪股份有限公司
 */
@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 用户注册
     */
    @PostMapping(value = "/v1/register", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<GenericDataResponse<UserLogin>> register(@Valid @RequestBody RegisterVO loginVO, HttpServletRequest request) {

        return registerService.register(loginVO,request);
    }


}
