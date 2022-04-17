package com.jt.lux.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Random;

/**
 * ������ OSS������
 *
 * @author lux
 * @version 1.0
 */
@Component
public class OSSClientUtil {
    //������yaml����
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.maxFileSize}")
    private Integer maxFileSize;
    // �ļ��洢Ŀ¼
    private String filedir = "";

    /**
     * �ϴ�ͼƬ
     *
     * @param url
     * @throws Exception
     */
    public String uploadImg2Oss(String url) throws Exception {
        File fileOnServer = new File(url);
        FileInputStream fin;
        String serverUrl = null;
        try {
            fin = new FileInputStream(fileOnServer);
            String[] split = url.split("/");
            serverUrl = this.uploadFile2OSS(fin, split[split.length - 1]);
        } catch (FileNotFoundException e) {
            throw new Exception("ͼƬ�ϴ�ʧ��");
        }
        return serverUrl;
    }

    public String uploadMultiFile2Oss(MultipartFile file) throws Exception {
        if (file.getSize() > maxFileSize * 1024 * 1024) {
            throw new Exception("�ϴ�ͼƬ�������ƣ�");
        }
        String serverUrl = null;
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis()+"";
        try {
            InputStream inputStream = file.getInputStream();
            serverUrl = this.uploadFile2OSS(inputStream, name+ substring);
            return serverUrl;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ͼƬ�ϴ�ʧ��");
        }
    }

    public String uploadFile2Oss(String filePath) throws Exception {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            String serverUrl = null;
            String originalFilename = file.getName();
            String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            Random random = new Random();
            String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
                serverUrl = this.uploadFile2OSS(inputStream, name);
                return serverUrl;
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("ͼƬ�ϴ�ʧ��");
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Exception e) {

                }
            }
        }
        return null;
    }

    /**
     * ���ͼƬ·��
     *
     * @param fileUrl
     * @return
     */
    private String getImgUrl(String fileUrl, OSSClient ossClient) {
        System.out.println(fileUrl);
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split("/");
            return this.getUrl(this.filedir + split[split.length - 1], ossClient);
        }
        return null;
    }

    /**
     * �ϴ���OSS������ ���ͬ���ļ��Ḳ�Ƿ������ϵ�
     *
     * @param instream
     *            �ļ���
     * @param fileName
     *            �ļ����� ������׺��
     * @return ������"" ,ΨһMD5����ǩ��
     */
    public String uploadFile2OSS(InputStream instream, String fileName) {
        String url = "";
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // �����ϴ�Object��Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            String name = fileName.substring(0,fileName.lastIndexOf("."));
            // �ϴ��ļ�
            PutObjectResult putResult = ossClient.putObject(bucketName, filedir + name, instream, objectMetadata);
            if (putResult != null) {
                url =  name;
            }
        } catch (IOException e) {

        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            ossClient.shutdown();
        }
        return url;
    }

    /**
     * Description: �ж�OSS�����ļ��ϴ�ʱ�ļ���contentType
     *
     * @param
     *
     * @return String
     */
    public static String getcontentType(String filenameExtension) {
        if ("bmp".equalsIgnoreCase(filenameExtension)) {
            return "image/bmp";
        }
        if ("gif".equalsIgnoreCase(filenameExtension)) {
            return "image/gif";
        }
        if ("jpeg".equalsIgnoreCase(filenameExtension) || "jpg".equalsIgnoreCase(filenameExtension)
                || "png".equalsIgnoreCase(filenameExtension)) {
            return "image/jpeg";
        }
        if ("html".equalsIgnoreCase(filenameExtension)) {
            return "text/html";
        }
        if ("txt".equalsIgnoreCase(filenameExtension)) {
            return "text/plain";
        }
        if ("vsd".equalsIgnoreCase(filenameExtension)) {
            return "application/vnd.visio";
        }
        if ("pptx".equalsIgnoreCase(filenameExtension) || "ppt".equalsIgnoreCase(filenameExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if ("docx".equalsIgnoreCase(filenameExtension) || "doc".equalsIgnoreCase(filenameExtension)) {
            return "application/msword";
        }
        if ("xml".equalsIgnoreCase(filenameExtension)) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    /**
     * ���url����
     *
     * @param key
     * @return
     */
    private String getUrl(String key, OSSClient ossClient) {
        boolean flag = false;
        if (ossClient == null) {
            flag = true;
            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        }
        try {
            // ����URL����ʱ��Ϊ1000�� 3600l* 1000*24*365*1000
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 1000);
            // ����URL
            URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
            if (url != null) {
                return url.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (true) {
                ossClient.shutdown();
            }
        }
        return null;
    }

    /**
     * ���url����
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        OSSObject object = null;
        try {
            object = ossClient.getObject(bucketName, key);
            if (object != null) {
                object.close();
            }
        } catch (OSSException e) {
            // OSS�ڲ��Ҳ���ĳ����ʱ�����׳�ErrorCodeΪ��NoSuchKey����OSSException�������Ƿ���null
            if (e.getErrorCode().contains("NoSuchKey")) {
                System.out.println("�Ҳ���OSS�ļ���" + key);
            }else {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // ����URL����ʱ��Ϊ1000�� 3600l* 1000*24*365*1000
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 1000);
            // ����URL
            URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
            if (url != null) {
                return url.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (true) {
                ossClient.shutdown();
            }
        }
        return null;
    }

    /**
     * ɾ���ļ�
     */
    public void delFileOSS(String fileName){
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        if(fileName != null && fileName.length() > 0) {
            ossClient.deleteObject(bucketName, fileName);
        }
    }

    /**
     * �ļ��Ƿ����
     * @param key
     * @return
     */
    public boolean doesExist(String key){
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        boolean exist = ossClient.doesObjectExist(bucketName, key);
        return exist;
    }

    /**
     * ��ȡ�ļ�
     * @param key
     * @return
     * @throws IOException
     */
    public byte[] getFile(String key) throws IOException {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        OSSObject object = null;
        try {
            object = ossClient.getObject(bucketName, key);
            if (object != null) {
                object.close();
            }
        } catch (OSSException e) {
            // OSS�ڲ��Ҳ���ĳ����ʱ�����׳�ErrorCodeΪ��NoSuchKey����OSSException�������Ƿ���null
            if (e.getErrorCode().contains("NoSuchKey")) {
                System.out.println("�Ҳ���OSS�ļ���" + key);
            }else {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = object.getObjectContent();
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inputStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

}