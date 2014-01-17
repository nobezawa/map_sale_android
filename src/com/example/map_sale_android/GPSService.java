package com.example.map_sale_android;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class GPSService extends Service implements LocationListener{
    
    private final Context mContext;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    
    private LocationManager locationManager;
    private Location location;
    double latitude; // latitude
    double longitude; // longitude
    
    
    boolean gpsFlag = false;
    boolean canGetLocation = false;
    
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
    
    public GPSService(Context context){
        this.mContext = context;
        getLocation();
    }
    
    public Location getLocation(){
        try{
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
            Criteria locationCritera = new Criteria();
            String providerName = locationManager.getBestProvider(locationCritera,
                    true);
            Log.d("best", providerName);
            gpsFlag = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            if(gpsFlag){
                this.canGetLocation = true;
                if (location == null) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            0,
                            0, this);
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(providerName);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                } 
            }
        }catch(Exception e){
            
        }
        
        return location;
    }
    
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }
        return latitude;
    }
    
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }
        return longitude;
    }
    
    public boolean canGetLocation() {
        return this.canGetLocation;
    }
    
    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(GPSService.this);
        }       
    }
    
    
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("GPS is settings");
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            }
        });
        alertDialog.show();
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
