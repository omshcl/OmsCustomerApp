package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.config.LocationAccuracy;
import io.nlopez.smartlocation.location.config.LocationParams;
public class LocationActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        final TextView textView6=(TextView)findViewById(R.id.textView6);
        final TextView textView7=(TextView)findViewById(R.id.textView7);
        long mLocTrackingInterval = 1000 * 5;
        float trackingDistance = 0;
        LocationAccuracy trackingAccuracy = LocationAccuracy.HIGH;

        LocationParams.Builder builder = new LocationParams.Builder()
                .setAccuracy(trackingAccuracy)
                .setDistance(trackingDistance)
                .setInterval(mLocTrackingInterval);

        SmartLocation.with(this)
                .location()
                .continuous()
                .config(builder.build())
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        textView6.setText("Latitude"+" "+location.getLatitude());
                        textView7.setText("Longitude"+" "+location.getLongitude());
                        System.out.println(location.getLatitude()+" "+location.getLongitude());
                        double latitude=location.getLatitude();
                        double longitude=location.getLongitude();
                        double l1=33.0557;
                        double l2=-96.8291;
                        final double _eQuatorialEarthRadius = 6378.1370D;
                        final double _d2r = (Math.PI / 180D);
                        double dlong = (l2 - longitude) * _d2r;
                        double dlat = (l1 - latitude) * _d2r;
                        double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(latitude * _d2r) * Math.cos(l1 * _d2r)
                                * Math.pow(Math.sin(dlong / 2D), 2D);
                        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
                        double d = _eQuatorialEarthRadius * c;

                        if(d<0.5)
                        {
                            Toast.makeText(getApplicationContext(),"Customer arrived for pickup",Toast.LENGTH_LONG).show();

                        }


                    }
                });

    }

}


