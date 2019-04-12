package com.shuttlebus.user;

import android.location.Location;
import android.util.Log;

public class Distance implements Station {
    Location gong = new Location("Gps");
    Location curLocation = new Location("now");
    Location apartment = new Location("apartment");
    Location mStationExit1 = new Location("mStationExit1");
    Location mStationExit2 = new Location("mStationExit2");
    Location underBridge = new Location("underBridge");
    Location simSchool = new Location("simSchool");
    Location songSchool = new Location("songSchool");
    Location maSchoolFront = new Location("maSchoolFront");
    Location maSchoolBack = new Location("maSchoolBack");
    Location maHiSchool = new Location("maHiSchool");

    // ap = 아파트 , mSe1 = 마석역 1번출구 , m

    private double allDistance = 0;
    private double distance[] = new double[8];


    public Distance(){
        setGps();
    }

    public void setGps(){
        curLocation.setLatitude(curLocation.getLatitude());
        curLocation.setLongitude(curLocation.getLongitude());

        gong.setLatitude(gongLat);
        gong.setLongitude(gongLon);
        apartment.setLatitude(apartmentLat);
        apartment.setLongitude(apartmentLon);
        mStationExit1.setLatitude(mStationExit1Lat);
        mStationExit1.setLongitude(mStationExit1Lon);
        mStationExit2.setLatitude(mStationExit2Lat);
        mStationExit2.setLongitude(mStationExit2Lon);
        underBridge.setLatitude(underBridgeLat);
        underBridge.setLongitude(underBridgeLon);
        simSchool.setLatitude(simSchoolLat);
        simSchool.setLongitude(simSchoolLon);
        songSchool.setLatitude(songSchoolLat);
        songSchool.setLongitude(songSchoolLon);
        maSchoolFront.setLatitude(maSchoolFrontLat);
        maSchoolFront.setLongitude(maSchoolFrontLon);
        maSchoolBack.setLatitude(maSchoolBackLat);
        maSchoolBack.setLongitude(maSchoolBackLon);
        maHiSchool.setLatitude(maHiSchoolLat);
        maHiSchool.setLongitude(maHiSchoolLon);
    }

    public double distanceToApart(double currentLat, double currentLon){
        double currentDistance;

        Location currentGps = new Location("current");
        currentGps.setLatitude(currentLat);
        currentGps.setLongitude(currentLon);
        currentDistance = currentGps.distanceTo(apartment);
//        Log.e("curDistance: " + currentDistance, toString());
        return currentDistance;
    }


    public double distanceToStation(double currentLat, double currentLon){
        double currentDistance;

        Location currentGps = new Location("current");
        currentGps.setLatitude(currentLat);
        currentGps.setLongitude(currentLon);
        currentDistance = currentGps.distanceTo(mStationExit1);
        return currentDistance;
    }
    public double distanceToGong(double currentLat, double currentLon){
        double currentDistance;

        Location currentGps = new Location("current");
        currentGps.setLatitude(currentLat);
        currentGps.setLongitude(currentLon);
        currentDistance = currentGps.distanceTo(gong);
        return currentDistance;
    }

//    public double getDistanceTo(Location location, double lat, double lon){
//        float dis[] = new float[0];
//        Location.distanceBetween(location.getLatitude(),location.getLongitude(),lat,lon, dis);
//        return (double) dis[0];
//    }

//    public double getDistanceTo(double sLat,double sLon, double eLat, double eLon){
//        float dis[] = new float[0];
//        Location.distanceBetween(sLat,sLon,eLat,eLon, dis);
//        return (double) dis[0];
//    }

    public double getDistanceTo(BusStation start, BusStation end){

        double dis;
        Location busStart = new Location("");
        Location busEnd = new Location("");
        busStart.setLatitude(start.getLatitude());
        busStart.setLongitude(start.getLongitude());
        busEnd.setLatitude(end.getLatitude());
        busEnd.setLongitude(end.getLongitude());

        dis = busStart.distanceTo(busEnd);

        Log.e("MaxDistance: "+dis,toString());
        return dis;
    }

    public double allDistance(){

        // 아파트 ->  역 1번출
        distance[0] = apartment.distanceTo(mStationExit1);

        // 마석역 1번출 -> 다리
        distance[1] = mStationExit1.distanceTo(underBridge);

        // 다리 -> 심석중고
        distance[2] = underBridge.distanceTo(simSchool);

        // 심석 중고 -> 송라중
        distance[3] = simSchool.distanceTo(songSchool);

        // 송라중 -> 마석초
        distance[4] = songSchool.distanceTo(maSchoolFront);

        // 마석초 -> 마석고
        distance[5] = maSchoolFront.distanceTo(maHiSchool);

        // 마석고 -> 역 2번출
        distance[6] = maHiSchool.distanceTo(mStationExit2);

        // 역 2번출 -> 아파트
        distance[7] = mStationExit2.distanceTo(apartment);

        for(int i = 0; i<distance.length;i++)
            allDistance += distance[i];
        return allDistance;
    }

    public double getAllDistance(){
        for(int i = 0; i<distance.length;i++)
            allDistance += distance[i];
        return allDistance;
    }

    public double getDistance(int index){
        if(index>distance.length || index < 0)
            return -1;
        return distance[index];
    }

}


