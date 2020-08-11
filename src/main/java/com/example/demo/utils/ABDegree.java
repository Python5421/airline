package com.example.demo.utils;

import com.example.demo.depo.Coordinate;


public class ABDegree {
    static int count = 5;


    public static void main(String[] args) {
        double R = 6371.393 * 1000;
//        A点
        double lon = 108.832013;
        double lat = 34.212912;
//        double lon = 0;
//        double lat = 0;
//        B点
        double lon1 = 108.802013;
        double lat2 = 34.221078;

        double brng = 180;
        double dist = 50;
        Coordinate coordA = new Coordinate(lon, lat,0);
        Coordinate coordB = new Coordinate(lon1, lat2,0);

        getAllPoints(coordA,coordB,1000,1,5);
        //        向左
//        getAllPoints(coordA,coordB,"A",1000,0,5);

    }

    /**
     * 求所有航点
     * @param A 顶点A
     * @param B 顶点B
     * @param direction 方向 0左 1 右
     * @param count 飞行行数
     * @return
     */
    static Coordinate[] getAllPoints(Coordinate A, Coordinate B, int width,int direction, int count) {
        Coordinate[] allPoints = new Coordinate[count * 2 + 2];

        allPoints[0] = A;
        allPoints[1] = B;
//        求AB方位角
        double abDegree = getABDegree(A, B);
//      获取BC方位角
        double bcDegree = getBCDegree(abDegree, direction);
//        q求C、D集合
        Coordinate[] cs = getCs(B, width, bcDegree, count);
        Coordinate[] ds = getCs(A, width, bcDegree, count);

        for (int i = 1; i <= count; i++) {
            if ((i & 1) != 0) {
                allPoints[2 * i] = cs[i - 1];
                allPoints[2 * i + 1] = ds[i - 1];
            } else {
                allPoints[2 * i] = ds[i - 1];
                allPoints[2 * i + 1] = cs[i - 1];
            }
        }
        for(int i = 2; i < allPoints.length; i++){
            System.out.println("var point"+(i-1)+"=new T.LngLat(" + String.format("%,.6f", allPoints[i].getLng()) + "," + String.format("%,.6f", allPoints[i].getLat()) + ");");
        }

        return allPoints;

    }

    static double getABDegree(Coordinate A, Coordinate B) {
        double Aj = A.getLng();
        double Aw = A.getLat();
        double Bj = B.getLng();
        double Bw = B.getLat();

        double x = Math.sin(Bj - Aj) * Math.cos(Bw);
        double y = Math.cos(Aw) * Math.sin(Bw) - Math.sin(Aw) * Math.cos(Bw) * Math.cos(Bj - Aj);
        double Z = Math.atan2(x, y);
        Z = Z * 180 / Math.PI;
        double Bearing = MOD(Z, 360);
        return 360 - Bearing;
    }

    static double MOD(double n, double d) {
        return n - d * Math.floor(n / d);
    }

    //    0代表左，1代表右
    static double getBCDegree(double ABDregree, int direction) {
        double tem = 0;
        tem = direction == 0 ?
                (ABDregree - 90)
                : (ABDregree + 90);
        tem = tem >= 360 ? (tem - 360) : tem;
        tem = tem < 0 ? (360 + tem) : tem;
        return tem;
    }

    // 获取C点集合  B对应那一列是C列 A对应那一列是D列
    static Coordinate[] getCs(Coordinate coordinate, double width, double degree, int count) {
         Coordinate[] cs = new Coordinate[count];
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                cs[i] = getC(coordinate, width, degree);
            } else
                cs[i] = getC(cs[i - 1], width, degree);
        }
        return cs;
    }

    ;

    /**
     * 求C点的坐标
     *
     * @param B
     * @param width
     * @param degree
     * @return
     */
    static Coordinate getC(Coordinate B, double width, double degree) {
        double Bj = B.getLng();
        double Bw = B.getLat();
        double ARC = 6371.393 * 1000;
        double Cj = Bj + (width * Math.sin(degree * Math.PI / 180)) / ((ARC * Math.cos(Bw * Math.PI / 180)) * Math.PI / 180);
        double Cw = Bw + (width * Math.cos(degree * Math.PI / 180)) / (ARC * Math.PI / 180);
//        System.out.println("C点坐标" + String.format("%,.6f", Cj) + "," + String.format("%,.6f", Cw));
//        System.out.println("var pointC"+0+"=new T.LngLat(" + String.format("%,.6f", Cj) + "," + String.format("%,.6f", Cw) + ");");
        Coordinate C = new Coordinate(Cj, Cw,0);
        return C;
    }


}
