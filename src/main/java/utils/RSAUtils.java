package utils;

/**
 * Created by Dao on 2017/5/31.
 */

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPublicKey;

/*
* 先初始化Key,整个类使用一对静态的公钥私钥
* */

public class RSAUtils {
    //KeyPair is a simple holder for a key pair.
    private static final KeyPair keyPair = initKey();
    /**
     * 初始化方法，产生key pair，提供provider和random
     * @return KeyPair instance
     */
    private static KeyPair initKey() {

        try {
            //添加provider
            Provider provider = new org.bouncycastle.jce.provider.BouncyCastleProvider();
            Security.addProvider(provider);
            //产生用于安全加密的随机数
            SecureRandom random = new SecureRandom();

            //KeyPairGenerator is used to generate pairs of public and private keys,
            //which is constructed using the getInstance() factory methods.
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", provider);
            generator.initialize(1024, random);
            return generator.generateKeyPair();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 产生public key
     * @return public key字符串
     */
    public static String generateBase64PublicKey() {
        PublicKey publicKey = (RSAPublicKey)keyPair.getPublic();

        //encodeBase64(): Encodes binary data using the base64
        //algorithm but does not chunk the output.
        //getEncoded():返回key的原始编码形式
        return new String(Base64.encodeBase64(publicKey.getEncoded()));
    }
    /**
     * 解密BASE64并RSA解密数据
     * @param string 需要解密的字符串
     * @return  破解之后的字符串
     */
    public static String decryptBase64(String string) {
        //decodeBase64():将Base64数据解码为"八位字节”数据
        return new String(decrypt(Base64.decodeBase64(string.getBytes())));
    }
    /**
     * 只解密RSA数据
     * @param string 需要解密的字符串
     * @return  破解之后的字符串
     */
    public static String decrypt(String string) {
        return new String(decrypt(string.getBytes()));
    }
    private static byte[] decrypt(byte[] byteArray) {
        try {
            Provider provider = new org.bouncycastle.jce.provider.BouncyCastleProvider();
            Security.addProvider(provider);
            //Cipher: 提供加密和解密功能的实例
            //transformation: "algorithm/mode/padding"
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", provider);
            PrivateKey privateKey = keyPair.getPrivate();
            //初始化
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            //doFinal(): 加密或者解密数据
            byte[] plainText = cipher.doFinal(byteArray);
            return plainText;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 只加密RSA数据
     * @param string 需要加密的字符串
     * @return  破解之后的String
     */
    public static String encrypt(String string) {
        return new String(encrypt(string.getBytes()));
    }
    /**
     * 只加密RSA数据
     * @param  byteArray 需要加密的byte[]
     * @return  破解之后的byte[]
     */
    private static byte[] encrypt(byte[] byteArray) {
        try {
            Provider provider = new org.bouncycastle.jce.provider.BouncyCastleProvider();
            Security.addProvider(provider);
            //Cipher: 提供加密和解密功能的实例
            //transformation: "algorithm/mode/padding"
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", provider);
            PublicKey publicKey = keyPair.getPublic();
            //初始化
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            //doFinal(): 加密或者解密数据
            byte[] plainText = cipher.doFinal(byteArray);
            return plainText;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }




    public static void main(String[] args) {
        String msg = "RSA加密解密案例";
        System.out.println("【加密前】：" + msg);

        //加密
        byte[] secretArr = RSAUtils.encrypt(msg.getBytes());
        System.out.println("【加密后】：" + new String(secretArr));

        //解密
        byte[] myMsgArr = RSAUtils.decrypt(secretArr);
        System.out.println("【解密后】：" + new String(myMsgArr));
    }

}