package com.jt.lux.mapper.security;

import com.jt.lux.entity.security.UserLogin;
import com.jt.lux.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Component
@Mapper
@Repository
public interface UserLoginMapper extends MyMapper<UserLogin> {

    UserLogin findByMobileNum(String phoneNum);

    UserLogin findByPartyId(String partyId);
}