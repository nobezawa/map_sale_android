package com.example.map_sale_android;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NowWhereSearchActivity extends Activity{
    private TextView ido;
    
    GPSService gps;
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_where_search);
        ido = (TextView) findViewById(R.id.ido);
        gps = new GPSService(this);
        if(gps.canGetLocation){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show(); 
        }else{
            gps.showSettingsAlert();
        }
        ido.setText("set text");
    }
    


}
