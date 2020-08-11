package com.example.demo.utils;

import com.example.demo.depo.Coordinate;

public class CalculateDegree {

    public static void main(String[] args) {

        //构造函数
        CalculateDegree calculateDegree = new CalculateDegree();
        //测试用数据
        //A点
        double lon = 108.832013;
        double lat = 34.212912;
        double high = 405.250780;
        //B点
        double lon1 = 108.822074;
        double lat2 = 34.221078;
        double high1 = 405.250780;

        //double brng = 180;
        double dist = 50;
        Coordinate coordinateA = new Coordinate(lon, lat, high);
        Coordinate coordinateB = new Coordinate(lon1, lat2, high1);

        //1.    求AB向量的方位角 可为负数          Xa<Xb Ya>Yb 0.009939 -0.008166
        double ABDegree = calculateDegree.getVectorOfAzimuth(coordinateA, coordinateB);
//        double ABDegree = calculateDegree.getVectorOfAzimuth(coordinateA, coordinateB,"");

        System.out.println("AB方位角：" + ABDegree);
//        System.out.println("AB方位角(改良1)：" + azimuthAngle(coordinateA.getLng(), coordinateA.getLat(), coordinateB.getLng(), coordinateB.getLat()));
//        System.out.println("AB方位角(改良2)：" + azimuthAngle(coordinateA.getLat(), coordinateA.getLng(), coordinateB.getLat(), coordinateB.getLng()));
        //2.    求C点方位角
        double CDegree = calculateDegree.getVectorOfAzimuth(coordinateA, coordinateB, "RIGHT");
//        double CDegree = calculateDegree.getCDegress(coordinateA, coordinateB, "RIGHT");
        System.out.println("C方位角：" + CDegree);
        //设C,D为垂直于AB的航点集合
        Coordinate[] C = calculateDegree.getC(coordinateB, CDegree, 6, dist);
        Coordinate[] D = calculateDegree.getC(coordinateA, CDegree, 6, dist);
        for (int i = 0; i < 6; i++) {
            System.out.println("{\"B\":\"" + String.format("%,.6f", C[i].getLat()) + "\",\"L\":\"" + String.format("%,.6f", C[i].getLng()) + "\",\"PName\":\"" + 11 * (i + 1) + "\",\"Status\":1},");
        }
        for (int i = 0; i < 6; i++) {
            System.out.println("{\"B\":\"" + String.format("%,.6f", D[i].getLat()) + "\",\"L\":\"" + String.format("%,.6f", D[i].getLng()) + "\",\"PName\":\"" + 11 * (i + 1) + "\",\"Status\":1},");
        }

    }

    //   方法名：获取航点 入参：1）A，B点 2）方向 3）行距 4）行次  返回：航点集合
    Coordinate[] getC(Coordinate B, double degress, int count, double dist) {
        Coordinate[] coordinates = new Coordinate[count];
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                coordinates[i] = computerThatLonLat(coordinates[i - 1], degress + 90, dist);
            } else
                coordinates[i] = computerThatLonLat(B, degress + 90, dist);
        }
        return coordinates;
    }


    /**
     * 数学中的角度分弧度和角度两种
     * 弧度的圆周是2π,而角度的圆周是360°
     * 所以要将角度化为弧度就只需用角度乘以π/180,反之就除以(π/180)
     */
    //角度转弧度
    private static double toRadius(double value) {
        return value * Math.PI / 180;
    }

    //弧度转角度
    private static double toDegree(double value) {
        return value * 180 / Math.PI;
    }


    /*
     * 大地坐标系资料WGS-84 长半径a=6378137 短半径b=6356752.3142 扁率f=1/298.2572236
     */
    /**
     * 长半径a=6378137
     */
    private double a = 6378137;
    /**
     * 短半径b=6356752.3142
     */
    private double b = 6356752.3142;
    /**
     * 扁率f=1/298.2572236
     */
    private double f = 1 / 298.2572236;

    /**
     * 计算另一点经纬度
     *
     * @param brng 方位角
     * @param dist 距离（米）
     *             航线条数
     */
    public Coordinate computerThatLonLat(Coordinate AorB, double brng, double dist) {
        //,double lon, double lat
        //角度转弧度
        double alpha1 = rad(brng);

        double sinAlpha1 = Math.sin(alpha1);
        double cosAlpha1 = Math.cos(alpha1);


        double tanU1 = (1 - f) * Math.tan(rad(AorB.getLat()));
        double cosU1 = 1 / Math.sqrt((1 + tanU1 * tanU1));
        double sinU1 = tanU1 * cosU1;
        double sigma1 = Math.atan2(tanU1, cosAlpha1);
        double sinAlpha = cosU1 * sinAlpha1;
        double cosSqAlpha = 1 - sinAlpha * sinAlpha;
        double uSq = cosSqAlpha * (a * a - b * b) / (b * b);
        double A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));
        double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));

        double cos2SigmaM = 0;
        double sinSigma = 0;
        double cosSigma = 0;
        double sigma = dist / (b * A), sigmaP = 2 * Math.PI;
        while (Math.abs(sigma - sigmaP) > 1e-12) {
            cos2SigmaM = Math.cos(2 * sigma1 + sigma);
            sinSigma = Math.sin(sigma);
            cosSigma = Math.cos(sigma);
            double deltaSigma = B * sinSigma * (cos2SigmaM + B / 4 * (cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM)
                    - B / 6 * cos2SigmaM * (-3 + 4 * sinSigma * sinSigma) * (-3 + 4 * cos2SigmaM * cos2SigmaM)));
            sigmaP = sigma;
            sigma = dist / (b * A) + deltaSigma;
        }

        double tmp = sinU1 * sinSigma - cosU1 * cosSigma * cosAlpha1;
        double lat2 = Math.atan2(sinU1 * cosSigma + cosU1 * sinSigma * cosAlpha1,
                (1 - f) * Math.sqrt(sinAlpha * sinAlpha + tmp * tmp));
        double lambda = Math.atan2(sinSigma * sinAlpha1, cosU1 * cosSigma - sinU1 * sinSigma * cosAlpha1);
        double C = f / 16 * cosSqAlpha * (4 + f * (4 - 3 * cosSqAlpha));
        double L = lambda - (1 - C) * f * sinAlpha
                * (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM)));
