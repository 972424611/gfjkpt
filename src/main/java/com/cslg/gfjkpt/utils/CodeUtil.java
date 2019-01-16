package com.cslg.gfjkpt.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author twilight
 */
public class CodeUtil {

    private static final String HEX_STRING = "0123456789ABCDEF";

    private static final Logger logger = LoggerFactory.getLogger(CodeUtil.class);

    /**
     * 字符串转成十六进制byte[] 类型数组
     */
    public static byte[] hex2byte(String hex) {
        String hex1 = hex.replace(" ", "");
        char[] hex2char = hex1.toCharArray();
        byte[] bytes = new byte[hex1.length() / 2];
        byte temp;
        for(int p = 0; p < bytes.length; p++) {
            temp = (byte) (HEX_STRING.indexOf(hex2char[2 * p]) * 16);
            temp += HEX_STRING.indexOf(hex2char[2 * p + 1]);
            bytes[p] = (byte) (temp & 0xff);
        }
        return bytes;
    }

    /**
     *  将字符串编码成16进制数字,适用于所有字符（包括中文）
     */
    public static String encode(byte[] bytes) {
        try {
            StringBuilder sb = new StringBuilder(bytes.length * 2);
            for(byte k : bytes) {
                sb.append(HEX_STRING.charAt((k & 0xf0) >> 4));
                sb.append(HEX_STRING.charAt(k & 0x0f));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Thread.currentThread().getName(), "encode方法异常", e);
            return null;
        }
    }

    public static void main(String[] args) {
        String s="0F";
        System.out.println(encode(hex2byte(s)));
    }
}
