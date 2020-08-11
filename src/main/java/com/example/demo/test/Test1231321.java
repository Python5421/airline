package com.example.demo.test;

import com.example.demo.depo.Coordinate;

public class Test1231321 {
    public static void main(String[] args) {
        double lon = 108.832013;
        double lat = 34.212912;
        double high = 405.250780;
        //B点
        double lon1 = 108.822074;
        double lat2 = 34.221078;
        double high1 = 405.250780;
        Coordinate[] coordinates=new Coordinate[10];
        for(int i=0;i<10;i++){
            //经度
            coordinates[i]=new Coordinate(lon+0.000100,lat+0.000100*i,high+0.000100*i);
        }
        for(int j=0;j<coordinates.length;j++){
            System.out.println(j+":"+coordinates[j].getLng()+","+coordinates[j].getLat()+","+coordinates[j].getHigh());
        }

    }
}