//夹角
        double revAz = Math.atan2(sinAlpha, -tmp); // final bearing

//        System.out.println(revAz);
//        System.out.println(AorB.getLng() + deg(L) + "," + deg(lat2));

//        暂时认为是同高度
        double high_1 = AorB.getHigh();
        Coordinate coordinate = new Coordinate(AorB.getLng() + deg(L), deg(lat2), high_1);
        return coordinate;
    }

    /**
     * 度换成弧度
     *
     * @param d 度
     * @return 弧度
     */
    private double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 弧度换成度
     *
     * @param x 弧度
     * @return 度
     */
    private double deg(double x) {
        return x * 180 / Math.PI;
    }

    // 向量的方位角 经线指南北，纬线指东西 也就是说经线是Y，纬线是X
    private double getVectorOfAzimuth(Coordinate coordinateA, Coordinate coordinateB) {
        double Ay = coordinateA.getLng();
        double Ax = coordinateA.getLat();

        double By = coordinateB.getLng();
        double Bx = coordinateB.getLat();
        double AB_Y = Math.tan(Math.abs(Ax - Bx) / Math.abs(Ay - By));
//        double AB_X=Math.tan(Math.abs(Ay-By));
        if (Bx >= Ax) {
            if (By >= Ay) {
                return AB_Y;
            } else return 180 - AB_Y;
        }

        if (Bx <= Ax) {
            if (By >= Ay) {
                return AB_Y * (-1);
            } else return AB_Y - 180;
        }
        return AB_Y;
    }


    public static double azimuthAngle(double x1, double y1, double x2, double y2) {
        double dx, dy, angle = 0;
        dx = x2 - x1;
        dy = y2 - y1;
        if (x2 == x1) {
            angle = Math.PI / 2.0;
            if (y2 == y1) {
                angle = 0.0;
            } else if (y2 < y1) {
                angle = 3.0 * Math.PI / 2.0;
            }
        } else if ((x2 > x1) && (y2 > y1)) {
            angle = Math.atan(dx / dy);
        } else if ((x2 > x1) && (y2 < y1)) {
            angle = Math.PI / 2 + Math.atan(-dy / dx);
        } else if ((x2 < x1) && (y2 < y1)) {
            angle = Math.PI + Math.atan(dx / dy);
        } else if ((x2 < x1) && (y2 > y1)) {
            angle = 3.0 * Math.PI / 2.0 + Math.atan(dy / -dx);
        }

        return (angle * 180 / Math.PI);
    }



    //向量的方位角 经线指南北，纬线指东西 也就是说经线是Y，纬线是X
    // C方位角 around为LEFT或者RIGHT
    private double getVectorOfAzimuth(Coordinate coordinateA, Coordinate coordinateB, String around) {
        double Ay = coordinateA.getLng();
        double Ax = coordinateA.getLat();

        double By = coordinateB.getLng();
        double Bx = coordinateB.getLat();
        double AB_Y = Math.tan(Math.abs(Ax - Bx) / Math.abs(Ay - By));
//        double AB_X=Math.tan(Math.abs(Ay-By));
//      AB在第一象限
        if (Bx >= Ax) {
            if (By >= Ay) {

                if (around!=null&&around!=""&&around.equalsIgnoreCase("LEFT")) {
                    return AB_Y - 90;
                } else {
                    return AB_Y + 90;
                }

            }
//       第二象限
            else if (around!=null&&around!=""&&around.equalsIgnoreCase("LEFT")) {
                return AB_Y;
            } else {
                return AB_Y * (-1) - 90;
            }
        }
        if (Bx <= Ax) {
            //       第三象限
            if (By <= Ay) {
                if (around!=null&&around!=""&&around.equalsIgnoreCase("LEFT")) {
                    return AB_Y + 90;
                } else {
                    return AB_Y - 90;
                }
            }
//            第四象限
            else if (around!=null&&around!=""&&around.equalsIgnoreCase("LEFT")) {
                return AB_Y * (-1) - 90;
            } else {
                return 90 - AB_Y;
            }
        }
        return AB_Y;
    }
}
