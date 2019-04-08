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

public class DB_GetData {

    // DB Connection
    private static final String address = "http://218.159.255.118/get_php_conection.php";  //주소 바뀜
    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "str_user_id";
    private static final String TAG_TIME = "str_datetime";
    private static final String TAG_LAT = "str_latitude";
    private static final String TAG_LON = "str_longitude";

    // json
    private String myJSON;
    private JSONArray locations = null;

    // value getting at DB
    private String id;
    private String time;
    private String latitude;
    private String longitude;


    protected void getDBData() {
        Log.e("myJson: "+myJSON, toString());
        if(myJSON != null){
            try {
                JSONObject jsonObj = new JSONObject(myJSON);
                locations = jsonObj.getJSONArray(TAG_RESULTS);
                JSONObject c = locations.getJSONObject(0);

                id = c.getString(TAG_ID);
                time = c.getString(TAG_TIME);
                latitude = c.getString(TAG_LAT);
                longitude = c.getString(TAG_LON);
                Log.e("lat: " + latitude + ", lon: " + longitude, jsonObj.toString());

            } catch (JSONException e) {
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


    public String getDBId(){
        return this.id;
    }

    public String getDBTime(){
        return this.time;
    }

    public String getDBLatitude(){
        return this.latitude;
    }

    public String getDBLongitude(){
        return this.longitude;
    }
}