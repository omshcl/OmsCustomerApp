package com.hcl.omsapplication;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MyLocationListener implements LocationListener {

    Context mContext;


    public MyLocationListener(Context context) {
        mContext = context;

    }
    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(
                mContext,
                "Location changed: Lat: " + location.getLatitude() + " Lng: "
                        + location.getLongitude(), Toast.LENGTH_SHORT).show();
        String longitude = "Longitude: " + location.getLongitude();
        Log.v("location", longitude);
        String latitude = "Latitude: " + location.getLatitude();
        Log.v("location", latitude);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
