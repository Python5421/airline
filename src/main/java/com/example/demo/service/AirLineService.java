package com.example.demo.service;

import com.example.demo.depo.Coordinate;
import com.example.demo.depo.Polygon;

import java.util.Arrays;

public class AirLineService {

    public static void main(String[] args) {
        //设置多边形
        Coordinate coordinate1=new Coordinate(9.00,2.00,0);
        Coordinate coordinate2=new Coordinate(6.00,8.00,0);
        Coordinate coordinate3=new Coordinate(1.00,5.00,0);
        Coordinate[] polygon_coord={coordinate1,coordinate2,coordinate3};
        Polygon polygon=new Polygon();
        polygon.setVertex(polygon_coord);
        Polygon polygonout=createOutsideRectangle(polygon);
        Coordinate[] coordinates=polygonout.getVertex();
        for (int i=0;i<coordinates.length;i++){
            System.out.println(coordinates[i].getLng()+","+coordinates[i].getLat());
        }
    }

    /**
     * 创建外接矩形
     * @author xiexiaoyu
     * @param polygon
     * @return Polygon 外接多边形
     */

    public static Polygon createOutsideRectangle(Polygon polygon) {
        //从入参中获取顶点数组
        Coordinate[] vertexs = polygon.getVertex();
        //顶点个数
        int sum_vertexs = vertexs.length;
        //获取经度、纬度数组
        Double[] lngs = new Double[sum_vertexs];
        Double[] lats = new Double[sum_vertexs];
        for (int i = 0; i < sum_vertexs; i++) {
            lngs[i] = vertexs[i].getLng();
            lats[i] = vertexs[i].getLat();
        }
        //数组排序，然后取最大最小经纬度
        Arrays.sort(lngs);
        Arrays.sort(lats);
        double max_lng = lngs[sum_vertexs-1];
        double min_lng = lngs[0];
        double max_lat = lats[sum_vertexs-1];
        double min_lat = lats[0];
        //四个顶点 顶点为 ：最大经度纬度 最大经度最小纬度 最大纬度最小经度 最小经纬度
        Coordinate MaxLng_Maxlat = new Coordinate(max_lng,max_lat,0);
        Coordinate MaxLng_Minlat = new Coordinate(max_lng,min_lat,0);
        Coordinate MinLng_Minlat = new Coordinate(min_lng,min_lat,0);
        Coordinate MinLng_Maxlat = new Coordinate(min_lng,max_lat,0);


        //外接矩形顶点坐标数组
        Coordinate[] coordinates={MaxLng_Maxlat,MaxLng_Minlat,MinLng_Minlat,MinLng_Maxlat};
        //初始化外接矩形
        Polygon rectangle = new Polygon();
        rectangle.setVertex(coordinates);

        return rectangle;
    }



}
