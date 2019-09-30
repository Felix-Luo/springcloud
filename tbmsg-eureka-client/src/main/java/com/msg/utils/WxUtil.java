package com.msg.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;

/**
* @Description:    微信工具类
* @Author:         luoyhong
* @CreateDate:     2019/7/9 16:46
* @UpdateUser:     luoyhong
* @UpdateDate:     2019/7/9 16:46
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class WxUtil {

    //public static final String appid = "wxf7921050c476286d";
    //public static final String secret = "1b43b589d4204649e7473509fd650da4";
    public static final String AES = "AES";
    public static final String AES_CBC_PADDING = "AES/CBC/PKCS7Padding";

    /**
     * @function    方法功能描述
     * 微信 数据解密<br/>
     * 对称解密使用的算法为 AES-128-CBC，数据采用PKCS#7填充<br/>
     **对称解密的目标密文:encrypted=Base64_Decode(encryptData)<br/>
     * 对称解密秘钥:key = Base64_Decode(session_key),aeskey是16字节<br/>
     * 对称解密算法初始向量:iv = Base64_Decode(iv),同样是16字节<br/>
     * @author      luoyhong
     * @param encrypted 目标密文
    * @param session_key 会话ID
    * @param iv 加密算法的初始向量
     * @return      java.lang.String
     * @exception
     * @date        2019/7/9 17:08
     */
    public static String wxDecrypt(String encrypted, String session_key, String iv) {

        String result = null;
        byte[] encrypted64 = Base64.decodeBase64(encrypted);
        byte[] key64 = Base64.decodeBase64(session_key);
        byte[] iv64 = Base64.decodeBase64(iv);
        try {
            init();
            result = new String(decrypt(encrypted64, key64, generateIV(iv64)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @function    初始化密钥
     * @author      luoyhong
     * @param
     * @return      void
     * @exception
     * @date        2019/7/9 17:10
     */
    public static void init() throws Exception {

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        KeyGenerator.getInstance(AES).init(128);
    }

    /**
     * @function    生成iv
     * @author      luoyhong
     * @param iv
     * @return      java.security.AlgorithmParameters
     * @exception
     * @date        2019/7/9 17:10
     */
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception {

        // iv 为一个 16 字节的数组，这里采用和 iOS 端一样的构造方法，数据全为0
        // Arrays.fill(iv, (byte) 0x00);
        AlgorithmParameters params = AlgorithmParameters.getInstance(AES);
        params.init(new IvParameterSpec(iv));
        return params;
    }

    /**
     * @function    生成解密
     * @author      luoyhong
     * @param encryptedData
    * @param keyBytes
    * @param iv
     * @return      byte[]
     * @exception
     * @date        2019/7/9 17:11
     */
    public static byte[] decrypt(byte[] encryptedData, byte[] keyBytes, AlgorithmParameters iv)
            throws Exception {

        Key key = new SecretKeySpec(keyBytes, AES);
        Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
        // 设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return cipher.doFinal(encryptedData);
    }
    public static void main(String[] args){
        String encrypted = "F9oMxgDwEBL8pZnxbnBGjntWmD+PJDYwo5i79+lEjoDxsE05kBvDe0LRKw+u1N4gpQp9H9SLuxyuEbOCipoRFZ0bvT0gXbJUG6c6sxuS4cgjjqk4MDO74WNBQgPVmVhHuulIcOQnGciUw2mt2aHmLLnYQ8UbwtiWF4tfYSnxiUwHvBTLOWxcJVuAWfCwb/sXIeXl4AG8o7Q5c+aviEjWrw==";
        String session_key = "nJBgxIQQ+agzo+wOpymYrg==";
        String iv = "B4+QUi6cWKrbS3Yv657+Uw==";

        String result = wxDecrypt(encrypted, session_key, iv);
        JSONObject json = JSONObject.parseObject(result);
        System.out.println(json.toJSONString());
    }
}
