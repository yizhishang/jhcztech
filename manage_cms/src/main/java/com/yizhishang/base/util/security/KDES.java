package com.yizhishang.base.util.security;

import com.sun.crypto.provider.SunJCE;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Security;

/**
 * 描述: DES加解密类
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-23
 * 创建时间: 下午4:35:16
 */
public final class KDES
{

    public static int DES = 1;

    public static int DESEDE = 2;

    public static int BLOWFISH = 3;

    private final Cipher p_Cipher;

    BASE64Decoder decoder;

    BASE64Encoder encoder;

    private SecretKey p_Key;

    private String p_Algorithm;

    public KDES(int algorithm) throws Exception
    {
        decoder = new BASE64Decoder();
        encoder = new BASE64Encoder();
        selectAlgorithm(algorithm);
        Security.addProvider(new SunJCE());
        p_Cipher = Cipher.getInstance(p_Algorithm);
    }

    public KDES() throws Exception
    {
        decoder = new BASE64Decoder();
        encoder = new BASE64Encoder();
        selectAlgorithm(1);
        Security.addProvider(new SunJCE());
        p_Cipher = Cipher.getInstance(p_Algorithm);
    }

    private void selectAlgorithm(int al)
    {
        switch (al) {
            case 1: // '\001'
            default:
                p_Algorithm = "DES";
                break;

            case 2: // '\002'
                p_Algorithm = "DESede";
                break;

            case 3: // '\003'
                p_Algorithm = "Blowfish";
                break;
        }
    }

    public void setKey(String key)
    {
        if (key == null || key.equals(""))
            key = "desdesde";
        int n = key.length() % 8;
        if (n != 0) {
            for (int i = 8; i > n; i--)
                key = key + "0";

        }
        p_Key = new SecretKeySpec(key.getBytes(), p_Algorithm);
    }

    private SecretKey checkKey()
    {
        try {
            if (p_Key == null) {
                KeyGenerator keygen = KeyGenerator.getInstance(p_Algorithm);
                p_Key = keygen.generateKey();
            }
        } catch (Exception exception) {
        }
        return p_Key;
    }

    public String encode(String data) throws Exception
    {
        p_Cipher.init(1, checkKey());
        return new String(byte2hex(p_Cipher.doFinal(data.getBytes("GBK"))));
    }

    public String decode(String encdata, String enckey) throws Exception
    {
        setKey(enckey);
        p_Cipher.init(2, p_Key);
        return new String(p_Cipher.doFinal(hex2byte(encdata)), "GBK");
    }

    public String byte2hex(byte b[])
    {
        return encoder.encode(b);
    }

    public byte[] hex2byte(String hex) throws IOException
    {
        return decoder.decodeBuffer(hex);
    }
}
