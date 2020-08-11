package com.example.demo.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class ByteToChar {
    public static void main(String[] args) {
//        int number=0;
//        Byte b= 0x1D;
//        byte[] b1={0x1D};
//        int a= b1[0];
//        System.out.print(a);
//        char[] aaa=getChars(b1);
        double aa=21.340000;
        String bb=String.format("%,.8f",aa);
        System.out.println(bb);
    }
    private static  char[] getChars (byte[] bytes) {

        Charset cs = Charset.forName ("UTF-8");

        ByteBuffer bb = ByteBuffer.allocate (bytes.length);

        bb.put (bytes);

        bb.flip ();

        CharBuffer cb = cs.decode (bb);

        return cb.array();

    }
}
