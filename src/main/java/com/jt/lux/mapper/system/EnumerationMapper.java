package com.jt.lux.mapper.system;

import com.jt.lux.entity.system.Enumeration;
import com.jt.lux.util.MyMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EnumerationMapper extends MyMapper<Enumeration> {

    List<Enumeration> selectByEnumTypeId(String enumTypeId);
}