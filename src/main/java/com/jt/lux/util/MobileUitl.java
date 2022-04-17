package com.jt.lux.util;

import com.jt.lux.entity.security.ValidParamScope;
import com.jt.lux.mapper.security.ValidParamScopeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class MobileUitl {

    @Autowired
    private ValidParamScopeMapper validParamScopeMapper;

    static  MobileUitl mobileUitl;

    @PostConstruct
    public void init(){
        mobileUitl=this;
        mobileUitl.validParamScopeMapper=this.validParamScopeMapper;
    }


    /**
     * 验证手机号是否合法
     * @param mobile
     * @return
     */
    public static boolean isMobileNO(String mobile) {
        if (mobile.length() != 11) {
            return false;
        } else {
            Example example=new Example(ValidParamScope.class);
            example.createCriteria().andEqualTo("type","01").andEqualTo("allowValue",mobile.substring(0,3));
            List<ValidParamScope> validParamScopes = mobileUitl.validParamScopeMapper.selectByExample(example);
            if (validParamScopes==null||validParamScopes.size()<1){
                return false;
            }
            return true;
        }
    }
}
