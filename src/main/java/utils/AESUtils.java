package utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Dao on 2017/5/31.
 */
public class AESUtils {

    private static final String password = "t@IV(9mR?Pu1zmUi";
    /**加密
     * @param content  String 待加密
     * @return
     */
    public static String encryptHex(String content){
        return ParseSystemUtil.parseByte2HexStr(encrypt(content));
    }
    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @return
     */
    public static byte[] encrypt(String content) {
        try {/*
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(256, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();*/
            SecretKeySpec key = new SecretKeySpec(/*enCodeFormat*/password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**解密
     * @param content  String 待解密
     * @return
     */
    public static String decryptHex(String content){
        return new String(decrypt(ParseSystemUtil.parseHexStr2Byte(content)));
    }
    /**解密
     * @param content  待解密内容
     * @return
     */
    public static byte[] decrypt(byte[] content) {
        try {
            /*KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(256, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();*/
            SecretKeySpec key = new SecretKeySpec(/*enCodeFormat*/password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

public static void main(String[] args){
    String content = "jasfopnq 21 j401ujc42k1n0c912j4n12v";
    AESUtils tmp1= new AESUtils();
    AESUtils tmp2= new AESUtils();
//加密
System.out.println("加密前：" + content);
    String encryptResultStr = tmp1.encryptHex(content);
System.out.println("加密后：" + encryptResultStr);
    //解密
    String decryptResultStr = tmp2.decryptHex(encryptResultStr);
System.out.println("解密后：" + decryptResultStr);
}

}
