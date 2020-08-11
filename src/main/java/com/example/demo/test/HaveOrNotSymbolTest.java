package com.example.demo.test;

public class HaveOrNotSymbolTest {
    public static void main(String[] args) {
//        System.out.println(0x80);//128
//
//        //0x81看作是int型，最高位(第32位)为0，所以是正数
//        System.out.println(0x81);//129
//        byte a=(byte)0x81;
//        System.out.println(a);//129
//        System.out.println(a&0xff);
        System.out.println("直接打印(byte)0xab&：");
        System.out.println((byte)0xab);
        System.out.println("直接打印(byte)0xab&0xff：");
        System.out.println((byte)0xab&0xff);
//        byte[] a1=new byte[2];
//        a1[0]=(byte)0xab&0xff;
        int a=171;
        System.out.println((byte)a);

//        System.out.println(0x8001);//32769
    }
    private byte[] getHexBytes(String message) {
        int len = message.length() / 2;
        char[] chars = message.toCharArray();
        String[] hexStr = new String[len];
        byte[] bytes = new byte[len];
        for (int i = 0, j = 0; j < len; i += 2, j++) {
            hexStr[j] = "" + chars[i] + chars[i + 1];
            bytes[j] = (byte) Integer.parseInt(hexStr[j], 16);
        }
        return bytes;
    }
}
