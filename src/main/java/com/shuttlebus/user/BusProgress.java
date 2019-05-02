package com.shuttlebus.user;

public class BusProgress {
    public static int progress;

    public static int getProgress(double max, double value) {

        if ((max - value) > 0) {
            progress = (int) ((max - value) / max * 100);
        } else {
            progress = 0;
        }

        return progress;
    }

}
