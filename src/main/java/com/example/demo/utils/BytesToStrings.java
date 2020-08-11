package com.example.demo.utils;

public class BytesToStrings {
    //注意数组复制的时候 Arrays.copyOfRange 后面的第二个参数  是不包含 *****************************屡次被坑
    public static void main(String[] args) {
        String s="AA633700AB34000200000086764EB3401B4140BBEEAD484C1C4140333674B33F355B40153944DC9C345B40DBA7E331DBA7E3310000000000000000160209D7";
        addComma(s);
        System.out.println(addComma(s));
    }
    public  String[] getStrings(byte[] inBytes){
        BytesToString bytesToString=new BytesToString();
        String aa1231 = bytesToString.bytesToString(inBytes);
        String asdfa=addComma(aa1231);
        String[] split = asdfa.split("\\s+");
        return  split;
    }

    public static String addComma(String str) {
        String newstr = "";
        int size = ((str.length()) % 2 == 0) ? ((str.length()) / 2) : ((str.length()) / 2 + 1);
        for (int i = 0; i < size; i++) {
            int endIndex = (i + 1) * 2;
            if ((i + 1) == size) {
                endIndex = str.length();
            }
            if (i == 0) {
                newstr += str.substring(i, endIndex);
            } else {
                newstr += " " + str.substring(i * 2, endIndex);
            }
        }
        return newstr;
    }
}
