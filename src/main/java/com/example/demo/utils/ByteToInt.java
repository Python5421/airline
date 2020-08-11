package com.example.demo.utils;

public class ByteToInt {
    public static void main(String[] args) {
        int number=0;
        Byte b= 0x1D;
        for(int i = 0; i < 4 ; i++) {
            number += b << i * 4;
        }
        System.out.print(number);
    }
}
