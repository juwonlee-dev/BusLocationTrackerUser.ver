package com.shuttlebus.user;

public class Scheduler {

    TimeTable timeTable[];

    public void makeTimeTable(){
        TimeTable[] timeTable = {
                new TimeTable(6,45),
                new TimeTable(7,00),
                new TimeTable(7,20),
                new TimeTable(7,40),
                new TimeTable(8,00),
                new TimeTable(8,20,'D'),
                new TimeTable(9,10,'C'),
                new TimeTable(10,30),
                new TimeTable(10, 50),
                new TimeTable(11,10),
                new TimeTable(11,50),
                new TimeTable(12,10),
                new TimeTable(14,10),
                new TimeTable(15,10,'C'),
                new TimeTable(16,00,'B'),
                new TimeTable(16,40),
                new TimeTable(17,30),
                new TimeTable(18,00),
                new TimeTable(18,50),
                new TimeTable(19,20),
                new TimeTable(19,40),
                new TimeTable(20,00),
                new TimeTable(20,20),
        };
        this.timeTable = timeTable;
    }


    public TimeTable[] getTimeTable(){
        return timeTable;
    }


}

