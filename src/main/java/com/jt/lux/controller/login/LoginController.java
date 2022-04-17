package com.jt.lux.controller.login;

import com.jt.lux.entity.security.UserLogin;
import com.jt.lux.mapper.security.MenuTreeMapper;
import com.jt.lux.mapper.security.UserLoginSecurityGroupMapper;
import com.jt.lux.service.login.LoginService;
import com.jt.lux.service.login.MsgService;
import com.jt.lux.util.GenericDataResponse;
import com.jt.lux.util.GenericResponse;
import com.jt.lux.util.idg.DefaultUidGenerator;
import com.jt.lux.vo.common.LoginVO;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * @描述：用户登录
 * @作者：
 * @创建日期： 2018-7-25 14:39
 * @版权：
 */
@Api(value = "登录相关", tags = { "登录相关-接口" })
@RestController
@RequestMapping("/api")
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    @Autowired
    @Qualifier("idg")
    private DefaultUidGenerator idg;

    @Autowired
    private MsgService msgService;

    @Autowired
    private UserLoginSecurityGroupMapper userLoginSecurityGroupMapper;

    @Autowired
    private MenuTreeMapper menuTreeMapper;

    /**
     * 用户登录
     */
    @ApiOperation(value = "用户登录", notes = "用户登录-接口")
    @PostMapping(value = "/v1/userLogin", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GenericDataResponse<UserLogin>> userLogin(@Valid @RequestBody LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) {
        return loginService.userLogin(loginVO, request,response);
    }

    /**
     * 用户登出
     */
    @ApiOperation(value = "用户登出", notes = "用户登出-接口")
    @PostMapping(value = "/v1/logout", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String partyId = (String) request.getAttribute("partyId");
        loginService.logout(authorization,partyId);

        return GenericDataResponse.ok();
    }



    /**
     * 发送短信服务
     * @param phoneNum
     * @return
     */
    @GetMapping(value = "/v1/sendSMS",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "发送短信服务", notes = "发送短信服务-接口")
    @ApiResponses({@ApiResponse(code = 200, response = GenericResponse.class, message = "发送成功")})
    public ResponseEntity<?> sendSMS(@RequestParam String phoneNum,
                                     @ApiParam(name = "sign", value = "签名:md5(手机号|secret)。 secret：yougu", required = true)
                                     @RequestParam String sign,HttpServletRequest request) {
        msgService.sendMsg(request,phoneNum,sign);
        return GenericResponse.ok();
    }

    /**
     * 刷新token
     */
    @ApiOperation(value = "刷新token", notes = "刷新token")
    @PostMapping(value = "/refresh_token", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> refreshToken(@RequestBody String refreshToken,HttpServletResponse response) {

        return loginService.refreshToken(refreshToken,response);
    }




}
