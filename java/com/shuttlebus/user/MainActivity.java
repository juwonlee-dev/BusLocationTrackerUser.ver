package com.shuttlebus.user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Distance distance;

    private DB_GetData getData;
    private String lat;
    private String lon;
    private Button toRecycler_btn;
    private Button getData_btn;
    private SeekBar seekBar;
    private SeekBar seekBar2;

    private TextView tvApart;
    private TextView proStation;

    public static TextView location_tv;

//    private double latitude;
//    private double longitude;
    private double maxDis;
    private double maxDisStation;
    private double dis;
    private double disStation;
    private int progressApart;
    private int progress;
    private int progressStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        distance = new Distance();
        tvApart = (TextView) findViewById(R.id.progressGong);
        proStation = (TextView) findViewById(R.id.progressStation);
        location_tv = (TextView) findViewById(R.id.location_tv);
        toRecycler_btn = (Button) findViewById(R.id.toRecycler_btn);
        getData_btn = (Button) findViewById(R.id.getData_btn);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);


//        RoadData roadData = new RoadData(); 오류
//        roadData.getData();

        toRecycler_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusActivity.class);
                startActivity(intent);

            }
        });

        double d = distance.distanceToApart(37.660935,127.322490)*0.001;
        Log.e("maxDis: "+ d, toString());
        maxDis = (distance.distanceToApart(37.660935,127.322490)*0.001);
        maxDisStation = (distance.distanceToStation(37.652059,127.311561)*0.001);
        Log.e("maxDisStation: "+ maxDisStation,toString());

        seekBar.setMax(100);
        seekBar2.setMax(100);

        seekBar.setClickable(false);
        seekBar2.setClickable(false);


        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        seekBar2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        getData = new DB_GetData();

        getData_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location_tv.setText("로딩중");
                try {
                    getData.getData();
                }catch (Exception e){
                    e.printStackTrace();
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        double latApart = 37.652059;
                        double lonApart = 127.311561;
                        double latStation = 37.660935;
                        double lonStation = 127.311561;
                        maxDis = (distance.distanceToApart(latApart, lonApart) * 0.001);
                        Log.e("maxDis: "+ distance.distanceToApart(37.660935,127.322490), toString());
                        maxDisStation = (distance.distanceToStation(latStation,lonStation)*0.001);
                        Log.e("maxDisStation: "+ maxDisStation,toString());
                        lat = getData.getDBLatitude();
                        lon = getData.getDBLongitude();

                        if (lat != null && lon != null) {
                            double latitude = Double.parseDouble(lat);
                            double longitude = Double.parseDouble(lon);
//                            progressApart = busProgress.getProgress(latitude,longitude);
//                            Log.e("[Apart]: "+progressApart, toString());
                            dis = (distance.distanceToApart(latitude, longitude) * 0.001);
                            disStation = (distance.distanceToStation(latitude, longitude) * 0.001);
                            Log.e("distanceToApart: " + dis, toString());
                            Log.e("distanceToStation: " + disStation, toString());

                            if ((maxDis - dis) > 0) {
                                progress = (int) ((maxDis - dis) / maxDis * 100);
                            } else {
                                progress = 0;
//                                progress = maxProgress - (int) ((maxDis) / maxDis * 100);
                            }

                            if ((maxDisStation - disStation) > 0) {
                                progressStation = (int) ((maxDisStation - disStation) / maxDisStation * 100);
                            } else {
//                                progressStation = maxProgress - (int) ((maxDisStation) / maxDisStation * 100);
                                progressStation = 0;
                            }
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (lat != null && lon != null) {
                                    location_tv.setText("lat: " + lat + "\nlon: " + lon
                                            + "\n\n남은 거리: " + String.format("%.2fKm", dis));
                                    seekBar.setProgress(progress);
                                    seekBar2.setProgress(progressStation);
                                    tvApart.setText("진행률: " + progress + "%\n남은거리: " + String.format("%.2fKm", dis));
                                    proStation.setText("진행률: " + progressStation + "%\n남은거리: " + String.format("%.2fKm", disStation));
                                    Log.e("[UiThread]", getData.toString());
                                }
                            }
                        });
                        Log.e("progress: " + progress, toString());
                        Log.e("progressStation: " + progressStation, toString());
                    }
                }).start();
            }


        });


    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog, (ViewGroup) findViewById(R.id.popupView));

        alertDialog.setMessage("\n");
        alertDialog.setView(layout);


        alertDialog.setPositiveButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.setNegativeButton("종료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finishAffinity();
            }
        });

        alertDialog.setTitle("종료");
        final AlertDialog alert = alertDialog.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            }
        });
        alert.show();

//        backPressCloseHandler.onBackPressed();
//        super.onBackPressed();
    }


}
