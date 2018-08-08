//package com.json.test;
//
//public class OrderTest {
//
//    public static byte[] hexString2Bytes(String src) {
//        if (null == src || 0 == src.length()) {
//            return null;
//        }
//        byte[] ret = new byte[src.length() / 2];
//        byte[] tmp = src.getBytes();
//        for (int i = 0; i < (tmp.length / 2); i++) {
//            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
//        }
//        return ret;
//    }
//
//    // byte类型数据，转成十六进制形式；
//    private static byte uniteBytes(byte src0, byte src1) {
//        byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
//                .byteValue();
//        _b0 = (byte) (_b0 << 4);
//        byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
//                .byteValue();
//        byte ret = (byte) (_b0 ^ _b1);
//        return ret;
//    }
//
//    public static byte[] hex2byte(String hex) {
//        String digital = "0123456789ABCDEF";
//        String hex1 = hex.replace(" ", "");
//        char[] hex2char = hex1.toCharArray();
//        byte[] bytes = new byte[hex1.length() / 2];
//        byte temp;
//        for (int p = 0; p < bytes.length; p++) {
//            temp = (byte) (digital.indexOf(hex2char[2 * p]) * 16);
//            temp += digital.indexOf(hex2char[2 * p + 1]);
//            bytes[p] = (byte) (temp & 0xff);
//        }
//        return bytes;
//    }
//
//    public static void main(String[] args) {
//        byte[] bytes1 = hexString2Bytes("0103C2000001B9B2");
//        byte[] bytes2 = hex2byte("0103C2000001B9B2");
//        System.out.println("0103020000B844".getBytes().length);
//    }
//}
