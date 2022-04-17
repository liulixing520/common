package com.jt.lux.util;

import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSASHA1Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String algorithm = "RSA";

        try {
            getKey(algorithm);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String str = "erter%$%$54是的发送到sdfsdf";
        String signstr = sign(str,privateKey);
        System.out.println(signstr);
        System.out.println(verify(signstr,str,publicKey));

    }

    private static PublicKey publicKey;
    private static PrivateKey privateKey;
    private static String merName = "CGBBank";

    public static void getKey(String algorithm) throws NoSuchAlgorithmException, InvalidKeySpecException{

        byte[] pvk = new byte[2048];
        byte[] pu = new byte[2048];
        try {
            pvk = read("D:/UserData/yangyongbin/Downloads/gfs.pvk");
            pu = read("D:/UserData/yangyongbin/Downloads/gfs.puk");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        X509EncodedKeySpec pubX09 = new X509EncodedKeySpec(Base64.decodeBase64(pu));
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(pvk));

        KeyFactory key = KeyFactory.getInstance(algorithm);
        publicKey = key.generatePublic(pubX09);
        privateKey = key.generatePrivate(priPKCS8);

    }



    public static final byte[] read(String filePath) throws IOException {
        byte[] abyte0;
        if (filePath == null)
            throw new IllegalArgumentException("Illegal Argument: filePath");

        FileInputStream crls = null;
        try
        {
            int rLength;
            crls = new FileInputStream(filePath);
            byte[] out = new byte[crls.available()];
            byte[] buffer = new byte[65536];

            for (int offset = 0; (rLength = crls.read(buffer, 0, buffer.length)) != -1; offset += rLength)
                System.arraycopy(buffer, 0, out, offset, rLength);

            abyte0 = out;
        } catch (IOException e) {
            throw e;
        } finally {
            if (crls != null)
                try {
                    crls.close();
                }
                catch (Exception localException1) {
                }
        }
        return abyte0;
    }

    public static String sign(String unsigned,PrivateKey prk) {
        String signed = null;
        try {
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(prk);
            signature.update(unsigned.getBytes("GBK"));
            signed = new String(Base64.encodeBase64(signature.sign(), false), "GBK");
        } catch (Exception e) {
        }
        return signed;
    }

    /**
     * @see com.alipay.bcm.biz.certifier.Certifier#verify(String, String)
     */
    public static boolean verify(String signed, String unsigned,PublicKey puk) {
        try {
            Signature sig = Signature.getInstance("SHA1withRSA");
            sig.initVerify(puk);
            sig.update(unsigned.getBytes("GBK"));
            return sig.verify(Base64.decodeBase64(signed.getBytes()));
        } catch (Exception e) {
            return false;
        }
    }

}
