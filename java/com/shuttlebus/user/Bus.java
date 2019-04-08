package com.shuttlebus.user;

public class Bus {
    private double latitude;
    private double longitude;

    private long beforeTime;
    public long lateTime(int speed, float distance){
        long time = (long)(distance/speed);
        speed = 50;

        if(speed > 10){
            beforeTime = time;
            return time;
        }
        else {
            return beforeTime;
        }
    }

}
