package com.jt.lux.util;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;


/**
 * 加密工具
 */
public class SignatureUntils {
    /**
     * 生成签名；
     *
     * @param params
     * @return
     */
    public static  String signForInspiry(Map<String, Object> params,String key) {

        String sbkey = unionpayASCII(params,key);
        //MD5加密,结果转换为大写字符
        System.out.println(sbkey);
        String sign = getSHA256(sbkey).toUpperCase();
        System.out.println(sign);
        return sign;
    }



    /**
     *
     *方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串 末尾
     */
    public static String unionpayASCII(Map<String, Object> map,String signkey){
        Object[] keys =  map.keySet().toArray();
        Arrays.sort(keys);
        StringBuilder originStr = new StringBuilder();
        for(Object key:keys){
            if(null!=map.get(key)&&!map.get(key).toString().equals(""))
                originStr.append(key).append("=").append(JSONObject.toJSON(map.get(key)).toString()).append("&");
        }
        String str = originStr.substring(0,originStr.length()-1).toString();
        str = str + signkey;
        return str;
    }



    /**
     * 利用java原生的类实现SHA256加密
     * @param str 加密后的报文
     * @return
     */
    public static String getSHA256(String str){
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

}
