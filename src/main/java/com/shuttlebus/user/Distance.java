package com.shuttlebus.user;

import android.util.Log;

public class Distance implements Station {


    public Distance(){
//        setGps();
    }

    public static double allDistance(BusStation[] stations){
        double allDistance=0;
        for(int i = 0; i<stations.length-1;i++)
            allDistance += Distance.distance(stations[i], stations[i+1]);
        return allDistance;
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist=Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) +
                Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                        Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return dist;
    }

    public static double distance(Location start, Location end) {
        double theta = start.getLongitude() - end.getLongitude();
        double dist=Math.sin(deg2rad(start.getLatitude())) * Math.sin(deg2rad(end.getLatitude())) +
                Math.cos(deg2rad(start.getLatitude())) * Math.cos(deg2rad(end.getLatitude())) *
                        Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return dist;
    }

    public static double distance(Location start, BusStation end) {
        double theta = start.getLongitude() - end.getLongitude();
        double dist=Math.sin(deg2rad(start.getLatitude())) * Math.sin(deg2rad(end.getLatitude())) +
                Math.cos(deg2rad(start.getLatitude())) * Math.cos(deg2rad(end.getLatitude())) *
                        Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return dist;
    }

    public static double distance(BusStation start, BusStation end) {
        double theta = start.getLongitude() - end.getLongitude();
        double dist=Math.sin(deg2rad(start.getLatitude())) * Math.sin(deg2rad(end.getLatitude())) +
                Math.cos(deg2rad(start.getLatitude())) * Math.cos(deg2rad(end.getLatitude())) *
                        Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return dist;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI /180);
    }

    private static double rad2deg(double red) {
        return (red / Math.PI * 180);
    }
}


