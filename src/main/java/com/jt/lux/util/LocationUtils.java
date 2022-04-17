package com.jt.lux.util;

/**
 * ����������γ��֮��ľ���
 */
public class LocationUtils {

    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * @param lng1 ����1
     * @param lat1 γ��1
     * @param lng2 ����2
     * @param lat2 γ��2
     * @return double�������;���
     * @Title: getDistance
     * @Description: ͨ����γ�Ȼ�ȡ����(��λ �� ��)
     * @Author: XXXX
     * @Date: 2018��12��25�� ����3:15:21
     */
    public static String getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        return String.format("%.2f", s);
    }

    public static void main(String[] args) {
        String distance = getDistance(116.334201, 39.89584, 116.333482, 39.856747);
        System.out.println("����" + distance+ "����");

    }
}
