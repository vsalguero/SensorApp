package com.sensorapp;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Vladimir Salguero on 16/05/2016.
 */
public class TrySensorActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    TextView mTimeStamp;
    TextView mAccuracy;
    TextView[] mData;
    Sensor mSensor;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_activity);
        mTimeStamp = (TextView) findViewById(R.id.timestamp);
        mAccuracy = (TextView) findViewById(R.id.accuracy);


        int type = getIntent().getIntExtra(DetailsActivity.TYPE, 0);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(type);

        //mostrar nombre del sensor
        TextView view = (TextView) findViewById(R.id.sensorname);
        view.setText(mSensor.getName());

        mData = new TextView[]{
                (TextView) findViewById(R.id.data1),
                (TextView) findViewById(R.id.data2),
                (TextView) findViewById(R.id.data3),
                (TextView) findViewById(R.id.data4),
                (TextView) findViewById(R.id.data5),
                (TextView) findViewById(R.id.data6),
                (TextView) findViewById(R.id.data7),
                (TextView) findViewById(R.id.data8),
                (TextView) findViewById(R.id.data9),
                (TextView) findViewById(R.id.data10),
        };


    }


    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
        mAccuracy.setText("Accuracy: " + accuracy);
    }


    public final void onSensorChanged(SensorEvent event) {
        mTimeStamp.setText("Timestamp: " + String.valueOf(event.timestamp));

        float[] mValues;
        mValues = event.values;
        for (int i = 0; i < mValues.length; i++) {
            mData[i].setText("Data[" + i + "]" + mValues[i]);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


}
