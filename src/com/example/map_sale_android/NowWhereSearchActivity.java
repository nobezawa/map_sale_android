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

public class NowWhereSearchActivity extends Activity implements LocationListener{
    
    private LocationManager locationManager;
    private TextView ido;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_where_search);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        ido = (TextView) findViewById(R.id.ido);
        ido.setText("set text");
        Log.d("onCreate", "now");
    }
    
    
    @Override
    public void onStart(){
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        Log.d("onStart", "now");
        super.onStart();
    }
    
    
    private void showLocation(Location location){
        ido.setText(Double.toString(location.getLatitude()));
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("onLocationChanged", "now");
        showLocation(location);
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
        
    }

}
