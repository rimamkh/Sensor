package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    Sensor proximity, accelerometer, gryo, Mango, light, presure, temp, humi;

    //private static final String TAG = "MainActivity";///////////????????????? manifest
    TextView xValue, mangValue, gryoValue, LightValue, PresureValue,
            tempValue, humiValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xValue = findViewById(R.id.xValue);
        mangValue = findViewById(R.id.textView);
        gryoValue = findViewById(R.id.textView2);
        LightValue = findViewById(R.id.textView3);
        PresureValue = findViewById(R.id.textView4);
        tempValue = findViewById(R.id.textView5);
        humiValue = findViewById(R.id.textView6);

        //initilaize sensor service
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ///we should check if the phone support the sensor or no
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener(MainActivity.this, proximity,
                2 * 100 * 100);
        if (proximity == null) {
            Toast.makeText(this, "Not support ", Toast.LENGTH_SHORT).show();
        }
        if (accelerometer != null) {

            sensorManager.registerListener(MainActivity.this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            xValue.setText("the Acceletmerter not support");
        }
        gryo = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gryo != null) {

            sensorManager.registerListener(MainActivity.this, gryo,
                    SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            gryoValue.setText("the groyc not support");
        }
        Mango = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (Mango != null) {

            sensorManager.registerListener(MainActivity.this, Mango,
                    SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            mangValue.setText("the magnatic filed  not support");
        }
        presure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (presure != null) {

            sensorManager.registerListener(MainActivity.this, presure,
                    SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            PresureValue.setText("the presure   not support");
        }
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (light != null) {

            sensorManager.registerListener(MainActivity.this, light,
                    SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            LightValue.setText("the TEMP   not support");
        }
        temp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (temp != null) {

            sensorManager.registerListener(MainActivity.this, temp,
                    SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            tempValue.setText("the TEMP   not support");
        }
        humi = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (humi != null) {

            sensorManager.registerListener(MainActivity.this, humi,
                    SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            humiValue.setText("the HUMITY   not support");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        ///we check the type of sensor
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {//xyz
            Log.d("dddd", "onSensorChanged: x" + sensorEvent.values[0] +
                    "y:" + sensorEvent.values[1] + "z:" + sensorEvent.values[2]);
            xValue.setText("the value of x is :" + sensorEvent.values[0]);
        } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {//xyz
            gryoValue.setText("the value of x is :" + sensorEvent.values[0]);
        } else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            mangValue.setText("the value of x is :" + sensorEvent.values[0]);
        } else if (sensor.getType() == Sensor.TYPE_LIGHT) {
            LightValue.setText("the value of light is :" + sensorEvent.values[0]);
        } else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            tempValue.setText("the value of temp is :" + sensorEvent.values[0]);
        } else if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            Log.d("TAG", "HYMty: x" + sensorEvent.values[0]);
            humiValue.setText("the value of hum is :" + sensorEvent.values[0]);
        } else if (sensor.getType() == Sensor.TYPE_PRESSURE) {

            PresureValue.setText("the value of presure is :" + sensorEvent.values[0]);
        }
        if (sensorEvent.values[0] < proximity.getMaximumRange()) {
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        } else {
            getWindow().getDecorView().setBackgroundColor(Color.BLACK);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}