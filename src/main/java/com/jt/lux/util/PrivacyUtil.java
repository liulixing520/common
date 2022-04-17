package com.jt.lux.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * ��־�����࣬��������������Ϣ����������
 * @author tyg
 * @date   2018��5��5������4:01:22
 */
public class PrivacyUtil {

    //�����˻�����ʾǰ�����ģ�������622848******4568
    public static String encryptBankAcct(String bankAcct) {
        if (bankAcct == null) {
            return "";
        }
        return replaceBetween(bankAcct, 6, bankAcct.length() - 4, null);
    }

    //���֤���룺��ʾǰ�����ģ�������110601********2015
    public static String encryptIdCard(String idCard) {
        if (idCard == null) {
            return "";
        }
        return replaceBetween(idCard, 6, idCard.length() - 4, null);
    }

    //�ͻ�email����ʾǰ�����ģ�������abxx@xxx.com
    public static String encryptEmail(String email) {
        //�ж��Ƿ�Ϊ�����ַ
        if (email == null || !Pattern.matches(com.jt.lux.util.PatternUtil.EMAIL_REG, email)) {
            return "";
        }

        StringBuilder sb = new StringBuilder(email);
        //�����˺���ֻ��ʾǰ��λ
        int at_position = sb.indexOf("@");
        if (at_position > 2) {
            sb.replace(2, at_position, StringUtils.repeat("x", at_position - 2));
        }
        //������������
        int period_position = sb.lastIndexOf(".");
        sb.replace(at_position + 1, period_position, StringUtils.repeat("x", period_position - at_position - 1));
        return sb.toString();
    }

    //�ֻ�����ʾǰ�����ģ�������189****3684
    public static String encryptPhoneNo(String phoneNo) {
        if (phoneNo == null) {
            return "";
        }
        return replaceBetween(phoneNo, 3, phoneNo.length() - 4, null);
    }

    /**
     * @param object ���������
     * @param fieldNames ��Ҫ���������
     * @return
     * ������ת��Ϊ�ַ���������������Ϣ���д���
     */
    public static String encryptSensitiveInfo(Object object, String[] fieldNames) {
        return encryptSensitiveInfo(object, fieldNames, null);
    }

    /**
     * @param object ���������
     * @param fieldNames ��Ҫ���������
     * @param excludes ����Ҫ��ʾ������
     * @return
     * ������ת��Ϊ�ַ���������������Ϣ���д���
     */
    public static String encryptSensitiveInfo(Object object, String[] fieldNames, String[] excludes) {
        if (fieldNames == null) {
            fieldNames = new String[0];
        }
        //�ϲ�����
        Set<String> set = new HashSet<String>(Arrays.asList(fieldNames));
        if (excludes != null) {
            for (int i = 0; i < excludes.length; i++) {
                set.add(excludes[i]);
            }
        }
        //������ת��Ϊ�ַ���
        String str = ReflectionToStringBuilder.toStringExclude(object, set.toArray(new String[0]));

        //����������Ϣ
        StringBuilder sb = new StringBuilder(str);
        sb.deleteCharAt(sb.length() - 1);
        for (int i = 0; i < fieldNames.length; i++) {
            String fieldName = fieldNames[i];
            try {
                Field f = object.getClass().getDeclaredField(fieldName);
                f.setAccessible(true);
                String value = encryptSensitiveInfo(String.valueOf(f.get(object)));
                if (i != 0 || sb.charAt(sb.length() - 1) != '[') {
                    sb.append(",");
                }
                sb.append(fieldName).append("=").append(value);
            } catch (Exception e) {
            }
        }
        sb.append("]");
        str = sb.toString();
        return str;
    }

    /**
     * ��������Ϣ���д���ʹ��������ʽ�ж�ʹ�����ֹ���
     * @param sourceStr ��Ҫ�����������Ϣ
     * @return
     * @return String
     * @author tyg
     * @date   2018��5��5������3:59:28
     */
    public static String encryptSensitiveInfo(String sourceStr) {
        if (sourceStr == null) {
            return "";
        }
        if (Pattern.matches(com.jt.lux.util.PatternUtil.PHONE_REG, sourceStr)) {
            return encryptPhoneNo(sourceStr);
        } else if (Pattern.matches(com.jt.lux.util.PatternUtil.EMAIL_REG, sourceStr)) {
            return encryptEmail(sourceStr);
        } else if (Pattern.matches(com.jt.lux.util.PatternUtil.BANK_CARD_NUMBER, sourceStr)) {
            return encryptBankAcct(sourceStr);
        } else if (Pattern.matches(com.jt.lux.util.PatternUtil.ID_CARD, sourceStr)) {
            return encryptIdCard(sourceStr);
        } else {
            return sourceStr;
        }
    }

    /**
     * ���ַ�����ʼλ�õ�����λ��֮����ַ���ָ���ַ��滻
     * @param sourceStr �������ַ���
     * @param begin	��ʼλ��
     * @param end	����λ��
     * @param replacement �滻�ַ�
     * @return
     */
    private static String replaceBetween(String sourceStr, int begin, int end, String replacement) {
        if (sourceStr == null) {
            return "";
        }
        if (replacement == null) {
            replacement = "*";
        }
        int replaceLength = end - begin;
        if (StringUtils.isNotBlank(sourceStr) && replaceLength > 0) {
            StringBuilder sb = new StringBuilder(sourceStr);
            sb.replace(begin, end, StringUtils.repeat(replacement, replaceLength));
            return sb.toString();
        } else {
            return sourceStr;
        }
    }

    public static void main(String[] args) {
        System.out.println(PrivacyUtil.encryptBankAcct("6228482462893085616"));//622848*********5616
        System.out.println(PrivacyUtil.encryptPhoneNo("13228116626"));//132****6626
        System.out.println(PrivacyUtil.encryptIdCard("510658199107356847"));//510658********6847
        System.out.println(PrivacyUtil.encryptEmail("1182786142@qq.com"));//11xxxxxxxx@xx.com
        System.out.println(PrivacyUtil.encryptSensitiveInfo("13228116626"));//

    }
}