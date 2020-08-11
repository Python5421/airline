package com.example.demo.service;

import com.example.demo.depo.Coordinate;
import com.example.demo.utils.Double2Bytes;
import com.example.demo.utils.IntToBytes;

public class InPutTest {
    public static void main(String[] args) {
//        A点
        double lon = 108.832013;
        double lat = 34.212912;
        double high=405.250780;
//        B点
        double lon1 = 108.822074;
        double lat2 = 34.221078;
        double high1=405.250780;

        Coordinate coordA1 = new Coordinate(lon, lat,high);
        Coordinate coordB1 = new Coordinate(lon1, lat2,high1);
        getInputBytes(coordA1,coordB1);
    }
    static byte[] getInputBytes(Coordinate coordA,Coordinate coordB){
        double Aj = coordA.getLng();
        double Aw = coordA.getLat();
        double Ah = coordA.getHigh();

        double Bj = coordB.getLng();
        double Bw = coordB.getLat();
        double Bh = coordB.getHigh();


        byte[] bytes_in=new byte[57];
        bytes_in[0]=(byte)0xAA;
        bytes_in[1]=(byte)0x63;
        //数据域长度
        bytes_in[2]=(byte)0x60;
        bytes_in[3]=(byte)0x00;

        bytes_in[4]=(byte)0x00;
        //数据内容长度
        bytes_in[2]=(byte)0x49;
        bytes_in[3]=(byte)0x00;

        //数据部分
        byte[] bytes_in_data=new byte[49];
        bytes_in_data[0]=(byte)0xab;
        //航点数 低字节在前，高字节在后 4字节
        byte[] wayPoint=new IntToBytes(2).intToBytes();
        System.arraycopy(wayPoint,0,bytes_in_data ,1,4);
        //纬度经度高度
        byte[] bytes_aw=new Double2Bytes(Aw).double2Bytes();
        System.arraycopy(bytes_aw,0,bytes_in_data ,5,8);
        byte[] bytes_bw=new Double2Bytes(Bw).double2Bytes();
        System.arraycopy(bytes_bw,0,bytes_in_data ,13,8);
        byte[] bytes_aj=new Double2Bytes(Aj).double2Bytes();
        System.arraycopy(bytes_aj,0,bytes_in_data ,21,8);
        byte[] bytes_bj=new Double2Bytes(Bj).double2Bytes();
        System.arraycopy(bytes_bj,0,bytes_in_data ,29,8);
        byte[] bytes_ah=new Double2Bytes(Ah).double2Bytes();
        System.arraycopy(bytes_ah,0,bytes_in_data ,37,4);
        byte[] bytes_bh=new Double2Bytes(Bh).double2Bytes();
        System.arraycopy(bytes_bh,0,bytes_in_data ,41,4);
        bytes_in_data[45]=0x00;
        bytes_in_data[46]=0x00;
        //航点悬停方向 S16 N*2
        bytes_in_data[47]=0x00;
        bytes_in_data[48]=0x00;

        System.arraycopy(bytes_in_data,0,bytes_in ,4,49);
        bytes_in[53]=(byte)0x00;
        bytes_in[54]=(byte)0x00;
        bytes_in[55]=(byte)0x09;
        bytes_in[56]=(byte)0xD7;

        return bytes_in;
    }
}
