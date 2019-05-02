package com.shuttlebus.user;


import java.util.Calendar;

public class BusStation implements Station{
    private double latitude;
    private double longitude;
    private String stationName;
    private boolean isBusArrived; // 버스 도착여부
    
    private static int hour;
    private static int minute;

    public BusStation(){

    }
    public BusStation(String stationName, Location location) {
        this(stationName, location.getLatitude(), location.getLongitude());
    }


    public BusStation(String stationName,double latitude, double longitude){
        this.stationName = stationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isBusArrived = false;
    }

    public static BusStation[] checkCourse(char course){
        /*
         * 아파트 -> 1번출구 -> 다리및 -> 2번출구 -> 아파트
         */
        if(course == 'A'){
            BusStation[] stations = {
                    new BusStation("아파트" ,apartmentLat, apartmentLon),
                    new BusStation("마석역 1번출구",mStationExit1Lat,mStationExit1Lon),
                    new BusStation("다리",underBridgeLat, underBridgeLon),
                    new BusStation("마석역 2번출구" ,mStationExit2Lat,mStationExit2Lon),
                    new BusStation("아파트",apartmentLat,apartmentLon) };
            return stations;
        }

        /*
         * 아파트  -> 1번출구 -> 다리및 -> 심석고 -> 송라중 -> 마석초 뒷문 -> 2번출구 -> 아파트
         */
        else if(course == 'B'){

            BusStation stations[] = {
                    new BusStation("아파트" ,apartmentLat, apartmentLon),
                    new BusStation("마석역 1번출구",mStationExit1Lat,mStationExit1Lon),
                    new BusStation("다리",underBridgeLat, underBridgeLon),
                    new BusStation("심석고", simSchoolLat,simSchoolLon),
                    new BusStation("송라중", songSchoolLat,songSchoolLon),
                    new BusStation("마석초 뒷문", maSchoolBackLat,maSchoolBackLon),
                    new BusStation("마석역 2번출구", mStationExit2Lat,mStationExit2Lon),
                    new BusStation("아파트", apartmentLat,apartmentLon) };

            return stations;

        }

        /*
         * 아파트  -> 1번출구 -> 다리및 -> 심석고 -> 읍사무소 -> 마석초(뒷문) -> 2번출구 -> 아파트
         */
        else if(course == 'C'){
            BusStation stations[] = {
                    new BusStation("아파트" ,apartmentLat, apartmentLon),
                    new BusStation("마석역 1번출구",mStationExit1Lat,mStationExit1Lon),
                    new BusStation("다리",underBridgeLat, underBridgeLon),
                    new BusStation("심석고", simSchoolLat,simSchoolLon),
                    new BusStation("읍사무소", maOfficeLat, maOfficeLon),
                    new BusStation("마석초 뒷문", maSchoolBackLat,maSchoolBackLon),
                    new BusStation("마석역 2번출구", mStationExit2Lat,mStationExit2Lon),
                    new BusStation("아파트", apartmentLat,apartmentLon)
            };
            return stations;
        }

        /*
         * 아파트  -> 1번출구 -> 다리및 -> 심석고 -> 송라중 -> 마석초(앞 문) -> 마석고 ->  2번출구 -> 아파트
         */
        else if(course == 'D'){
            BusStation stations[] = {
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
            return stations;
        } else if (course == 'F') {
            BusStation station[] = { new BusStation("마석역", maseokStation), new BusStation("청평역", cheongpyeongStation),
                    new BusStation("가평역", GapyeongStation), new BusStation("강촌역", KangchonStation),
                    new BusStation("춘천역", ChuncheonStation) };
            return station;
        } else if (course == 'G') {
            BusStation station[] = { new BusStation("춘천역", ChuncheonStation), new BusStation("강촌역", KangchonStation),
                    new BusStation("가평역", GapyeongStation), new BusStation("청평역", cheongpyeongStation),
                    new BusStation("마석역", maseokStation)

            };
            return station;
        }

        else
            return null;
    }



    public static void checkTime(Scheduler scheduler){
        Calendar cal = Calendar.getInstance();

        int curhour = cal.get(Calendar.HOUR_OF_DAY);
        int curminute = cal.get(Calendar.MINUTE);

        for (int i = 0; i < scheduler.getTimeTable().length; i++) {
            /*
             * 시간 확인
             */
            if (scheduler.getTimeTable()[i].getHour() == curhour) {
                // Log.e("hour:"+curhour,toString());
                /*
                 * 분 확인
                 */
                if (scheduler.getTimeTable()[i].getMinute() <= curminute) {
                    hour = scheduler.getTimeTable()[i].getHour();
                    // Log.e("hour2: "+hour,toString());

                    if (scheduler.getTimeTable()[i + 1].getHour() == curhour
                            && scheduler.getTimeTable()[i + 1].getMinute() <= curminute) {
                        minute = scheduler.getTimeTable()[i + 1].getMinute();
                        scheduler.setCurrentCourse(scheduler.getTimeTable()[i].getCourse());

                        return;
                    }
                    if (scheduler.getTimeTable()[i].getMinute() <= curminute) {
                        minute = scheduler.getTimeTable()[i].getMinute();
                        scheduler.setCurrentCourse(scheduler.getTimeTable()[i].getCourse());
                    }
//					System.err.println("1111111");
//					System.err.println(scheduler.getCurrentCourse());
                }
            }
        }

    }
    public boolean getIsBusArrived() {
        return isBusArrived;
    }

    public void setIsBusArrived(boolean is) {
        this.isBusArrived = is;
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


    public static int getHour(){
        return hour;
    }
    public static int getMinute(){
        return minute;
    }

    public boolean getBusArrived(){
        return isBusArrived;
    }
}
