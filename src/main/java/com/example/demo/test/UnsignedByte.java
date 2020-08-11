package com.example.demo.test;

public class UnsignedByte {


    public static void main(String[] args) {
        short aa= toUnsignedByte((byte)0xab);
        System.out.println(aa);
    }
    private UnsignedByte() {
    }

    public static short toUnsignedByte(byte b) {
       short value = (short) ((short) b & 0xFF);

        return value;
    }

//    public  byte toByte(UnsignedByte b) {
//        return b.rawValue;
//    }
//
//    public  byte toByte(short i) {
//        return (byte)i;
//    }
}
