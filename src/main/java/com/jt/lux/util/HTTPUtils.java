package com.jt.lux.util;

import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HTTPUtils {
    private static final int TIMEOUT = 45000;
    public static final String ENCODING = "UTF-8";

    /**
     * ����HTTP����
     *
     * @param url
     *            ��ַ
     * @param method
     *            ����
     * @param headerParameters
     *            ͷ��Ϣ
     * @param body
     *            ��������
     * @return
     * @throws Exception
     */
    private static HttpURLConnection createConnection(String url,
                                                      String method, Map<String, String> headerParameters, String body)
            throws Exception {
        URL Url = new URL(url);
        trustAllHttpsCertificates();
        HttpURLConnection httpConnection = (HttpURLConnection) Url
                .openConnection();
        // ��������ʱ��
        httpConnection.setConnectTimeout(TIMEOUT);
        // ���� header
        if (headerParameters != null) {
            Iterator<String> iteratorHeader = headerParameters.keySet()
                    .iterator();
            while (iteratorHeader.hasNext()) {
                String key = iteratorHeader.next();
                httpConnection.setRequestProperty(key,
                        headerParameters.get(key));
            }
        }
        httpConnection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded;charset=" + ENCODING);

        // �������󷽷�
        httpConnection.setRequestMethod(method);
        httpConnection.setDoOutput(true);
        httpConnection.setDoInput(true);
        // дquery������
        if (!(body == null || body.trim().equals(""))) {
            OutputStream writer = httpConnection.getOutputStream();
            try {
                writer.write(body.getBytes(ENCODING));
            } finally {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }
        }

        // ������
        int responseCode = httpConnection.getResponseCode();
        if (responseCode != 200) {
            throw new Exception(responseCode
                    + ":"
                    + inputStream2String(httpConnection.getErrorStream(),
                    ENCODING));
        }

        return httpConnection;
    }

    /**
     * POST����
     * @param address �����ַ
     * @param headerParameters ����
     * @param body
     * @return
     * @throws Exception
     */
    public static String post(String address,
                              Map<String, String> headerParameters, String body) throws Exception {

        return proxyHttpRequest(address, "POST", null,
                getRequestBody(headerParameters));
    }

    /**
     * GET����
     * @param address
     * @param headerParameters
     * @param body
     * @return
     * @throws Exception
     */
    public static String get(String address,
                             Map<String, String> headerParameters, String body) throws Exception {

        return proxyHttpRequest(address + "?"
                + getRequestBody(headerParameters), "GET", null, null);
    }


    public static byte[] readInputStreamToByte(InputStream in) throws Exception {
        FileOutputStream out = null;
        ByteArrayOutputStream output = null;
        byte[] byteFile = null;

        try {
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            byteFile = output.toByteArray();
        } catch (Exception e) {
            throw e;
        } finally {
            if (output != null) {
                output.close();
            }
            if (out != null) {
                out.close();
            }
        }

        return byteFile;
    }

    /**
     * HTTP����
     *
     * @param address
     *            ��ַ
     * @param method
     *            ����
     * @param headerParameters
     *            ͷ��Ϣ
     * @param body
     *            ��������
     * @return
     * @throws Exception
     */
    public static String proxyHttpRequest(String address, String method,
                                          Map<String, String> headerParameters, String body) throws Exception {
        String result = null;
        HttpURLConnection httpConnection = null;

        try {
            httpConnection = createConnection(address, method,
                    headerParameters, body);

            String encoding = "UTF-8";
            if (httpConnection.getContentType() != null
                    && httpConnection.getContentType().indexOf("charset=") >= 0) {
                encoding = httpConnection.getContentType()
                        .substring(
                                httpConnection.getContentType().indexOf(
                                        "charset=") + 8);
            }
            result = inputStream2String(httpConnection.getInputStream(),
                    encoding);
            // logger.info("HTTPproxy response: {},{}", address,
            // result.toString());

        } catch (Exception e) {
            // logger.info("HTTPproxy error: {}", e.getMessage());
            throw e;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        return result;
    }

    /**
     * ��������Ϊ body
     * @param params
     * @return
     */
    public static String getRequestBody(Map<String, String> params) {
        return getRequestBody(params, true);
    }

    /**
     * ��������Ϊ body
     * @param params
     * @return
     */
    public static String getRequestBody(Map<String, String> params,
                                        boolean urlEncode) {
        StringBuilder body = new StringBuilder();

        Iterator<String> iteratorHeader = params.keySet().iterator();
        while (iteratorHeader.hasNext()) {
            String key = iteratorHeader.next();
            String value = params.get(key);

            if (urlEncode) {
                try {
                    body.append(key + "=" + URLEncoder.encode(value, ENCODING)
                            + "&");
                } catch (UnsupportedEncodingException e) {
                    // e.printStackTrace();
                }
            } else {
                body.append(key + "=" + value + "&");
            }
        }

        if (body.length() == 0) {
            return "";
        }
        return body.substring(0, body.length() - 1);
    }

    /**
     * ��ȡinputStream �� string
     * @param input
     * @param encoding
     * @return
     * @throws IOException
     */
    private static String inputStream2String(InputStream input, String encoding)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input,
                encoding));
        StringBuilder result = new StringBuilder();
        String temp = null;
        while ((temp = reader.readLine()) != null) {
            result.append(temp);
        }

        return result.toString();

    }


    /**
     * ���� https ����
     * @throws Exception
     */
    private static void trustAllHttpsCertificates() throws Exception {
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String str, SSLSession session) {
                return true;
            }
        });
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }


    //���� https ����֤��
    static class miTM implements javax.net.ssl.TrustManager,javax.net.ssl.X509TrustManager {

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }


    }

    //====================================================================
    //============================= ���Ե���   ============================
    //====================================================================
    public static void main(String[] args) {

        try {

            //�����ַ(���������ʹ���Ա��ṩ���ֻ�������Ϣ��ѯ�Ľӿ�)
            String address = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm";

            //�������
            Map<String, String> params = new HashMap<String, String>();
            params.put("tel", "13777777777");//���Ǹýӿ���Ҫ�Ĳ���

            // ���� get ����
            String res = get(address, params, null);
            System.out.println(res);//��ӡ���ز���

            res = res.substring(res.indexOf("{"));//��ȡ
            JSONObject result = JSONObject.parseObject(res);//תJSON

            System.out.println(result.toString());//��ӡ

        } catch (Exception e) {
            // TODO �쳣
            e.printStackTrace();
        }

    }
}