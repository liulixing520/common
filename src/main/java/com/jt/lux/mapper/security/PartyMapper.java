package com.jt.lux.mapper.security;

import com.jt.lux.entity.security.Party;
import com.jt.lux.util.MyMapper;
import org.springframework.stereotype.Component;

@Component
public interface PartyMapper extends MyMapper<Party> {

    int insertParty(Party party);
}