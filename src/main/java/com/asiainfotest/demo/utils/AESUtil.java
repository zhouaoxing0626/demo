package com.asiainfotest.demo.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;

public class AESUtil {
    //算法
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
    private static String AES_KEY = "3944232426862628";


    public static final String AES_KEY2 = "6686686686686686";

    /**
     * aes解密
     * @param encrypt   内容
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String encrypt) {
        try {
            return aesDecrypt(encrypt, AES_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * aes加密
     * @param content
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String content) {
        try {
            return aesEncrypt(content, AES_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes){
        return Base64.encodeBase64String(bytes);
    }

    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception{
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }


    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * AES加密为base 64 code
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }


    /**
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }


    /**
     * AES加密为base 64 code
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncryptCBC(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytesCBC(content, encryptKey));
    }

    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytesCBC(String content, String encryptKey) throws Exception {
        byte[] raw = encryptKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
        //CBC模式需要配置偏移量，设置一个向量，达到密码唯一性，增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec("wNSOYIB1k1DjY5lA".getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
        return encrypted;
    }

    /**
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecryptCBC(String encryptStr, String decryptKey) throws Exception {
        return aesDecryptByBytesCBC(base64Decode(encryptStr), decryptKey);
    }

    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytesCBC(byte[] encryptBytes, String decryptKey) throws Exception {
        byte[] raw = decryptKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //CBC模式需要配置偏移量，设置这个后，不会出来同一个明文加密为同一个密文的问题，达到密文唯一性
        IvParameterSpec iv = new IvParameterSpec("wNSOYIB1k1DjY5lA".getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(encryptBytes);
        String originalString = new String(original,"utf-8");
        return originalString;
    }




    /**
     * 测试
     * 前端js将参数加密提交到后台如何解密
     * 首先获取服务端的私钥:将客户端的公钥加密后获得的结果
     * 通过服务端的私钥和客户端传递的加密字符串即可实现解密
     */
    public static void main(String[] args) throws Exception {
        String content = "13060807628";
        System.out.println("加密前：" + content);
        System.out.println("加密密钥和解密密钥：" + AES_KEY);
        String encrypt = aesEncrypt(content, AES_KEY);
        System.out.println("加密后：" + encrypt);
        String decrypt = aesDecrypt(encrypt, AES_KEY);
        System.out.println("解密后：" + decrypt);
        //js加密后的字符串: lkqsgKHH7OkhIa0tISMtuQ==
        String jsData = aesDecrypt("lkqsgKHH7OkhIa0tISMtuQ==", AES_KEY);
        System.out.println("前端数据解密后的值:" + jsData);

    }



    public static String toBase64(byte[] array) {
        return DatatypeConverter.printBase64Binary(array);
    }

    public static byte[] fromBase64(String s) {
        return DatatypeConverter.parseBase64Binary(s);
    }

    public static String encrypt(String defaultKey,String str) throws Exception {
        SecretKey secretKey= new SecretKeySpec(defaultKey.getBytes("UTF-8"), "AES");
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] src = str.getBytes("UTF-8");
        byte[] cipherByte = c.doFinal(src);
        return toBase64(cipherByte);
    }

    public static String decrypt(String defaultKey,String str) throws Exception {
        SecretKey secretKey= new SecretKeySpec(defaultKey.getBytes("UTF-8"), "AES");
        byte[] buff = fromBase64(str);
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] cipherByte = c.doFinal(buff);
        return new String(cipherByte, "UTF-8");
    }



}
