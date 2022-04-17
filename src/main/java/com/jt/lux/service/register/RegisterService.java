package com.jt.lux.service.register;

import com.alibaba.fastjson.JSONObject;
import com.jt.lux.entity.security.*;
import com.jt.lux.exception.ServiceException;
import com.jt.lux.mapper.security.*;
import com.jt.lux.util.Encryption;
import com.jt.lux.util.OSSClientUtil;
import com.jt.lux.util.idg.DefaultUidGenerator;
import com.jt.lux.vo.common.RegisterVO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ÃèÊö£º ×¢²á
 * @×÷Õß£º lux
 * @´´½¨ÈÕÆÚ£º 2020-8-31 15:28

 */
@Service
public class RegisterService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserLoginMapper userLoginMapper;


    @Autowired
    private DefaultUidGenerator idg;

    @Autowired
    private PartyMapper partyMapper;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PartyRoleMapper roleMapper;

    @Autowired
    private UserLoginSecurityGroupMapper userLoginSecurityGroupMapper;

    @Autowired
    private OSSClientUtil ossClientUtil;

    /**
     * ×¢²á
     * @param vo
     * @param request
     * @return
     */
    @Transactional
    public UserLogin register(RegisterVO vo, HttpServletRequest request){
        log.info("register:{}", JSONObject.toJSON(vo).toString());
        UserLogin userLogin = new UserLogin();
        String partyId = "P"+idg.nextStrId();
        //´´½¨µÇÂ½ÕË»§
        userLogin.setMobileNum(vo.getPhoneNum());
        userLogin.setPartyId(partyId);
        userLogin.setCurrentPassword(vo.getPassWord());
        userLogin.setOpenid(vo.getOpenid());
        userLogin.setEnabled("Y");
        createUserLogin(userLogin);
        //´´½¨party
        createParty(partyId);
        return userLogin;
    }

    public UserLogin createUserLogin(UserLogin userLogin){
        String password = userLogin.getCurrentPassword();
        if(StringUtils.isNotBlank(password)){
            String salt = new Encryption().createSalt();
            String encryPassword = new SimpleHash("MD5", password, salt, 1024).toString();
            userLogin.setSalt(salt);
            userLogin.setCurrentPassword(encryPassword);
            userLogin.setIsSystem("Y");
        }
        userLogin.setStatusAuth("U");//Î´ÈÏÖ¤
        userLogin.setUserLoginId(idg.nextStrId());
        userLogin.setLastUpdatedStamp(new Date());
        userLogin.setLastUpdatedTxStamp(new Date());
        userLogin.setCreatedStamp(new Date());
        userLogin.setCreatedTxStamp(new Date());
        int n = userLoginMapper.insert(userLogin);
        if(n == 0 ){
            log.error("×¢²áUserLoginÊ§°Ü{}",userLogin.getPartyId());
            throw new ServiceException("×¢²áÊ§°Ü");
        }
        return userLogin;
    }

    public void createParty(String partyId){
        Party party = new Party();
        party.setPartyId(partyId);
        party.setStatusId("PARTY_ENABLED");
        party.setCreatedStamp(new Date());
        party.setCreatedDate(new Date());
        party.setLastUpdatedStamp(new Date());
        party.setLastModifiedDate(new Date());
        party.setIsOpenAccount("N");
        int n = partyMapper.insert(party);
        if(n == 0 ){
            log.error("×¢²ápartyÊ§°Ü{}",partyId);
            throw new ServiceException("×¢²áÊ§°Ü");
        }
    }

    /**
     * ´´½¨¸öÈË
     * @param person
     * @return
     */
    public Person createPerson(Person person){
        String partyId = person.getPartyId();
        Party party = new Party();
        party.setStatusId("PARTY_ENABLED");
        party.setPartyId(partyId);
        party.setPartyTypeId("PERSON");
        int n = partyMapper.updateByPrimaryKeySelective(party);
        if(n == 0 ){
            log.error("×¢²ápartyÊ§°Ü{}",party.getPartyId());
            throw new ServiceException("×¢²áÊ§°Ü");
        }

        person.setPartyId(party.getPartyId());
        person.setLastUpdatedStamp(new Date());
        person.setLastUpdatedTxStamp(new Date());
        person.setCreatedStamp(new Date());
        person.setCreatedTxStamp(new Date());
        n = personMapper.insert(person);
        if(n == 0 ){
            log.error("×¢²ápersonÊ§°Ü{}",person.getPartyId());
            throw new ServiceException("×¢²áÊ§°Ü");
        }
        PartyRole role = new PartyRole();
        role.setPartyId(partyId);
        role.setRoleTypeId("EMPLOYEE");//Ô±¹¤
        role.setCreatedStamp(new Date());
        roleMapper.insert(role);
        return person;
    }


    /**
     * ´´½¨ÏµÍ³¹ÜÀíÔ±
     * @return
     */
    public void createSystemManager(Person person){
        String partyId = person.getPartyId();
        Party party = new Party();
        party.setStatusId("PARTY_ENABLED");
        party.setPartyId(partyId);
        party.setCreatedStamp(new Date());
        party.setCreatedDate(new Date());
        int n = partyMapper.updateByPrimaryKeySelective(party);
        if(n == 0 ){
            log.error("×¢²ápartyÊ§°Ü{}",party.getPartyId());
            throw new ServiceException("×¢²áÊ§°Ü");
        }
        person.setPartyId(party.getPartyId());
        person.setLastUpdatedStamp(new Date());
        person.setLastUpdatedTxStamp(new Date());
        person.setCreatedStamp(new Date());
        person.setCreatedTxStamp(new Date());
        n = personMapper.insert(person);
        if(n == 0 ){
            log.error("×¢²ápersonÊ§°Ü{}",person.getPartyId());
            throw new ServiceException("×¢²áÊ§°Ü");
        }

        PartyRole role = new PartyRole();
        role.setPartyId(partyId);
        role.setRoleTypeId("MANAGER");//ÏµÍ³¹ÜÀíÔ±
        role.setCreatedStamp(new Date());
        roleMapper.insert(role);

        UserLogin login = new UserLogin() ;
        login.setPartyId(partyId);
        login = userLoginMapper.selectOne(login);
        UserLoginSecurityGroup group = new UserLoginSecurityGroup();
        group.setGroupId("GROUP_SYSTEM");
        group.setUserLoginId(login.getUserLoginId());
        group.setCreatedStamp(new Date());
        group.setFromDate(new Date());
        userLoginSecurityGroupMapper.insert(group);

    }




}
