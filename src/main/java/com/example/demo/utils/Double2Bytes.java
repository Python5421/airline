package com.example.demo.utils;

public class Double2Bytes {
    double a1;
    public   Double2Bytes(double a){
        this.a1=a;
    }


    public  byte[] double2Bytes() {
        long value = Double.doubleToRawLongBits(a1);
        byte[] byteRet = new byte[8];
        for (int i = 0; i < 8; i++) {
            byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
        }
        return byteRet;
    }

//    public static void main(String[] args) {
//        double b=-10.6;
//        byte[] b1=double2Bytes(b);
//        BytesToString bytesToString=new BytesToString(b1);
//
//        System.out.println(bytesToString);
//        System.out.println(bytes2Double(double2Bytes(b)));
//    }

    public  double bytes2Double(byte[] arr) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        }
        return Double.longBitsToDouble(value);
    }

}
