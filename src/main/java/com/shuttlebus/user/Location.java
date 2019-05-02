package com.shuttlebus.user;

public class Location {
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Location [latitude=" + latitude + ", longitude=" + longitude + "]";
    }

    public Location(){
        this(0.0,0.0);
    }

    public Location(double lat, double lon){
        this.latitude = lat;
        this.longitude = lon;
    }

}
