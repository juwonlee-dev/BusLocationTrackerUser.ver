package com.shuttlebus.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.animation.MotionSpec;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;


import android.support.design.widget.FloatingActionButton;


public class BusActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;

    //@BindView(R.id.toolbar)
    //Toolbar toolbar;
    private Toolbar toolbar;

    //@BindView(R.id.fab)
    //FloatingActionButton fab;
    private static TextView busTimeTextView;
    private TextView noItemTextView;
    private FloatingActionButton fab;
    private BusRecyclerViewAdapter mAdapter;
    private RecyclerViewScrollListener scrollListener;
    private EditText courseEdit;

    private ArrayList<AbstractModel> modelList = new ArrayList<>();

    private BusProgress busProgress[];
    private static BusStation[] busStations;
    private static double[] curDis;
    private static double[] maxDis;
    private static int[] progress;
    private Context mContext = this;

    private static Scheduler sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);


        // ButterKnife.bind(this);
        findViews();
        initToolbar("");
        setAdapter();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v,"로딩중",Snackbar.LENGTH_SHORT).show();
                modelList.clear();
                try {
                    DB_GetData.getData(mContext);
                }catch (NullPointerException e) {
                    Log.e("BusActivityNull",e.getMessage());
                }catch (Exception e) {
                    Log.e("BusActivity",e.getMessage());
                }

                try {
//                    processing();
//                    Thread.sleep(100);
                    setAdapter();
                }catch (ArrayIndexOutOfBoundsException e){
                    busTimeTextView.setText("ArrayIndexOutOfBoundsException!!!" + e.getMessage());
                } catch (NullPointerException e){
                    busTimeTextView.setText("NullPointerException!!!" + e.getMessage());
                }catch (Exception e){
                    busTimeTextView.setText("Exception!!!" + e.getMessage());

                }
            }
        });

    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        noItemTextView = (TextView) findViewById(R.id.noItemText);
        busTimeTextView = (TextView) findViewById(R.id.busTimeText);
        courseEdit = (EditText) findViewById(R.id.courseEdit);
    }

    public void initToolbar(String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setTitle(title);

    }



    @SuppressLint("RestrictedApi")
    private void setAdapter() throws ArrayIndexOutOfBoundsException, NullPointerException{

        sd = new Scheduler();
//        BusStation.checkTime(sd);
        String strCourse = courseEdit.getText().toString();

        try {
            char charCourse = strCourse.charAt(0);
            if(charCourse >= 'a' && charCourse <= 'z')
                charCourse -= 32;
            sd.setCurrentCourse(charCourse);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
//        if(sd.getCurrentCourse() == 'E'){
//            // No course
//            Log.e("코스 없음", ""+sd.getCurrentCourse());
//        }



        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);

        try {
            if(sd.getCurrentCourse() == 'E') throw new Exception("코스 없음");
            // 현재 진행중인 코스가 있으면 구현
            else busStations = BusStation.checkCourse(sd.getCurrentCourse());

        } catch (Exception e) {
            System.err.println("오류발생: " + e.getMessage());
        }

        String today;
        if (month <= 9)
            today = year + "0" + month;
        else {
            today = year + "" + month;
        }

        if (day <= 9) {
            today = today + "0" + day;
        } else {
            today += day;
        }


        // 공휴일 판단.
        if(Holiday.isHoliday(today)){
            noItemTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            noItemTextView.setText("공휴일은 운행하지 않습니다.");

        }
        else {
            /*
             * 버스 객체가 없다면.
             */
            if (busStations == null) {
                noItemTextView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
//                busTimeTextView.setText("다음 운행 시간: ");
                /*
                 * busStation 의 마지막 역까지 도착햇다면
                 */
            } else if(busStations[busStations.length-1].getBusArrived()){
                // 객체 다시 생성
                busStations = BusStation.checkCourse(sd.getCurrentCourse());
            }
            else {
                try {
                    processing();
                } catch (NullPointerException e) {
                    busTimeTextView.setText("Processing NULL ERROR");
                }

                noItemTextView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);


                if (DB_GetData.getDBLatitude() == null) {
                    busTimeTextView.setText("DB ERROR");

                } else {
                    busTimeTextView.setText("운행 시작 시간: " + BusStation.getHour() + "시 "
                            + BusStation.getMinute() + "분\n"
                            + "총 거리: " + Distance.allDistance(busStations));

                }

                for (int i = 0; i < busStations.length; i++) {
                    if (i != busStations.length - 1) {
                        modelList.add(new AbstractModel(busStations[i].getStationName()
                                , "거리: " + String.format("%.2fKm", maxDis[i]) + "\n진행률: " + progress[i]
                                , busStations[i + 1].getStationName()
                                , progress[i]
                                ,DB_GetData.getIsArrived(i))
                        );

                    }
                }
            }

        }
        mAdapter = new BusRecyclerViewAdapter(BusActivity.this, modelList);


        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mAdapter);

        scrollListener = new RecyclerViewScrollListener() {

            public void onEndOfScrollReached(RecyclerView rv) {

//                Toast.makeText(BusActivity.this, "End of the RecyclerView reached. Do your pagination stuff here", Toast.LENGTH_SHORT).show();

                scrollListener.disableScrollListener();
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
          /*
             Note: The below two methods should be used wisely to handle the pagination enable and disable states based on the use case.
                     1. scrollListener.disableScrollListener(); - Should be called to disable the scroll state.
                     2. scrollListener.enableScrollListener(); - Should be called to enable the scroll state.
          */

        mAdapter.SetOnItemClickListener(new BusRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, AbstractModel model) {
                //handle item click events here
                Toast.makeText(BusActivity.this,  "[" + position+ "]Hey " + model.getStart(), Toast.LENGTH_SHORT).show();

            }
        });


    }
    static void processing() throws NullPointerException{


        curDis = new double[busStations.length - 1];
        maxDis = new double[busStations.length - 1];
        progress = new int[busStations.length - 1];


        /*
         * 현재 위치에서 부터의 거리
         */
        Location currentLocation = new Location(DB_GetData.getLatitude(), DB_GetData.getLongitude());
        for (int i = 0; i < curDis.length; i++) {
            curDis[i] = Distance.distance(currentLocation, busStations[i+1]);
            Log.e("curDis["+i+"]: ", Double.toString(curDis[i]));
        }
        /*
         * max
         */
        for (int i = 0; i < busStations.length - 1; i++) {
            maxDis[i] = Distance.distance(busStations[i], busStations[i + 1]);
        }

        // Progress
        for (int i = 0; i < progress.length; i++) {
            progress[i] = BusProgress.getProgress(maxDis[i], curDis[i]);
            Log.e("progress["+i+"]:", Double.toString( progress[i]) );
        }

        busStations[0].setIsBusArrived(true);
        for (int i = 0; i < progress.length; i++) {
            // 전 정류장에 먼저 도착햇는지 여부 확인
            if (i != 0 && !busStations[i].getIsBusArrived()) {
                continue;
            }
            else busCheck(busStations[i + 1], progress[i]);

        }

    }
    static void busCheck(BusStation busStation, int progress) {
//		if(busStation.getIsBusArrived()) {
//			busStation.setIsBusArrived(true);
//		}
        if (progress >= 96) {
            busStation.setIsBusArrived(true);
        }
    }

}
