package com.example.ttx.appmessagerme.Model;

public class Endpoint {
    static String namepoit;
    double lat;
    double log;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }

    public static String getNamepoit() {
        return namepoit;
    }

    public void setNamepoit(String namepoit) {
        this.namepoit = namepoit;
    }
}
