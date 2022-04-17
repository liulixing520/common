package com.jt.lux.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author��
 * @Date��
 * @Description��Excel���������࣬������ClassUtil������
 */
@Component
public final class ExcelExport {

    /**
     * ����������ݵ���excel������
     * @param response ���ص�HttpServletResponse
     * @param importlist Ҫ�����Ķ���ļ���
     * @param attributeNames ����ÿ������������excel���ж�Ӧ�ı����ַ��������飨�밴������������������ַ����������е�λ�ã�
     * @param heardName �����ļ� ��������
     * @param cnt �ϲ���Ԫ���� ��һ������ַ��������鳤��-1��
     */
    public static void export(HttpServletResponse response, List<?> importlist, String[] attributeNames,
                              String heardName, int cnt ) {
        //��ȡ���ݼ�
        List<?> datalist = importlist;

        //����һ��������
        HSSFWorkbook workbook = new HSSFWorkbook();
        //����һ�����
        HSSFSheet sheet = workbook.createSheet();
        //���ñ��Ĭ���п��Ϊ15���ֽ�
        sheet.setDefaultColumnWidth((short) 18);
        //�������ñ������
        HSSFCellStyle style = workbook.createCellStyle();

        //��ȡ�ֶ�������
        String[] tableAttributeName = attributeNames;
        //��ȡ��������
        Field[] fields = ClassUtil.getClassAttribute(importlist.get(0));
        //��ȡ����get����
        List<Method> methodList = ClassUtil.getMethodGet(importlist.get(0));

        //���ñ�����ʽ
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        HSSFFont headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setBold(true);
//        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFont(headerFont);
        CellRangeAddress cra0 = new CellRangeAddress(0, 1, 0, cnt);// �ϲ���Ԫ��
        sheet.addMergedRegion(cra0);
        //�����������
        Row row = sheet.createRow(0);
        Cell cell1 = row.createCell(0);
        cell1.setCellValue(heardName);
        cell1.setCellStyle(headerStyle);

        // ����������ʽ
        HSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 10);

        style.setFont(titleFont);
//        style.setAlignment(HorizontalAlignment.CENTER);
        //ѭ���ֶ������飬����������
        row = sheet.createRow(2);
        for (int j = 0; j< tableAttributeName.length; j++){
            //������
            Cell cell = row.createCell(j);
            cell.setCellStyle(style);
            //���õ�Ԫ����ΪString
//            cell.setCellType(CellType.);
            cell.setCellValue(transCellType(tableAttributeName[j]));
        }
        //������ͨ��
        for (int i = 0;i<datalist.size();i++){
            //��Ϊ��һ���Ѿ����ڴ��������У��ʴӵڶ��п�ʼ����
            row = sheet.createRow(i+3);
            //����ǵ�һ�о�����Ϊ������
            Object targetObj = datalist.get(i);
            for (int j = 0;j<fields.length;j++){
                //������
                Cell cell = row.createCell(j);
//                cell.setCellType(CellType.STRING);
                //
                try {
                    Object value = methodList.get(j).invoke(targetObj, new Object[]{});
                    cell.setCellValue(transCellType(value));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        response.setContentType("application/octet-stream");
        //Ĭ��Excel����
        response.setHeader("Content-Disposition", "attachment;fileName="+"test.xls");
        try {
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

//            response.flushBuffer();
//            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String transCellType(Object value){
        String str = null;
        if (value instanceof Date){
            Date date = (Date) value;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str = sdf.format(date);
        }else{
            str = String.valueOf(value);
            if (str == "null"){
                str = "";
            }
        }

        return str;
    }

}

