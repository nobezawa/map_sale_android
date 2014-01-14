package com.example.map_sale_android;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;

public class GPSService extends Service implements LocationListener{
    
    private final Context mContext;
    Location location;
    
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    
    public GPSService(Context context){
        this.mContext = context;
        getLocation();
    }
    
    public Location getLocation(){
        return location;
    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
        
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
