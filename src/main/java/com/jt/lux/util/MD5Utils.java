package com.jt.lux.util;

/**
 * @������
 * @���ߣ� lux
 * @�������ڣ� 2018-9-7 11:54
 */
public class MD5Utils {

    public static String getMD5(String source) {
        return getMD5(source.getBytes());
    }

    public static String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] = { // �������ֽ�ת���� 16 ���Ʊ�ʾ���ַ�
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte tmp[];
            synchronized (MD5Utils.class) {
                md.update(source);
                tmp = md.digest(); // MD5 �ļ�������һ�� 128 λ�ĳ�������
            }
            // ���ֽڱ�ʾ���� 16 ���ֽ�
            char str[] = new char[16 * 2]; // ÿ���ֽ��� 16 ���Ʊ�ʾ�Ļ���ʹ�������ַ���
            // ���Ա�ʾ�� 16 ������Ҫ 32 ���ַ�
            int k = 0; // ��ʾת������ж�Ӧ���ַ�λ��
            for (int i = 0; i < 16; i++) { // �ӵ�һ���ֽڿ�ʼ���� MD5 ��ÿһ���ֽ�
                // ת���� 16 �����ַ���ת��
                byte byte0 = tmp[i]; // ȡ�� i ���ֽ�
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // ȡ�ֽ��и� 4 λ������ת��,
                // >>> Ϊ�߼����ƣ�������λһ������
                str[k++] = hexDigits[byte0 & 0xf]; // ȡ�ֽ��е� 4 λ������ת��
            }
            s = new String(str); // ����Ľ��ת��Ϊ�ַ���

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void main(String[] args) {
        String s = "13126807958|yougu";
        System.out.println(MD5Utils.getMD5(s));
    }
}
