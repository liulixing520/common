package com.jt.lux.service.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @描述：
 * @作者： lux
 * @创建日期： 2020-12-23 15:10
 * @版权： 江泰保险经纪股份有限公司
 */
public class AuthService {


    private String createToken(String signingKey, int minutes, Map<String, Object> tokenData) {
        Date expiryDate = getExpiryDate(minutes);
        return Jwts.builder().setClaims(tokenData)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, signingKey).compact();
    }

    private Date getExpiryDate(int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

}
