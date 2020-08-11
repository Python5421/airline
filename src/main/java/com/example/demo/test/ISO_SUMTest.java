package com.example.demo.test;

public class ISO_SUMTest {
//    public static void main(String[] args) {
//        ISOSum();
//    }
    byte[] ISOSum(byte[] buf)
    {
        byte[] sum=new byte[2];

        int i = 0,k1 = 0,k2 = 0;
        int c0 = 0,c1 = 0;
        for (i=0; i<buf.length; ++i)
        {
            c0 = c0 + buf[i];
            c1 = c1 + c0;
        }
        k1 = 0xff-((c0+c1)%0xff);
        k2 = c1;
        sum[0] = (byte)(k1%0xff);
        sum[1] = (byte)(k2%0xff);
        if(sum[0] == 0) sum[0] =(byte) 0xff;
        if(sum[1] == 0) sum[1] = (byte) 0xff;
        return sum;
    }
}
