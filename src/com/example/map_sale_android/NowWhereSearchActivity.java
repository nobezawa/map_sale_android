package com.example.map_sale_android;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class NowWhereSearchActivity extends Activity implements OnClickListener{
    
    static final String WHERE_POST_URL = "http://map-sale.nobezawa.info/api/where";
    
    private TextView latitude_text;
    private TextView longitude_text;
    private TextView name_text;
    private TextView address_text;
    private TextView event_title_text;
    private TextView summary_text;
    
    
    public JSONObject result;
    

    GPSService gps;
    private double latitude;
    private double longitude;
    
    private Button map_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_where_search);
        latitude_text = (TextView) findViewById(R.id.latitude_text);
        longitude_text = (TextView) findViewById(R.id.longitude_text);
        name_text = (TextView) findViewById(R.id.name_text);
        address_text = (TextView) findViewById(R.id.address_text);
        event_title_text = (TextView) findViewById(R.id.event_title_text);
        summary_text = (TextView) findViewById(R.id.summary_text);
        
        Button post_btn = (Button) findViewById(R.id.post_btn);
        map_btn = (Button) findViewById(R.id.map_button);
        map_btn.setVisibility(View.INVISIBLE);
        post_btn.setOnClickListener(this);
        map_btn.setOnClickListener(this);
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
                    //JSONArray resultArray = responseJSON.getJSONArray("result");
                    result = responseJSON.getJSONObject("result");
                    //for (int i = 0; i < resultArray.length(); i++) {
                    //    result = resultArray.getJSONObject(i);
                    //    Log.d("JSONSampleActivity", result.getString("name"));
                    //}
                    name_text.setText(result.getString("name"));
                    address_text.setText(result.getString("address"));
                    event_title_text.setText(result.getString("title"));
                    summary_text.setText(result.getString("summary"));
                    map_btn.setVisibility(View.VISIBLE);                  
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
        }else if(id == R.id.map_button){
            try{
                String uri = "geo:0,0?q=" + result.getString("address");
                Intent map_intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(map_intent);
            }catch (Exception e){
                
            }
            
        }
        
    }
    


}
