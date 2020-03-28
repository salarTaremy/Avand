package com.smartVisitor.avand.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smartVisitor.avand.R;

import java.util.Calendar;
import java.util.Date;

public class TrackingActivity extends AppCompatActivity {

    private Button buttonLocation;
    private TextView TV_Location;

    /* GPS Constant Permission */
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 12;

    /* Position */
    private static final long MINIMUM_TIME =  10000;  // 10s
    private static final float MINIMUM_DISTANCE = 1; // 50m

    /* GPS */
    private LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        initView();
        buttonLocation_onClick(TV_Location);
    }
    private void initView() {
        buttonLocation = findViewById(R.id.buttonShowLocation);
        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLocation_onClick(view);
            }
        });
        this.TV_Location = findViewById(R.id.Tv_Location);
    }



    private void buttonLocation_onClick(View view) {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MINIMUM_TIME, MINIMUM_DISTANCE, locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_ACCESS_COARSE_LOCATION);
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_ACCESS_FINE_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "ACCESS_COARSE_LOCATION permission was granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "ACCESS_COARSE_LOCATION denied", Toast.LENGTH_LONG).show();
                }
                break;
            case MY_PERMISSION_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "ACCESS_FINE_LOCATION permission was granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "ACCESS_FINE_LOCATION denied", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private final LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(final Location location) {
            //Toast.makeText(getApplicationContext(), "location is Changed", Toast.LENGTH_LONG).show();
            if (location != null) {
                Date DateTime = Calendar.getInstance().getTime();
                TV_Location.append(String.valueOf( DateTime));
                TV_Location.append("\n");
                TV_Location.append(String.valueOf(location.getLatitude()));
                TV_Location.append(",");
                TV_Location.append(String.valueOf(location.getLongitude()));
                TV_Location.append("\n");
            } else {
                Toast.makeText(getApplicationContext(), "location is null", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            //Toast.makeText(TrackingActivity.this, "onStatusChanged" + s, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String s) {
            Toast.makeText(TrackingActivity.this, "Provider enabled: " + s, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String s) {
            Toast.makeText(TrackingActivity.this, "Provider disabled: " + s, Toast.LENGTH_SHORT).show();
        }
    };

}
