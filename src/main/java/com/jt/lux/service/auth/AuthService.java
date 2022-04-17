package com.jt.lux.service.auth;


import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @������
 * @���ߣ� lux
 * @�������ڣ� 2020-12-23 15:10

 */
@Component
public class AuthService {

    //��������kye.�û����ӽ��ܵ�key���ݡ�
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
     * ǩ��JWT������token�ķ���
     * @param id  jwt��Ψһ��ʶ����Ҫ������һ����token��
     * @param iss  jwtǩ����
     * @param subject  jwt��������û���һ��ʹ���û��ĵ�¼��
     * @param minutes  ��Ч�ڣ���λ����
     * @return  token ��Ϊһ���û�����Ч��¼����׼����һ��tkoen ���û��Ƴ���ʱ��tokenʧЧ
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
                    .signWith(signatureAlgorithm,secretKey);//�����ܳ׺��㷨
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
     * ����JWT�ַ���
     * @param jwt  �������ɵ�toekn
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
     * ��֤jwt
     * @param jwtStr
     * @return
     */
    public  Claims validateJWT(String jwtStr){
        Claims claims=null;
        try{
            claims=parseJWT(jwtStr);
            //�ɹ�
        }catch (ExpiredJwtException e){
            //token����
            e.printStackTrace();
        }catch (SignatureException e){
            //ǩ������
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return claims;
    }



}
