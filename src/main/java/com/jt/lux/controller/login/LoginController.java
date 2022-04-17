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
 * @�������û���¼
 * @���ߣ�
 * @�������ڣ� 2018-7-25 14:39
 * @��Ȩ��
 */
@Api(value = "��¼���", tags = { "��¼���-�ӿ�" })
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
     * �û���¼
     */
    @ApiOperation(value = "�û���¼", notes = "�û���¼-�ӿ�")
    @PostMapping(value = "/v1/userLogin", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GenericDataResponse<UserLogin>> userLogin(@Valid @RequestBody LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) {
        return loginService.userLogin(loginVO, request,response);
    }

    /**
     * �û��ǳ�
     */
    @ApiOperation(value = "�û��ǳ�", notes = "�û��ǳ�-�ӿ�")
    @PostMapping(value = "/v1/logout", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String partyId = (String) request.getAttribute("partyId");
        loginService.logout(authorization,partyId);

        return GenericDataResponse.ok();
    }



    /**
     * ���Ͷ��ŷ���
     * @param phoneNum
     * @return
     */
    @GetMapping(value = "/v1/sendSMS",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "���Ͷ��ŷ���", notes = "���Ͷ��ŷ���-�ӿ�")
    @ApiResponses({@ApiResponse(code = 200, response = GenericResponse.class, message = "���ͳɹ�")})
    public ResponseEntity<?> sendSMS(@RequestParam String phoneNum,
                                     @ApiParam(name = "sign", value = "ǩ��:md5(�ֻ���|secret)�� secret��yougu", required = true)
                                     @RequestParam String sign,HttpServletRequest request) {
        msgService.sendMsg(request,phoneNum,sign);
        return GenericResponse.ok();
    }

    /**
     * ˢ��token
     */
    @ApiOperation(value = "ˢ��token", notes = "ˢ��token")
    @PostMapping(value = "/refresh_token", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> refreshToken(@RequestBody String refreshToken,HttpServletResponse response) {

        return loginService.refreshToken(refreshToken,response);
    }




}
