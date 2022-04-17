package com.jt.lux.mapper.security;

import com.jt.lux.entity.security.Person;
import com.jt.lux.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PersonMapper extends MyMapper<Person> {

}