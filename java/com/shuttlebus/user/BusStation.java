package com.shuttlebus.user;

import java.util.Calendar;

public class BusStation implements Station{
    private double latitude;
    private double longitude;
    private String stationName;
    private char course;
    private float distance;

    private int hour;
    private int minute;



    private double maxDis[];
    public BusStation station[];
    public static int length;



    public BusStation(){

    }


    public BusStation(String stationName,double latitude, double longitude){
        this.stationName = stationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void checkCourse(){
        /*
         * 아파트 -> 1번출구 -> 다리및 -> 2번출구 -> 아파트
         */
        if(course == 'A'){
            BusStation[] station = {
                    new BusStation("아파트" ,apartmentLat, apartmentLon),
                    new BusStation("마석역 1번출구",mStationExit1Lat,mStationExit1Lon),
                    new BusStation("다리",underBridgeLat, underBridgeLon),
                    new BusStation("마석역 2번출구" ,mStationExit2Lat,mStationExit2Lon),
                    new BusStation("아파트",apartmentLat,apartmentLon) };
            length = station.length;
            this.station = station;
        }

        /*
         * 아파트  -> 1번출구 -> 다리및 -> 심석고 -> 송라중 -> 마석초 뒷문 -> 2번출구 -> 아파트
         */
        else if(course == 'B'){

            BusStation station[] = {
                    new BusStation("아파트" ,apartmentLat, apartmentLon),
                    new BusStation("마석역 1번출구",mStationExit1Lat,mStationExit1Lon),
                    new BusStation("다리",underBridgeLat, underBridgeLon),
                    new BusStation("심석고", simSchoolLat,simSchoolLon),
                    new BusStation("송라중", songSchoolLat,songSchoolLon),
                    new BusStation("마석초 뒷문", maSchoolBackLat,maSchoolBackLon),
                    new BusStation("마석역 2번출구", mStationExit2Lat,mStationExit2Lon),
                    new BusStation("아파트", apartmentLat,apartmentLon) };
            length = station.length;
            this.station = station;

        }

        /*
         * 아파트  -> 1번출구 -> 다리및 -> 심석고 -> 읍사무소 -> 마석초(뒷문) -> 2번출구 -> 아파트
         */
        else if(course == 'C'){
            BusStation station[] = {
                    new BusStation("아파트" ,apartmentLat, apartmentLon),
                    new BusStation("마석역 1번출구",mStationExit1Lat,mStationExit1Lon),
                    new BusStation("다리",underBridgeLat, underBridgeLon),
                    new BusStation("심석고", simSchoolLat,simSchoolLon),
                    new BusStation("읍사무소", maOfficeLat, maOfficeLon),
                    new BusStation("마석초 뒷문", maSchoolBackLat,maSchoolBackLon),
                    new BusStation("마석역 2번출구", mStationExit2Lat,mStationExit2Lon),
                    new BusStation("아파트", apartmentLat,apartmentLon)
            };
            length = station.length;
            this.station = station;
        }

        /*
         * 아파트  -> 1번출구 -> 다리및 -> 심석고 -> 송라중 -> 마석초(앞 문) -> 마석고 ->  2번출구 -> 아파트
         */
        else if(course == 'D'){
            BusStation station[] = {
                    new BusStation("아파트" ,apartmentLat, apartmentLon),
                    new BusStation("마석역 1번출구",mStationExit1Lat,mStationExit1Lon),
                    new BusStation("다리",underBridgeLat, underBridgeLon),
                    new BusStation("심석고", simSchoolLat,simSchoolLon),
                    new BusStation("송라중", songSchoolLat,songSchoolLon),
                    new BusStation("마석초 앞문", maSchoolFrontLat,maSchoolFrontLon),
                    new BusStation("마석고" , maHiSchoolLat, maHiSchoolLon),
                    new BusStation("마석역 2번출구", mStationExit2Lat,mStationExit2Lon),
                    new BusStation("아파트", apartmentLat,apartmentLon)
            };
            length = station.length;
            this.station = station;
        }
        maxDis  = new double[length];
    }

    public void checkTime(){
        Scheduler scheduler = new Scheduler();
        Calendar cal = Calendar.getInstance();
        scheduler.makeTimeTable();
        int curhour = cal.get(Calendar.HOUR_OF_DAY);
        int curminute = cal.get(Calendar.MINUTE);

        for(int i = 0 ; i<scheduler.timeTable.length;i++){
            /*
             * 시간 확인 8 10
             */
            if(scheduler.timeTable[i].getHour() == curhour) {
                /*
                 * 분 확인
                 */
                //                       10,50   20
                if(scheduler.timeTable[i].getMinute() <= curminute){
                    hour = scheduler.timeTable[i].getHour();
                    if(scheduler.timeTable[i+1].getHour() == curhour){
                        if(scheduler.timeTable[i+1].getMinute() <= curminute){
                            minute = scheduler.timeTable[i+1].getMinute();
                            course = scheduler.timeTable[i+1].getCourse();
                        }
                        minute = scheduler.timeTable[i].getMinute();
                        course = scheduler.timeTable[i].getCourse();
                    }
                }
            }
        }

    }

    public void setCourse(char course){
        this.course = course;
    }

    public char getCourse(){
        return course;
    }

    public String getStationName(){
        return stationName;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public double getMaxDis(int index){return maxDis[index];}
    public void setMaxDis(double maxDis, int index){this.maxDis[index] = maxDis;}

    public int getHour(){
        return hour;
    }
    public int getMinute(){
        return minute;
    }

    public BusStation getBusStation(int index){
        return station[index];
    }

    public int getStationLength(){
        return station.length;
    }



}
