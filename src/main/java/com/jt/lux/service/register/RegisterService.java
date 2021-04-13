package com.jt.lux.service.register;

import com.alibaba.fastjson.JSONObject;
import com.jt.lux.entity.security.Party;
import com.jt.lux.entity.security.Person;
import com.jt.lux.entity.security.UserLogin;
import com.jt.lux.exception.ServiceException;
import com.jt.lux.mapper.security.PartyMapper;
import com.jt.lux.mapper.security.PersonMapper;
import com.jt.lux.mapper.security.UserLoginMapper;
import com.jt.lux.util.Encryption;
import com.jt.lux.util.GenericDataResponse;
import com.jt.lux.util.IdGenerator;
import com.jt.lux.vo.common.RegisterVO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
        UserLogin userLogin = new UserLogin();
        Person person = new Person();
        if(StringUtils.isNotBlank(vo.getPhoneNum())){
            person.setMobileNum(vo.getPhoneNum());
        }
        if(StringUtils.isNotBlank(vo.getOpenid())){
            person.setOpenId(vo.getOpenid());
        }

        //创建person用户
        createPerson(person);


        if(StringUtils.isNotBlank(vo.getPassWord())){
            userLogin.setCurrentPassword(vo.getPassWord());
        }
        userLogin.setPartyId(person.getPartyId());
        //创建登陆账户
        createUserLogin(userLogin);

        return GenericDataResponse.okWithData(userLogin);
    }

    public UserLogin createUserLogin(UserLogin userLogin){
        String password = userLogin.getCurrentPassword();
        if(StringUtils.isNotBlank(password)){
            String salt = new Encryption().createSalt();
            String encryPassword = new SimpleHash("MD5", password, salt, 1024).toString();
            userLogin.setSalt(salt);
            userLogin.setCurrentPassword(encryPassword);
        }
        userLogin.setUserLoginId("U"+idg.nextId());
        int n = userLoginMapper.insert(userLogin);
        if(n == 0 ){
            log.error("注册UserLogin失败{}",userLogin.getPartyId());
            throw new ServiceException("注册失败");
        }
        return userLogin;
    }

    public Person createPerson(Person person){

        Party party = new Party();
        party.setPartyTypeId("PERSON");
        party.setStatusId("PARTY_ENABLED");
        int n = partyMapper.insertParty(party);
        if(n == 0 ){
            log.error("注册party失败{}",party.getPartyId());
            throw new ServiceException("注册失败");
        }

        person.setPartyId(party.getPartyId());
        n = personMapper.insert(person);
        if(n == 0 ){
            log.error("注册person失败{}",person.getPartyId());
            throw new ServiceException("注册失败");
        }
        return person;
    }


}
