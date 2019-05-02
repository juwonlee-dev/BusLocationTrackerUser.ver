package com.shuttlebus.user;

import android.app.AlertDialog;
import android.content.Context;
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


    private String lat;
    private String lon;
    private Button toRecycler_btn;
    private Button getData_btn;
    private SeekBar seekBar;
    private SeekBar seekBar2;

    public static TextView location_tv;
    private Context mContext = this;

//    private double latitude;
//    private double longitude;
    private double maxDisStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            DB_GetData.getData(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        location_tv = (TextView) findViewById(R.id.location_tv);
        toRecycler_btn = (Button) findViewById(R.id.toRecycler_btn);
        getData_btn = (Button) findViewById(R.id.getData_btn);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);

        toRecycler_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusActivity.class);
                startActivity(intent);

            }
        });


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


//        getData = new DB_GetData();

        getData_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location_tv.setText("로딩중");
                try {
                    DB_GetData.getData(mContext);
                }catch (Exception e){
                    e.printStackTrace();
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        lat = DB_GetData.getDBLatitude();
                        lon = DB_GetData.getDBLongitude();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (lat != null && lon != null) {
                                    location_tv.setText("lat: " + lat + "\nlon: " + lon);
//                                    Log.e("[UiThread]", getData.toString());
                                }
                            }
                        });
//                        Log.e("progress: " + progress, toString());
//                        Log.e("progressStation: " + progressStation, toString());
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


        Button cancelBtn = (Button) layout.findViewById(R.id.cancelBtn);
        Button endBtn = (Button) layout.findViewById(R.id.endBtn);


//        alertDialog.setPositiveButton("취소", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });

//        alertDialog.setNegativeButton("종료", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                finishAffinity();
//            }
//        });

        alertDialog.setTitle("종료");
        final AlertDialog alert = alertDialog.create();
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert.dismiss();
            }
        });

        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
//                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
//                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            }
        });
        alert.show();

//        backPressCloseHandler.onBackPressed();
//        super.onBackPressed();
    }


}
