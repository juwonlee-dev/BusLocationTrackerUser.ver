package com.shuttlebus.user;

import android.util.Log;

public class BusProgress {
    private Distance distanceTo;
    private DB_GetData getData = new DB_GetData();

    private int progress;
    private double maxDis;


    public BusProgress(double maxDis){
        distanceTo = new Distance();
        this.maxDis = maxDis;
    }

    public int getProgress(double curLat, double curLon){
        double distance = (distanceTo.distanceToApart(curLat, curLon) * 0.001);
        Log.e("distanceToApart: " + distance, toString());

        if ((maxDis - distance) > 0) {
            progress = (int) ((maxDis - distance) / maxDis * 100);
        } else {
            progress = 0;
        }

        return progress;
    }

}
