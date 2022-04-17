package com.jt.lux.mapper.security;

import com.jt.lux.entity.security.UserLoginSecurityGroup;
import com.jt.lux.util.MyMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserLoginSecurityGroupMapper extends MyMapper<UserLoginSecurityGroup> {

    List<UserLoginSecurityGroup> selectByGroupId(String groupId);

}