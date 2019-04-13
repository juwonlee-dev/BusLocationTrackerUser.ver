package com.shuttlebus.user;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RoadData {
    // DB Connection
    private static final String address = "http://openapi.its.go.kr/api/NTrafficInfo?key=1552962283383&ReqType=2&MinX=127.290000&MaxX=127.330000&MinY=37.640000&MaxY=37.660000";  //주소 바뀜
    private static final String TAG_DATA="data";
    private static final String TAG_SPEED="avgspeed";


    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "str_user_id";
    private static final String TAG_TIME = "str_datetime";
    private static final String TAG_LAT = "str_latitude";
    private static final String TAG_LON = "str_longitude";

    // json
    private String myJSON;
    private JSONArray speed = null;

    // value getting at DB
    private String avgSpeed;
    private String id;
    private String time;
    private String latitude;
    private String longitude;


    protected void getDBData() {
        Log.e("myJson: "+myJSON, toString());
        if(myJSON != null){
            try {
                JSONObject jsonObj = new JSONObject(myJSON);
                speed = jsonObj.getJSONArray(TAG_DATA);
                JSONObject c = speed.getJSONObject(0);

                for(int i=0; i<speed.length();i++){
                    avgSpeed = c.getString(TAG_SPEED);
                }
                Log.e("avgSpeed: " + avgSpeed , jsonObj.toString());

            } catch (JSONException e) {
                Log.e("json error: "+ e, toString());
                e.printStackTrace();
            }
        }

    }

    public void getData() {
        class GetData extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... strings) {
                String uri = strings[0];
                BufferedReader bufferedReader = null;

                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                Log.e("myJson: "+myJSON, toString());
                getDBData();
            }
        }

        GetData g = new GetData();
        g.execute(address);
    }

}
