package com.jt.lux.util;


import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @PROJECT_NAME: water_chivalry
 * @AUTHOR: Hanson-Hsc
 * @DATE: 2020-07-27 09:08
 * @DESCRIPTION: ͼƬѹ������
 * @VERSION:
 */
public class PicUtils {

    /**
     * ѹ��ͼƬ��ͨ������ͼƬ������
     * @explain ѹ��ͼƬ,ͨ��ѹ��ͼƬ����������ԭͼ��С
     * @param quality
     *       ͼƬ������0-1��
     * @return byte[]
     *      ѹ�����ͼƬ��jpg��
     * @throws
     */
    public static byte[] compressPicByQuality(byte[] imgByte, float quality) {
        byte[] imgBytes = null;
        try {
            ByteArrayInputStream byteInput = new ByteArrayInputStream(imgByte);
            BufferedImage image = ImageIO.read(byteInput);

            // ���ͼƬ�գ����ؿ�
            if (image == null) {
                return null;
            }
            // �õ�ָ��FormatͼƬ��writer����������
            Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");
            // �õ�writer
            ImageWriter writer = (ImageWriter) iter.next();
            // �õ�ָ��writer�������������(ImageWriteParam )
            ImageWriteParam iwp = writer.getDefaultWriteParam();
            // ���ÿɷ�ѹ��
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            // ����ѹ����������
            iwp.setCompressionQuality(quality);

            iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);

            ColorModel colorModel = ColorModel.getRGBdefault();
            // ָ��ѹ��ʱʹ�õ�ɫ��ģʽ
            iwp.setDestinationType(
                    new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));

            // ��ʼ���ͼƬ��д��byte[]
            // ȡ���ڴ������
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            IIOImage iIamge = new IIOImage(image, null, null);

            // �˴���ΪImageWriter����������write��Ϣ��outputҪ�������ImageOutput
            // ͨ��ImageIo�еľ�̬�������õ�byteArrayOutputStream��ImageOutput
            writer.setOutput(ImageIO.createImageOutputStream(byteArrayOutputStream));
            writer.write(null, iIamge, iwp);
            imgBytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            System.out.println("write errro");
            e.printStackTrace();
        }
        return imgBytes;
    }

}