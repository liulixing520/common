package com.jt.lux.util;

import java.nio.charset.Charset;

/**
 * @������
 * @���ߣ� lux
 */
public class Encoding {
    public static String getEncoding(String str)
    {
        String encode;

        encode = "UTF-16";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return encode;
            }
        }
        catch(Exception ex) {}

        encode = "ASCII";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return "�ַ���<< " + str + " >>�н������ֺ�Ӣ����ĸ��ɣ��޷�ʶ��������ʽ";
            }
        }
        catch(Exception ex) {}

        encode = "ISO-8859-1";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return encode;
            }
        }
        catch(Exception ex) {}

        encode = "GB2312";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return encode;
            }
        }
        catch(Exception ex) {}

        encode = "UTF-8";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return encode;
            }
        }
        catch(Exception ex) {}

        /*
	 *......������
	 */

        return "δʶ������ʽ";
    }

    public static void main(String[] args)
    {
        //��ȡϵͳĬ�ϱ���
        System.out.println("ϵͳĬ�ϱ��룺" + System.getProperty("file.encoding")); //��ѯ���GBK
        //ϵͳĬ���ַ�����
        System.out.println("ϵͳĬ���ַ����룺" + Charset.defaultCharset()); //��ѯ���GBK
        //����ϵͳ�û�ʹ�õ�����
        System.out.println("ϵͳĬ�����ԣ�" + System.getProperty("user.language")); //��ѯ���zh

        System.out.println();

        String s1 = "hi, nice to meet you!";
        String s2 = "hi, �����ˣ�";

        System.out.println(getEncoding(s1));
        System.out.println(getEncoding(s2));
    }
}
