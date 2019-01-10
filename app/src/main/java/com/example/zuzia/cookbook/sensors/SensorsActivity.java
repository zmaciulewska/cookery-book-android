package com.example.zuzia.cookbook.sensors;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zuzia.cookbook.R;

import java.util.ArrayList;
import java.util.List;


public class SensorsActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("CookBook", "On create started");
        setContentView(R.layout.activity_sensors);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        String result = getResources().getQuantityString(R.plurals.sensors_summary_info, deviceSensors.size(), deviceSensors.size());
        TextView sensorsSubtitle = findViewById(R.id.sensors_subtitle);
        sensorsSubtitle.setText(result);

        // sensors list
        List<String> sensors = new ArrayList<>();
        for(Sensor s : deviceSensors) {
            sensors.add(s.getName());
        }
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.list_view_element, sensors);
        ListView sensorsList = findViewById(R.id.sensors_list);
        sensorsList.setAdapter(adapter);

        Log.d("CookBook", "Getting accelerometer");
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Log.d("CookBook", "Accelerometer got");

        TextView acceleratorSubtitle = findViewById(R.id.accelerator_subtitle);
        acceleratorSubtitle.setText("Accelerator values");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        Log.d("CookBook", "On resume");
        super.onResume();
        sensorManager
                .registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        Log.d("CookBook", "On pause");
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

      /*  Log.d("CookBook", "x=" + sensorEvent.values[0] +
        ", y=" + sensorEvent.values[1]+
        ", z=" + sensorEvent.values[2]);*/
        TextView acceleratorValue = findViewById(R.id.accelerator_value);
        acceleratorValue.setText("x=" + sensorEvent.values[0] +
                ", y=" + sensorEvent.values[1]+
                ", z=" + sensorEvent.values[2]);
        acceleratorValue.invalidate();
        /*TextView yValue = findViewById(R.id.yvalue);
        yValue.setText((int) sensorEvent.values[1]);
        TextView zValue = findViewById(R.id.zvalue);
        zValue.setText((int) sensorEvent.values[2]);
    */}

    public void goToNextSensor(View view) {
        this.onPause();
        Log.d("CookBook", "Trying to move to next sensor");
        sensorManager.unregisterListener(this);
        startActivity(new Intent(SensorsActivity.this, NextSensorActivity.class));
    }
}

