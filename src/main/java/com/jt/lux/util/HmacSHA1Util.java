package com.jt.lux.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.TreeMap;

public class HmacSHA1Util {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

    /**
     * 使用 HMAC-SHA1 签名方法对data进行签名
    加密后的字符串
     */
    public static String sign(String s, String key) throws Exception {
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), mac.getAlgorithm());
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(s.getBytes("UTF-8"));
        return DatatypeConverter.printBase64Binary(hash);
    }

    public static String getStringToSign(String method, String endpoint, TreeMap<String, Object> params) {
        StringBuilder s2s = new StringBuilder();
        s2s.append(method).append(endpoint).append("/?");
        // 签名时要求对参数进行字典排序，此处用TreeMap保证顺序
        for (String k : params.keySet()) {
            s2s.append(k).append("=").append(params.get(k).toString()).append("&");
        }
        return s2s.toString().substring(0, s2s.length() - 1);
    }

    /**
     * SHA1加密
     * @param str
     * @return
     */
    public static String string2Sha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @Comment SHA1实现
     * @Author Ron
     * @Date 2017年9月13日 下午3:30:36
     * @return
     */
    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // TreeMap可以自动排序
//        TreeMap<String, Object> params = new TreeMap<String, Object>();
//        params.put("Nonce", new Random().nextInt(Integer.MAX_VALUE));
//        // 实际调用时应当使用系统当前时间
//        params.put("Timestamp", System.currentTimeMillis() / 1000);
//        params.put("Region", "ap-beijing");
//        params.put("SecretId", "AKIDXtkLp3QqSpxwhHvnvoSv85vc5cvyYJbh");
//        params.put("Action", "IDCardOCR");
//        params.put("Version", "2018-11-19");
//        params.put("ImageUrl", "http://gw.jiangtai.com/res-svc/res/showFile?fileId=1730891645883522901");
//
//        String str2sign = getStringToSign("GET", "ocr.ap-beijing.tencentcloudapi.com", params);
//        String signature = sign(str2sign, "2oWy1tjlX4PVO2SefU5ZotjP2PXP2QZS");
//        System.out.println("Signature=" + signature);

        String ss =  string2Sha1("712db037ebc482fc1587609493917ce977299712db037ebc482fc2b520f04");
        String ss2 =  shaEncode("c3048c2eae53d8ea+1546930204190+7eeb57e7c3048c2eae53d8eae2598954");
        System.out.println("ss=" + ss);
        System.out.println("ss2=" + ss2);
    }

}
