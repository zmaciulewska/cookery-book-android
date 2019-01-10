package com.example.zuzia.cookbook.sensors;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zuzia.cookbook.R;

public class NextSensorActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private PowerManager.WakeLock wakeLock;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("CookBook", "On create proximity sensor activity started");
        setContentView(R.layout.next_sensor_activity);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK, ":tag");
        counter = 0;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        Log.d("CookBook", "On resume");
        super.onResume();
        sensorManager
                .registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        Log.d("CookBook", "On pause");
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    //@SuppressLint("InvalidWakeLockTag")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        TextView sensorTextView = findViewById(R.id.proximity_sensor_text_view);
        sensorTextView.setTextColor(Color.WHITE);
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (sensorEvent.values[0] == 0) {
                sensorTextView.setText("Near");
                sensorTextView.setBackgroundColor(getResources().getColor(R.color.bordeaux));
                sensorTextView.setTextSize(25);
                counter++;
            } else {
                sensorTextView.setText("Away");
                sensorTextView.setTextSize(50);
                sensorTextView.setBackgroundColor(getResources().getColor(R.color.dark_green));

                if(counter==2) {
                    Context context = getApplicationContext();
                    CharSequence text = "Application will be closed";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else if (counter>2) {
                    Log.d("CookBook", "Screen will be turned off");
                    wakeLock.acquire();
                    Log.d("CookBook", "Screen turned off");
                    counter = 0;
                }
            }
        }
    }
}
