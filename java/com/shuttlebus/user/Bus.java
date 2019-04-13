package com.shuttlebus.user;

import java.util.Calendar;

public class Bus {

    Distance[] distanceToArray;
    Distance distanceTo = new Distance();

    private BusStation busStation = new BusStation();// length = 5
    private BusProgress[] busProgress;
    private double distance[] = new double[busStation.length];

    public void courseDistanceToA(){

        for(int i =0;i<distance.length;i++){
            distance[i] = distanceTo.getDistanceTo((BusStation)busStation.station[i]
                    ,(BusStation)busStation.station[i+1]);

        }
    }

//    public void busRun(){
//        distanceToArray = new Distance[busStation.length];
//        busProgress = new BusProgress[busStation.length];
//
//        if(busStation.getCourse() == 'A')
//            courseDistanceToA();
//        for(int i = 0; i<busProgress.length;i++){
//            busProgress[i].setMaxDis(distance[i]);
//        }
//    }

//    public char busRun(){
//        Scheduler scheduler = new Scheduler();
//        Calendar cal = Calendar.getInstance();
//        scheduler.makeTimeTable();
//        for(int i = 0 ; i<scheduler.timeTable.length;i++){
//            /*
//             * 시간 확인
//             */
//            if(scheduler.timeTable[i].getHour() == cal.get(Calendar.HOUR_OF_DAY)){
//                /*
//                 * 분 확인
//                 */
//                //                       20,40   50
//                if(scheduler.timeTable[i].getMinute() <= cal.get(Calendar.MINUTE)){
//
//                    if(scheduler.timeTable[i+1].getHour() == cal.get(Calendar.HOUR_OF_DAY)){
//                        if(scheduler.timeTable[i+1].getMinute() <= cal.get(Calendar.MINUTE)){
//                            return scheduler.timeTable[i+1].getCourse();
//                        }
//                        return scheduler.timeTable[i].getCourse();
//                    }
//                }
//            }
//        }
//        return '1';
//    }
}
