package com.jt.lux.util;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author��
 * @Date��
 * @Description��������Ĳ����Ĺ�����
 */
public final class ClassUtil {

    private ClassUtil() {
        throw new Error("�����಻����ʵ������");
    }

    /**
     * ��ȡ������
     * @param targetObj Ҫ��ȡ���Ե���
     * @return ���������Եļ���
     */
    public static Field[] getClassAttribute(Object targetObj){

        Class<?> objectClass = targetObj.getClass();
        return objectClass.getDeclaredFields();

    }

    /**
     * ��ȡ���������get��set����
     * @param targetObj Ҫ��ȡ���Ե���
     * @param methodKeyword get����set�ؼ���
     * @return ������get��set�����ļ���
     */
    public static List<Method> getMethod(Object targetObj,String methodKeyword){
        List<Method> methodList = new ArrayList<>();

        Class<?> objectClass = targetObj.getClass();

        Field[] field = objectClass.getDeclaredFields();
        for (int i = 0;i<field.length;i++){
            //��ȡ����������װ������
            String fieldName = field[i].getName();
            String getMethodName = methodKeyword
                    + fieldName.substring(0, 1).toUpperCase()
                    + fieldName.substring(1);

            try {
                Method method = objectClass.getMethod(getMethodName,new Class[]{});
                methodList.add(method);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return methodList;
    }

    /**
     * ��ȡ���������get����
     * @param targetObj Ҫ��ȡ���Ե���
     * @return �����෽���ļ���
     */
    public static List<Method> getMethodGet(Object targetObj){
        return getMethod(targetObj,"get");
    }

    /**
     * ��ȡ���������set����
     * @param targetObj Ҫ��ȡ���Ե���
     * @return �����෽���ļ���
     */
    public static List<Method> getMethodSet(Object targetObj){
        return getMethod(targetObj,"set");
    }
}




