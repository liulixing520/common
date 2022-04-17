package com.jt.lux.service.auth;


import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @描述：
 * @作者： lux
 * @创建日期： 2020-12-23 15:10

 */
@Component
public class AuthService {

    //服务器的kye.用户做加解密的key数据。
    private static final String JWT_SECERT="jwt_secert";



    public  SecretKey generalKey(){
        try {
            byte[] encodedKey=JWT_SECERT.getBytes("UTF-8");
            SecretKey key=new SecretKeySpec(encodedKey,0,encodedKey.length,"AES");
            return key;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 签发JWT，创建token的方法
     * @param id  jwt的唯一标识，主要用来做一次性token。
     * @param iss  jwt签发者
     * @param subject  jwt所面向的用户。一般使用用户的登录名
     * @param minutes  有效期，单位分钟
     * @return  token 是为一个用户的有效登录周期准备的一个tkoen 。用户推出或超时，token失效
     */
    public  String createToken(String id,String iss,String subject,int minutes,Map<String, Object> tokenData){
        try {
            SignatureAlgorithm signatureAlgorithm= SignatureAlgorithm.HS256;
            Date expiryDate = getExpiryDate(minutes);
            SecretKey secretKey=generalKey();
            JwtBuilder builder= Jwts.builder().setClaims(tokenData)
                    .setId(id)
                    .setIssuer(iss)
                    .setSubject(subject)
                    .setIssuedAt(new Date())
                    .setExpiration(expiryDate)
                    .signWith(signatureAlgorithm,secretKey);//设置密匙和算法
            return builder.compact();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    private static  Date getExpiryDate(int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    /**
     * 解析JWT字符串
     * @param jwt  就是生成的toekn
     * @return
     */
    public  Claims parseJWT(String jwt){
        SecretKey secretKey=generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 验证jwt
     * @param jwtStr
     * @return
     */
    public  Claims validateJWT(String jwtStr){
        Claims claims=null;
        try{
            claims=parseJWT(jwtStr);
            //成功
        }catch (ExpiredJwtException e){
            //token过期
            e.printStackTrace();
        }catch (SignatureException e){
            //签名错误
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return claims;
    }



}
