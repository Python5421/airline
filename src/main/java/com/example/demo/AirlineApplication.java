package com.example.demo;

import com.example.demo.depo.Coordinate;
import com.example.demo.depo.Polygon;
import com.example.demo.service.AirLineService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@SpringBootApplication
public class AirlineApplication {

    //经度
    private double lng;
    //纬度
    private double lat;

    //外接矩形的顶点坐标
//    private LatLng[] Rectangle;
    public static void main(String[] args) {
        SpringApplication.run(AirlineApplication.class, args);
    //设置多边形
        Coordinate coordinate1=new Coordinate(9.00,2.00,0);
        Coordinate coordinate2=new Coordinate(6.00,8.00,0);
        Coordinate coordinate3=new Coordinate(1.00,5.00,0);
        Coordinate[] polygon_coord={coordinate1,coordinate2,coordinate3};
        Polygon polygon=new Polygon();
        polygon.setVertex(polygon_coord);
        Polygon polygonout=new AirLineService().createOutsideRectangle(polygon);
        Coordinate[] coordinates=polygonout.getVertex();
        for (int i=0;i<coordinates.length;i++){
            System.out.println(coordinates[i].getLng()+","+coordinates[i].getLat());
        }
    }




}
