package com.example.map_sale_android;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class NowWhereSearchActivity extends Activity{
    private TextView latitude_text;
    private TextView longitude_text;
    
    GPSService gps;
    private double latitude;
    private double longitude;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_where_search);
        latitude_text = (TextView) findViewById(R.id.latitude_text);
        longitude_text = (TextView) findViewById(R.id.longitude_text);
        gps = new GPSService(this);
        if(gps.canGetLocation){
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show(); 
        }else{
            gps.showSettingsAlert();
        }
        
    }
    
    @Override
    public void onStart(){
    	latitude_text.setText(Double.toString(latitude));
    	longitude_text.setText(Double.toString(longitude));
    	super.onStart();
    }
    
    
    public void pointPost(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        
    }
    


}
