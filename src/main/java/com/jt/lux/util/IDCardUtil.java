package com.jt.lux.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description�����֤�ŵ�util
 * @Author:
 * @Date: Created in 11:26 2019-03-27
 * @Modified By:
 */
public class IDCardUtil {
    /**
     * 15λ���֤��
     */
    private static final Integer FIFTEEN_ID_CARD=15;
    /**
     * 18λ���֤��
     */
    private static final Integer EIGHTEEN_ID_CARD=18;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * �������֤�Ż�ȡ�Ա�
     * @param IDCard
     * @return
     */
    public static String getSex(String IDCard){
        String sex ="";
        if (StringUtils.isNotBlank(IDCard)){
            //15λ���֤��
            if (IDCard.length() == FIFTEEN_ID_CARD){
                if (Integer.parseInt(IDCard.substring(14, 15)) % 2 == 0) {
                    sex = "Ů";
                } else {
                    sex = "��";
                }
                //18λ���֤��
            }else if(IDCard.length() == EIGHTEEN_ID_CARD){
                // �ж��Ա�
                if (Integer.parseInt(IDCard.substring(16).substring(0, 1)) % 2 == 0) {
                    sex = "Ů";
                } else {
                    sex = "��";
                }
            }
        }
        return sex;
    }

    /**
     * �������֤�Ż�ȡ����
     * @param IDCard
     * @return
     */
    public static Integer getAge(String IDCard){
        Integer age = 0;
        Date date = new Date();
        if (StringUtils.isNotBlank(IDCard)&& isValid(IDCard)){
            //15λ���֤��
            if (IDCard.length() == FIFTEEN_ID_CARD){
                // ���֤�ϵ����(15λ���֤Ϊ1980��ǰ��)
                String uyear = "19" + IDCard.substring(6, 8);
                // ���֤�ϵ��·�
                String uyue = IDCard.substring(8, 10);
                // ��ǰ���
                String fyear = format.format(date).substring(0, 4);
                // ��ǰ�·�
                String fyue = format.format(date).substring(5, 7);
                if (Integer.parseInt(uyue) <= Integer.parseInt(fyue)) {
                    age = Integer.parseInt(fyear) - Integer.parseInt(uyear) + 1;
                    // ��ǰ�û���û����
                } else {
                    age = Integer.parseInt(fyear) - Integer.parseInt(uyear);
                }
                //18λ���֤��
            }else if(IDCard.length() == EIGHTEEN_ID_CARD){
                // ���֤�ϵ����
                String year = IDCard.substring(6).substring(0, 4);
                // ���֤�ϵ��·�
                String yue = IDCard.substring(10).substring(0, 2);
                // ��ǰ���
                String fyear = format.format(date).substring(0, 4);
                // ��ǰ�·�
                String fyue = format.format(date).substring(5, 7);
                // ��ǰ�·ݴ����û�������·ݱ�ʾ�ѹ�����
                if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) {
                    age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;
                    // ��ǰ�û���û������
                } else {
                    age = Integer.parseInt(fyear) - Integer.parseInt(year);
                }
            }
        }
        return age;
    }

    /**
     * ��ȡ��������  yyyy��MM��dd��
     * @param IDCard
     * @return
     */
    public static String getBirthday(String IDCard){
        String birthday="";
        String year="";
        String month="";
        String day="";
        if (StringUtils.isNotBlank(IDCard)){
            //15λ���֤��
            if (IDCard.length() == FIFTEEN_ID_CARD){
                // ���֤�ϵ����(15λ���֤Ϊ1980��ǰ��)
                year = "19" + IDCard.substring(6, 8);
                //���֤�ϵ��·�
                month = IDCard.substring(8, 10);
                //���֤�ϵ�����
                day= IDCard.substring(10, 12);
                //18λ���֤��
            }else if(IDCard.length() == EIGHTEEN_ID_CARD){
                // ���֤�ϵ����
                year = IDCard.substring(6).substring(0, 4);
                // ���֤�ϵ��·�
                month = IDCard.substring(10).substring(0, 2);
                //���֤�ϵ�����
                day=IDCard.substring(12).substring(0,2);
            }
            birthday=year+"��"+month+"��"+day+"��";
        }
        return birthday;
    }

    /**
     * ���֤��֤
     * @param id ��������
     * @return �Ƿ���Ч
     */
    public static boolean isValid(String id){
        Boolean validResult = true;
        //У�鳤��ֻ��Ϊ15��18
        int len = id.length();
        if (len != FIFTEEN_ID_CARD && len != EIGHTEEN_ID_CARD){
            validResult = false;
        }
        //У������
        if (!validDate(id)){
            validResult = false;
        }
        return validResult;
    }

    /**
     * У������
     * @param id
     * @return
     */
    private static boolean validDate(String id)
    {
        try
        {
            String birth = id.length() == 15 ? "19" + id.substring(6, 12) : id.substring(6, 14);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date birthDate = sdf.parse(birth);
            if (!birth.equals(sdf.format(birthDate))){
                return false;
            }
        }
        catch (ParseException e)
        {
            return false;
        }
        return true;
    }
}
