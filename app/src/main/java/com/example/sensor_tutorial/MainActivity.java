package com.example.sensor_tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String  TAG = "MainActivity";
    TextView accel_tv1,accel_tv2,accel_tv3,accel_tv4,light_tv1,light_tv2,proximity_tv1,proximity_tv2,gps_tv1,gps_tv2,gps_tv3;
    ToggleButton accel_btn,light_btn,proximity_btn,gps_btn;
    private SensorManager sensorManager;
    private LocationManager locationManager;
    LocationListener mlocListener;

    Sensor accelerometer,mLight,mProx;
    double lon,la;

 //   @Override
    protected void Stop(int i) {

        if(i == 1) {
            sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        }
        else if(i == 2) {
            sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT));
        }

        else if(i == 3) {
            sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY));
        }

        else if(i == 4) {
            locationManager.removeUpdates(mlocListener);
        }

    }
    class MyLocationListener implements LocationListener{
        @Override
        public void onProviderDisabled(@NonNull String provider) {
            LocationListener.super.onProviderDisabled(provider);
        }

        @Override
        public void onLocationChanged(@NonNull Location location) {

            gps_tv2.setText(String.valueOf(location.getLongitude()));
            gps_tv3.setText(String.valueOf(location.getLatitude()));

        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Textview Initialization
        accel_tv1 = (TextView) findViewById(R.id.tv7);
        accel_tv2 = (TextView) findViewById(R.id.tv1);
        accel_tv3 = (TextView) findViewById(R.id.tv10);
        accel_tv4 = (TextView) findViewById(R.id.tv11);
        light_tv1 = (TextView) findViewById(R.id.tv8);
        light_tv2 = (TextView) findViewById(R.id.tv2);
        proximity_tv1 = (TextView) findViewById(R.id.tv9);
        proximity_tv2 = (TextView) findViewById(R.id.tv3);
        gps_tv1 = (TextView) findViewById(R.id.tv6);
        gps_tv2 = (TextView) findViewById(R.id.tv4);
        gps_tv3 = (TextView) findViewById(R.id.tv5);

// ToggleButton Initialization
        accel_btn = (ToggleButton) findViewById(R.id.toggleButton1);
        light_btn = (ToggleButton) findViewById(R.id.toggleButton2);
        proximity_btn = (ToggleButton) findViewById(R.id.toggleButton3);
        gps_btn = (ToggleButton) findViewById(R.id.toggleButton4);

        Log.d(TAG,"Inintializing sensor service");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accel_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                    if (accelerometer != null) {

                        sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, "Accelerometer sensor started", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Log.i(TAG,"Accelerometer Not supported");
                    }
                }
                else{
                    Stop(1);
                    accel_tv2.setText("0");
                    accel_tv3.setText("0");
                    accel_tv4.setText("0");
                    Toast.makeText(MainActivity.this, "Accelerometer sensor stopped", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // --------------accelerometer ends and light starts ----------------------------
        light_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                    if (mLight != null) {

                        sensorManager.registerListener(MainActivity.this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, " Light sensor started", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Log.i(TAG,"Light Not supported");
                    }
                }
                else{
                    Stop(2);
                    light_tv2.setText("0");
                    Toast.makeText(MainActivity.this, " Light sensor stopped", Toast.LENGTH_SHORT).show();

                }
            }
        });
        // ---------------- light sensor ends and proximity sensor starts--------------------
        proximity_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    mProx = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                    if (mProx != null) {

                        sensorManager.registerListener(MainActivity.this, mProx, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, " Proximity sensor started", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Log.i(TAG,"Not supported");
                    }
                }
                else{
                    Stop(3);
                    proximity_tv2.setText("0");
                    Toast.makeText(MainActivity.this, " Proximity sensor stopped", Toast.LENGTH_SHORT).show();

                }
            }
        });
        // ---------- Proximity sensor ends and GPS sensor starts ----------------------



        gps_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},1);
                    }
                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    mlocListener = new MyLocationListener();
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, mlocListener);

                }
                else if (!isChecked){

                    Stop(4);
                }
            }
        });

                }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x, y, z;
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            z = sensorEvent.values[2];

            Log.d(TAG, "Accelerometer X:" + sensorEvent.values[0] + "Y:" + sensorEvent.values[1]
                    + "Z:" + sensorEvent.values[2]);
            accel_tv2.setText(Float.toString(sensorEvent.values[0]));
            accel_tv3.setText(Float.toString(sensorEvent.values[1]));
            accel_tv4.setText(Float.toString(sensorEvent.values[2]));





        }
        else  if (sensor.getType() == Sensor.TYPE_LIGHT) {
            Log.d(TAG, "Light:" + sensorEvent.values[0]);
            light_tv2.setText(Float.toString(sensorEvent.values[0]));
        }
        else  if (sensor.getType() == Sensor.TYPE_PROXIMITY) {
            Log.d(TAG, "Proximity" + sensorEvent.values[0]);
            proximity_tv2.setText(Float.toString(sensorEvent.values[0]));
        }


    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


            }