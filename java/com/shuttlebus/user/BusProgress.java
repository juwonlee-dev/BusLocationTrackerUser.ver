package com.shuttlebus.user;

import android.location.Location;
import android.util.Log;

public class BusProgress {
    private Distance distanceTo = new Distance();
//    private DB_GetData getData = new DB_GetData();

    private int progress;
    private double maxDis;
//    private static int index = 0;
//    private double latitude;
//    private double longitude;





    public BusProgress(double maxDis){
        this.maxDis = maxDis;
    }

    // 진행률
//    public int getProgress(Location location, double curLat, double curLon){
////        double distance = (distanceTo.getDistanceTo(location,curLat, curLon) * 0.001);
//        Log.e("distanceToApart: " + distance, toString());
//
//        if ((maxDis - distance) > 0) {
//            progress = (int) ((maxDis - distance) / maxDis * 100);
//        } else {
//            progress = 0;
//        }
//
//        return progress;
//    }
//
//    public int getProgress(double sLat, double sLon, double eLat, double eLon){
////        double distance = (distanceTo.getDistanceTo(sLat, sLon ,eLat, eLon) * 0.001);
//        Log.e("distanceToApart: " + distance, toString());
//
//        if ((maxDis - distance) > 0) {
//            progress = (int) ((maxDis - distance) / maxDis * 100);
//        } else {
//            progress = 0;
//        }
//
//        return progress;
//    }

    public double getMaxDis(){
        return maxDis;
    }

    public void setMaxDis(double maxDis){
        this.maxDis = maxDis;
    }
}
