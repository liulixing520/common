package com.jt.lux.service.register;

import com.alibaba.fastjson.JSONObject;
import com.jt.lux.entity.security.Party;
import com.jt.lux.entity.security.Person;
import com.jt.lux.entity.security.UserLogin;
import com.jt.lux.exception.BizException;
import com.jt.lux.exception.ParamException;
import com.jt.lux.exception.PassWordExpiredException;
import com.jt.lux.exception.ServiceException;
import com.jt.lux.mapper.security.PartyMapper;
import com.jt.lux.mapper.security.PersonMapper;
import com.jt.lux.mapper.security.UserLoginMapper;
import com.jt.lux.util.Constants;
import com.jt.lux.util.Encryption;
import com.jt.lux.util.GenericDataResponse;
import com.jt.lux.util.IdGenerator;
import com.jt.lux.vo.common.LoginVO;
import com.jt.lux.vo.common.RegisterVO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @描述： 注册
 * @作者： lux
 * @创建日期： 2020-8-31 15:28
 * @版权： 江泰保险经纪股份有限公司
 */
@Service
public class RegisterService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private IdGenerator idg;

    @Autowired
    private PartyMapper partyMapper;

    @Autowired
    private PersonMapper personMapper;

    /**
     * 注册
     * @param vo
     * @param request
     * @return
     */
    public ResponseEntity<GenericDataResponse<UserLogin>> register(RegisterVO vo, HttpServletRequest request){
        log.info("register:{}", JSONObject.toJSON(vo).toString());
        UserLogin userLogin = userLoginMapper.selectByPrimaryKey(vo.getPhoneNum());
        if (null != userLogin){
            log.error(Constants.PHONENUM_ISEXIST,vo.getPhoneNum());
            throw new ServiceException(Constants.PHONENUM_ISEXIST);
        }else {
            userLogin = new UserLogin();
        }

        String password = "";
        if(StringUtils.isEmpty(vo.getPassWord())){
            password = vo.getPassWord();
        }
        Encryption en = new Encryption();
        String salt = en.createSalt();
        password = new SimpleHash("MD5", password, salt, 1024).toString();

        Person person = new Person();
        person.setMobileNum(vo.getPhoneNum());

        person = createPerson(person);

        userLogin.setSalt(salt);
        userLogin.setCurrentPassword(password);
        userLogin.setPartyId(person.getPartyId());

        return GenericDataResponse.okWithData(userLogin);
    }

    public Person createPerson(Person person){
        if(StringUtils.isBlank(person.getMobileNum())){
            throw new ServiceException("手机号不存在");
        }

        Person t = new Person();
        t.setMobileNum(person.getMobileNum());
        if(personMapper.select(t).size()>0){
            throw new ServiceException("手机号已被占用，无法创建: "+person.getMobileNum());
        }


        Party party = new Party();
        party.setPartyTypeId("PERSON");
        party.setStatusId("PARTY_ENABLED");
        int n = partyMapper.insertParty(party);
        if(n == 0 ){
            throw new ServiceException("party插入失败");
        }

        person.setPartyId(party.getPartyId());
        personMapper.insert(person);

        return person;
    }


}
