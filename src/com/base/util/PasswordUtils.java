package com.base.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.AlgorithmConstraints;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Random;

public class PasswordUtils {

    /**
     * 支持以下任意一种算法
     * PBEWithMD5AndDES
     * PBEWithMD5AndTripleDES
     * PBEWithSHA1AndDESede
     * PBEWithSHA1AndRC2_40
     */

    private static final String ALGORITHM = "PBEWITHMD5andDES"; //加密算法

    /**
     * 获取盐
     * @return
     */
    public static byte[] getSalt(){
        byte[] salt = new byte[8];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }

    /**
     * 转换密钥
     * @param password
     * @return
     * @throws Exception
     */
    public static Key toKey(String password) throws Exception{
        PBEKeySpec keySpec= new PBEKeySpec(password.toCharArray());
        SecretKeyFactory keyFactory  = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(keySpec);
        return secretKey;

    }

    /**
     * 加密
     * @param data   加密的数据
     * @param password   密码
     * @param salt 盐
     * @return
     * @throws Exception
     */
    public static String encrypt(String data,String password,byte[] salt) throws Exception{
        Key key = toKey(password);
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt,1000);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,key,parameterSpec);
        return bytesToHexString(cipher.doFinal(data.getBytes()));
    }

    /**
     * 解密
     * @param data  数据
     * @param password 密码
     * @param salt 盐
     * @return
     * @throws Exception
     */

    public static  String decrypt(String data,String password,byte[] salt) throws Exception{
        Key key = toKey(password);
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt,1000);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,key,parameterSpec);
        return new String(cipher.doFinal(hexStringToBytes(data)));
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param src
     *            字节数组
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 将十六进制字符串转换为字节数组
     *
     * @param hexString
     *            十六进制字符串
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


    public static void main(String[] args) throws Exception {
        String password = "123456";
        byte[] salt = getSalt();

        String data1 = encrypt(password,password,salt);
        System.out.println(data1);
        System.out.println(decrypt(data1,password,salt));
    }
}
