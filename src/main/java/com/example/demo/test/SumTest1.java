package com.example.demo.test;

public class SumTest1 {
    public static void main(String[] args) {
        test1();
    }

    /**
     * 求校验和的算法
     * @param b 需要求校验和的字节数组
     * @return 校验和
     */
    private static byte sumCheck(byte[] b, int len){
        int sum = 0;
        for(int i = 0; i < len; i++){
            sum = sum + b[i];
        }
        if(sum > 0xff){ //超过了255，使用补码（补码 = 原码取反 + 1）
            sum = ~sum;
            sum = sum + 1;
        }
        return (byte) (sum & 0xff);
    }


    public static void test1(){
//        byte[] b = new byte[7];
//        b[0] = (byte) 0xfd;
//        b[1] = (byte) 0xfc;
//        b[2] = (byte) 0x08;
//        b[3] = (byte) 0x80;
//        b[4] = (byte) 0x02;
//        b[5] = (byte) 0x00;
//        b[6] = (byte) 0x0a;
//      byte[] bytes=new byte[]{(byte)0xAA,0x63,0x7C,0x00,(byte)0xEF,0x79,0x00,(byte)0x90,0x00,0x74,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00, (byte) 0xC0,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,(byte)0xC3,0x36,(byte)0xD3,0x09,(byte)0xD7};
        byte[] bytes=new byte[]{(byte)0xAB,(byte)0x34,(byte)0x00,(byte)0x02,(byte)0x00,(byte)0x86,(byte)0x76,(byte)0x4E,(byte)0xB3,(byte)0x40,(byte)0x1B,(byte)0x41,(byte)0x40,(byte)0xBB,(byte)0xEE,(byte)0xAD,(byte)0x48,(byte)0x4C,(byte)0x1C,(byte)0x41,(byte)0x40,(byte)0x33,(byte)0x36,(byte)0x74,(byte)0xB3,(byte)0x3F,(byte)0x35,(byte)0x5B,(byte)0x40,(byte)0x15,(byte)0x39,(byte)0x44,(byte)0xDC,(byte)0x9C,(byte)0x34,(byte)0x5B,(byte)0x40,(byte)0xDB,(byte)0xA7,(byte)0xE3,(byte)0x31,(byte)0xDB,(byte)0xA7,(byte)0xE3,(byte)0x31,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
        byte result = sumCheck(bytes, bytes.length);
        System.out.printf("%x", result);//正确的结果应该是8d
    }

}