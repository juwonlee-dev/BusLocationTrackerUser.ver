package com.shuttlebus.user;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;


import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
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
    private TextView noItemTextView;
    private FloatingActionButton fab;
    private BusRecyclerViewAdapter mAdapter;
    private RecyclerViewScrollListener scrollListener;

    private ArrayList<AbstractModel> modelList = new ArrayList<>();


    private Distance distanceTo = new Distance();
    private DB_GetData getData;
    private BusStation busStation;
    private BusProgress busProgress[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        // ButterKnife.bind(this);
        findViews();
        initToolbar("Bus ");
        setAdapter();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BusActivity.this, "ki mo", Toast.LENGTH_SHORT).show();

                try{
                    getData.getData();
                }catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for(int i= 0; i<modelList.size() -1 ;i++){
//                                    modelList.get(i).getSeekBar().setProgress();
                                }
                            }
                        });
                    }
                }).start();



            }
        });

    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        noItemTextView = (TextView) findViewById(R.id.noItemText);
    }

    public void initToolbar(String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);
    }


    @SuppressLint("RestrictedApi")
    private void setAdapter() {
        busStation = new BusStation();
        busStation.checkTime();
        char course = busStation.getCourse();
        busStation.setCourse(course);
        Log.e("course: "+busStation.getCourse(),toString());
        busStation.checkCourse();

        initToolbar("Bus "+busStation.getHour()+"시"+busStation.getMinute()+"분 출발");;
        Log.e("Bus "+busStation.getHour()+"시"+busStation.getMinute()+"분 출발",toString());

        if(busStation.station == null){
            noItemTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            fab.setVisibility(View.GONE);
        }
        else{
            noItemTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            fab.setVisibility(View.VISIBLE);
            for(int i=0; i<busStation.station.length;i++) {
                try {
//                Log.e("111]: "+ distanceTo.getDistanceTo(busStation.station[i], busStation.station[i + 1]),toString());
                    busStation.setMaxDis(distanceTo.getDistanceTo(busStation.getBusStation(i),busStation.getBusStation(i+1)), i);
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(i != busStation.station.length - 1){
                    modelList.add(new AbstractModel(busStation.getBusStation(i).getStationName(),"거리: "+String.format("%.2fKm",busStation.getMaxDis(i)*0.001)
                            ,busStation.getBusStation(i+1).getStationName()));
//                modelList.add(new AbstractModel())
                }
            }
        }


        mAdapter = new BusRecyclerViewAdapter(BusActivity.this, modelList);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.progressbus, (ViewGroup) findViewById(R.id.progressBus));

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mAdapter);

        scrollListener = new RecyclerViewScrollListener() {

            public void onEndOfScrollReached(RecyclerView rv) {

                Toast.makeText(BusActivity.this, "End of the RecyclerView reached. Do your pagination stuff here", Toast.LENGTH_SHORT).show();

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


}
