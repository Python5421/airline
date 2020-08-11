package com.example.demo.utils;

public class IntToBytes {

    int value;
    public IntToBytes(int value1){
        this.value=value1;
    }
    public  byte[] intToBytes()
    {
        byte[] src = new byte[4];
        src[3] =  (byte) ((value>>24) & 0xFF);
        src[2] =  (byte) ((value>>16) & 0xFF);
        src[1] =  (byte) ((value>>8) & 0xFF);
        src[0] =  (byte) (value & 0xFF);
        return src;
    }
}
