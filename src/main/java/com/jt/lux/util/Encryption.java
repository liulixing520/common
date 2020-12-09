package com.jt.lux.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

@Component
@Slf4j
public class Encryption {



    /**
     * 生成盐(128个字符)  盐的长度应该等同于加密后字符串的长度
     * @return
     */
    public String createSalt() {
        char[] chars="0123456789abcdefghijklmnopqrwtuvzxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] saltchars=new char[128];
        Random random = new SecureRandom();
        for(int i=0;i<128;i++)
        {
            int n=random.nextInt(62);
            saltchars[i]=chars[n];
        }
        String salt=new String(saltchars);
        return salt;
    }


    /**
     * SHA512加密(128个字符)
     * @param pwd
     * @return
     */
    public String sha512(String pwd) {
        String shaPwd = null;
        if (pwd != null && pwd.length() > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
                messageDigest.update(pwd.getBytes());
                byte byteBuffer[] = messageDigest.digest();
                StringBuilder strHexString = new StringBuilder();
                for (byte aByteBuffer : byteBuffer) {
                    String hex = Integer.toHexString(0xff & aByteBuffer);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                shaPwd = strHexString.toString();
            } catch (Exception e) {
                log.error( e.getMessage(),e);
            }
        }
        return shaPwd;
    }

    /* 测试用
        public static void main(String[] args) {
            String str = "test";
            Encryption en = new Encryption();
            String salt = en.createSalt();
            str = salt+str;
            String sha512 = en.sha512(str);
            System.out.println("salt="+salt);
            System.out.println("sha512="+sha512);
        }
        */
}