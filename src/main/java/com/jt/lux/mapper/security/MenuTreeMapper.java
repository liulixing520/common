package com.jt.lux.mapper.security;

import com.jt.lux.entity.security.MenuTree;
import com.jt.lux.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MenuTreeMapper extends MyMapper<MenuTree> {

    List<MenuTree> selectMenuByGroupId(String groupId);

    List<MenuTree> selectAllMenuTree(@Param("pn")Integer pn, @Param("ps")Integer ps);
    Integer selectAllMenuTreeCount();
}