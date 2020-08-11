package com.example.demo.depo;

//坐标
public class Coordinate {
    //    纬度
    double lat;
    //    经度
    double lng;
    //      高度
    double high;

    public Coordinate(double lng_in, double lat_in, double high) {
        this.lng = lng_in;
        this.lat = lat_in;
        this.high = high;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public double getHigh() {
        return high;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setHigh(double high) {
        this.high = high;
    }
}
