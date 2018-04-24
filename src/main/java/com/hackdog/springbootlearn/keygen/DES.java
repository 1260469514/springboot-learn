package com.hackdog.springbootlearn.keygen;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DES {

    /*
     * 不要写成：Cipher cipher = Cipher.getInstance("DES");
     * 或：Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
     * 原因是Cipher cipher = Cipher.getInstance("DES");
     * 与Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
     * 等同，填充方式错误，加密的时候会得到16长度的字节数组。
     * 应该写成
     * Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
     */


    private static final String KEY = "hongApp=";

    private static byte[] iv = {12, 22, 32, 43, 51, 64, 57, 98};

    /**
     * 指定密钥加密
     *
     * @param encryptString 原文
     * @param encryptKey    密钥
     * @return 密文
     * @throws Exception
     */
    public static String encryptDES(String encryptString, String encryptKey)
            throws Exception {

        IvParameterSpec zeroIv = new IvParameterSpec(iv);

        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);

        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());

        return Base64DES.encode(encryptedData);
    }

    /**
     * 指定密钥进行解密
     *
     * @param decryptString 密文
     * @param decryptKey    密钥
     * @return 原文
     * @throws Exception
     */
    public static String decryptDES(String decryptString, String decryptKey)
            throws Exception {

        byte[] byteMi = Base64DES.decode(decryptString);

        IvParameterSpec zeroIv = new IvParameterSpec(iv);

        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);

        byte decryptedData[] = cipher.doFinal(byteMi);

        return new String(decryptedData);

    }

    /**
     * 默认密钥加密
     *
     * @param encryptString 原文
     * @return 密文
     * @throws Exception
     */
    public static String encryptDES(String encryptString)
            throws Exception {

        IvParameterSpec zeroIv = new IvParameterSpec(iv);

        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), "DES");

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);

        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());

        return Base64DES.encode(encryptedData);
    }

    /**
     * 默认密钥解密
     *
     * @param decryptString 密文
     * @return 解密得到的原文
     * @throws Exception
     */
    public static String decryptDES(String decryptString)
            throws Exception {

        byte[] byteMi = Base64DES.decode(decryptString);

        IvParameterSpec zeroIv = new IvParameterSpec(iv);

        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), "DES");

        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);

        byte decryptedData[] = cipher.doFinal(byteMi);

        return new String(decryptedData, "UTF-8");

    }

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//		String plaintext = "server=app/user/getUserInfo&userId=1";
//		String pasd = "r/HlScyKG+hC7XpIj1aXQKISre87EafvPD8YPeaZVtlOe/gBIN4d+icDZedj We3K47JxORFU7b2It4nSloz9N8UO4zr+fIqLCmdwSKo9z+4=";
//		 try {
//			 //ciphertext = DES.encryptDES(plaintext);
//			 System.out.println("明文：" + plaintext);
//			 System.out.println("密钥：" + KEY);
//			 System.out.println("密文：" + DES.encryptDES(plaintext));
//			 String str = new String(DES.decryptDES(pasd).getBytes(),"utf-8");
//			 System.out.println("解密后：" +str);
//
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

    }
}
