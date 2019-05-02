package com.shuttlebus.user;



public interface Station {


    //마석역 '37.652768', '127.311827'
    Location maseokStation = new Location(37.652768, 127.311827);

    //청평역  '37.735408', '127.426584'
    Location cheongpyeongStation = new Location(37.735408, 127.426584);

    //가평역 '37.814430', '127.510682'
    Location GapyeongStation = new Location(37.814430, 127.510682);

    //강촌역 '37.805635', '127.634159'
    Location KangchonStation = new Location(37.805635, 127.634159);

    //춘청역 '37.884912', '127.716894'
    Location ChuncheonStation = new Location(37.884912, 127.716894);

    Location apart = new Location(37.6601549, 127.3225263);

    //37.886273, 127.735640
    double gongLat = 37.886273;
    double gongLon = 127.735640;
    // 아파트 37.6601549 127.3225263
    // 수정필요
    double apartmentLat = 37.660935;
    double apartmentLon = 127.322490;

    // 마석역 1번출구 37.652059, 127.311561
    double mStationExit1Lat = 37.652059;
    double mStationExit1Lon = 127.311561;

    // 마석역 2번출구 37.652995, 127.311207
    double mStationExit2Lat = 37.652995;
    double mStationExit2Lon = 127.311207;

    // 다리및 37.652982, 127.306714
    double underBridgeLat = 37.652982;
    double underBridgeLon = 127.306714;

    // 심석중고등 37.656376, 127.305985
    double simSchoolLat = 37.656376;
    double simSchoolLon = 127.305985;

    // 송라초중 37.655009, 127.302257
    double songSchoolLat = 37.655009;
    double songSchoolLon = 127.302257;

    // 화도읍사무소
    double maOfficeLat = 37.657519;
    double maOfficeLon = 127.301633;

    // 마석초중 정문 37.648726, 127.305006
    double maSchoolFrontLat = 37.648726;
    double maSchoolFrontLon = 127.305006;

    // 마석초중 후문 37.649843, 127.305032
    double maSchoolBackLat = 37.649853;
    double maSchoolBackLon = 127.304930;

    // 마석고 37.644891, 127.300135
    double maHiSchoolLat = 37.644891;
    double maHiSchoolLon = 127.300135;
}
