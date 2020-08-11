package com.example.demo.test;

import com.example.demo.utils.IntToBytes;

import java.util.Arrays;

/**
 * 结论：参数意义：System.arraycopy(a,0,b ,1,3); a不变化,b 变化   前不变化，后变化
 */
public class SystemArraycopyTest {
    public static void main(String[] args) {
        int[] int_in_data=new int[]{0,1,2,3,4,5,6,7,8,9,10};
        int[] wayPoint=new int[]{1111,2222,3333,4444,5555};
        System.arraycopy(int_in_data,0,wayPoint ,1,3);
        for (int i=0;i<int_in_data.length;i++){
            System.out.println(int_in_data[i]);
        }
        for (int j=0;j<wayPoint.length;j++){
            System.out.println(wayPoint[j]);
        }

//        char[]a= {'a','b','c','d','e','f'},
//                b= {'1','2','3','4','5','6'};
//        int []c= {1,2,3,4,5,6},
//                d= {10,20,30,40,50,60};
//        System.arraycopy(a, 0, b, 0, a.length);
//        System.arraycopy(c, 2, d, 2, c.length-3);
//
//        System.out.println("数组a的各个元素中的值：");
//        System.out.println(Arrays.toString(a));
//        System.out.println("数组b的各个元素中的值：");
//        System.out.println(Arrays.toString(b));
//        System.out.println("数组c的各个元素中的值：");
//        System.out.println(Arrays.toString(c));
//        System.out.println("数组d的各个元素中的值：");
//        System.out.println(Arrays.toString(d));

    }
}
