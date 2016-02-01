package com.utils;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Description:
 * @author zhaozhineng
 * @date 2016-1-30 上午10:54:20
 */
public class MD5 {

    private static final String CHARSET = "UTF-8";

    /**
     * 对字符串进行MD5加密
     * 
     * @param text
     * @return
     */
    public static String encode(String text) {
        return DigestUtils.md5Hex(getContentBytes(text, CHARSET));
    }

    /**
     * 验证对字符串MD5加密后是否和目标字符串一致
     * 
     * @param sourceText 待加密
     * @param targetText
     * @return
     */
    public static boolean verify(String sourceText, String targetText) {
        String myPassword = encode(sourceText);
        if (myPassword.equals(targetText)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    public static void main(String[] args) {
        System.out.println(encode("123456"));
    }
}
