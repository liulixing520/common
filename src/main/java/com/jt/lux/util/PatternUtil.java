package com.jt.lux.util;

/**
 * ������ʽƥ��
 * @author tyg
 * @date   2018��9��21������4:32:27
 */
public class PatternUtil {

    /** �ֻ�����ƥ�� */
    public static final String PHONE_REG = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
    /** ��������ƥ�� */
    public static final String MOBILE_REG = "^\\d{3}-\\d{8}|\\d{4}-\\d{7}$";
    /** ����ƥ�� */
    public static final String CHIINESE_REG = "^[\\u4e00-\\u9fa5]{0,}$";
    /** Ӣ�ĺ�����ƥ��(�����ִ�Сд) */
    public static final String LETTER_NUMBER_REG = "^[A-Za-z0-9]+$";
    /** ���㿪ͷ��������λС�������� */
    public static final String DECIMAL_REG = "^([1-9][0-9]*)+(.[0-9]{1,2})?$";
    /** ������ */
    public static final String ONLY_NUMBER_REG = "^[0-9]{1,}$";
    /** ����ĸ */
    public static final String ONLY_LETTER_REG = "^[a-zA-Z]{1,}$";
    /** ����email */
    public static final String EMAIL_REG = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /** ���ڹ��� */
    public static final String DATE_YMD = "^(\\d{4}-\\d{2}-\\d{2})$";
    /** ʱ����� */
    public static final String DATE_YMDHDS = "^(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})$";
    /** ���п�����λ�� */
    public final static String BANK_CARD_NUMBER = "^\\d{16}|\\d{19}$";
    /** ���֤����λ������ */
    public final static String ID_CARD = "^\\d{15}|(\\d{17}[0-9,x,X])$";

    /**
     * �Ƿ�Ϊ�ֻ�����
     * @param phone
     * @return
     * @return boolean
     * @author tyg
     * @date   2018��9��25������9:44:38
     */
    public static boolean isPhone(String phone) {
        return phone.matches(PHONE_REG);
    }

    /**
     * �Ƿ�Ϊ��������
     * @param mobile
     * @return
     * @return boolean
     * @author tyg
     * @date   2018��9��25������9:44:38
     */
    public static boolean isMobile(String mobile) {
        return mobile.matches(MOBILE_REG);
    }

    public static void main(String[] args) {
        System.out.println(isPhone("13228116626"));
        System.out.println(isMobile("028-63358547"));
    }
}