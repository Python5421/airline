package com.example.demo.utils;

import java.util.Arrays;
import java.util.Comparator;
//这个可以用来倒序排数组
public class MyComparator implements Comparator<Double> {
//    public static void main(String[] args) {
//        double[] a={0.00,1.00,2.00,3.00};
//        Arrays.sort(a);
//        for (int i=0;i<a.length;i++){
//            System.out.print(a[i]+",");
//        }
//        System.out.println("");
//        Double[] b={0.00,1.00,2.00,3.00};
//        Comparator comparator=new MyComparator();
//        Arrays.sort(b,comparator);
//        for (int i=0;i<b.length;i++){
//            System.out.print(b[i]+",");
//        }
//        System.out.println("");
//    }
    @Override
    public int compare(Double o1, Double o2) {
//      如果o1小于o2，我们就返回正值，

//      这样颠倒一下，就可以实现反向排序了
        if (o1 < o2) {
            return 1;
        }
        //      如果o1大于o2我们就返回负值，
        else if (o1 > o2) {
            return -1;
        }

        else {
            return 0;
        }

    }
}
