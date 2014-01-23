package com.example.map_sale_android;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class NowWhereSearchActivity extends Activity implements OnClickListener{
    
    static final String WHERE_POST_URL = "http://map-sale.nobezawa.info/api/where";
    
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
        Button post_btn = (Button) findViewById(R.id.post_btn);
        post_btn.setOnClickListener(this);
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
        
        params.put("point[latitude]", String.valueOf(this.latitude));
        params.put("point[longitude]", String.valueOf(this.longitude));
        
        client.post(WHERE_POST_URL, params, new AsyncHttpResponseHandler(){
           
            @Override
            public void onSuccess(String response){
                Log.d("sucess", "通信成功");
                try{
                    JSONObject responseJSON = new JSONObject(response);
                    Log.d("response", responseJSON.toString());
                }catch(Exception e) {
                    Log.d("失敗しました", "aaaa");
                    Log.d("Error", e.getMessage());   
                }
            }
            
            @Override
            public void onFailure(Throwable e, String response){
                Log.d("失敗", response);
            }
            
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.post_btn){
            pointPost();
        }
        
    }
    


}
