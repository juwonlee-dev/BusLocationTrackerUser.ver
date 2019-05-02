package com.shuttlebus.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DB_GetData {

    // DB Connection
    private static final String busInfoAddress = "http://getDataAddress";
    private static final String TAG_ARRIVED = "arrived";
    private static final String TAG_IS_ARRIVED = "isArrived";
    private static final String TAG_LATLON = "LatLon";
    private static final String TAG_ID = "str_user_id";
    private static final String TAG_TIME = "str_datetime";
    private static final String TAG_LAT = "str_latitude";
    private static final String TAG_LON = "str_longitude";

    // json
    private static String myJSON;
    private static JSONArray locations = null;
    private static JSONArray stationArrived = null;


    // value getting at DB
    private static String id;
    private static String time;
    private static String latitude;
    private static String longitude;
    private static boolean[] isArrived;


    private static void getLatLonData() {
//        Log.e("myJson[getDBdata]: ", myJSON);
        if(myJSON != null){
            try {
                JSONObject jsonObj = new JSONObject(myJSON);
                locations = jsonObj.getJSONArray(TAG_LATLON);
                JSONObject c = locations.getJSONObject(0);

                id = c.getString(TAG_ID);
                time = c.getString(TAG_TIME);
                latitude = c.getString(TAG_LAT);
                longitude = c.getString(TAG_LON);
//                Log.e("lat: " + latitude + ", lon: " + longitude, jsonObj.toString());
            } catch (JSONException e) {
                Log.e("JSONException:" , e.getMessage());
            }
        }

    }
    private static void getArrivedData() {
        if(myJSON != null){
            try {
                JSONObject jsonObj = new JSONObject(myJSON);
                stationArrived = jsonObj.getJSONArray(TAG_ARRIVED);

                isArrived = new boolean[stationArrived.length()];
                for(int i = 0; i < stationArrived.length(); i++) {
                    JSONObject c = stationArrived.getJSONObject(i);
                    Log.e("isArrived: ",c.getString(TAG_IS_ARRIVED));
                    isArrived[i] = Boolean.parseBoolean(c.getString(TAG_IS_ARRIVED));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    public static void getData(final Context mContext) throws NullPointerException {
        class GetLocationData extends AsyncTask<String, Void, String> {

            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                // UI 작업을 수행하는 부분
                super.onPreExecute();
                progressDialog = progressDialog.show(mContext,"Please Wait","잠시만 기달려 주세요\n데이터를 불러오고 있습니다.",true,true);
//                Log.e("progressDialog:",progressDialog.toString());
            }
            @Override
            protected String doInBackground(String... strings) {
                String serverUrl = strings[0];
                BufferedReader bufferedReader = null;

                try {
                    URL url = new URL(serverUrl);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setReadTimeout(1000);
                    con.setConnectTimeout(1000);
                    con.setDoInput(true);
                    con.connect();

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

            // 모든 작업이 끝난 후 처리되는 메소드
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                myJSON = result;
                Log.e("myJSON: ",myJSON);
                getLatLonData();
                getArrivedData();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();

            }
        }
        GetLocationData g = new GetLocationData();
        g.execute(busInfoAddress);

    }



    public static double getLatitude() {
        return Double.parseDouble(latitude);
    }

    public static double getLongitude() {
        return Double.parseDouble(longitude);
    }


    public String getDBId(){
        return this.id;
    }

    public String getDBTime(){
        return this.time;
    }

    public static String getDBLatitude(){
        return latitude;
    }

    public static String getDBLongitude(){
        return longitude;
    }

    public static boolean getIsArrived(int index) {
        return isArrived[index];
    }
}