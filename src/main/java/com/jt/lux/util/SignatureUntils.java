package com.jt.lux.util;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;


/**
 * ���ܹ���
 */
public class SignatureUntils {
    /**
     * ����ǩ����
     *
     * @param params
     * @return
     */
    public static  String signForInspiry(Map<String, Object> params,String key) {

        String sbkey = unionpayASCII(params,key);
        //MD5����,���ת��Ϊ��д�ַ�
        System.out.println(sbkey);
        String sign = getSHA256(sbkey).toUpperCase();
        System.out.println(sign);
        return sign;
    }



    /**
     *
     *������;: �����д�����������ֶ����� ASCII ���С���������ֵ��򣩣���������url������ ĩβ
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
     * ����javaԭ������ʵ��SHA256����
     * @param str ���ܺ�ı���
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
     * ��byteתΪ16����
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1�õ�һλ�Ľ��в�0����
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

}
