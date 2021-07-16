package com.example.wearostestapplication;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.wearostestapplication.databinding.ActivityMainBinding;

public class MainActivity extends Activity implements SensorEventListener{

    private TextView mTextView;
    TextView txtXValAccel;
    TextView txtYValAccel;
    TextView txtZValAccel;
    TextView txtXValGyro;
    TextView txtYValGyro;
    TextView txtZValGyro;

    private ActivityMainBinding binding;
    private SensorManager sensorManager;
    Sensor accelerometer;
    Sensor gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.txtXVal;

        txtXValAccel = findViewById(R.id.txtXVal);
        txtYValAccel = findViewById(R.id.txtYVal);
        txtZValAccel = findViewById(R.id.txtZVal);
        txtXValGyro = findViewById(R.id.txtXValGyro);
        txtYValGyro = findViewById(R.id.txtYValGyro);
        txtZValGyro = findViewById(R.id.txtZValGyro);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(MainActivity.this,accelerometer,500000);
        sensorManager.registerListener(MainActivity.this,gyroscope,500000);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //TODO Remove later, keeps screen on for testing
    }

    @Override
    public void onSensorChanged(SensorEvent se) {
        Sensor sensor = se.sensor;

        String xVal = "X: " + se.values[0];
        String yVal = "Y: " + se.values[1];
        String zVal = "Z: " + se.values[2];
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            txtXValAccel.setText(xVal);
            txtYValAccel.setText(yVal);
            txtZValAccel.setText(zVal);
        }
        else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            txtXValGyro.setText(xVal);
            txtYValGyro.setText(yVal);
            txtZValGyro.setText(zVal);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        //sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(MainActivity.this);
    }
}