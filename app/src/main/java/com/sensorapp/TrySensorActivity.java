package com.sensorapp;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vladimir Salguero on 16/05/2016.
 */
public class TrySensorActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    TextView mTimeStamp;
    TextView mAccuracy;
    Sensor mSensor;
    static final String NAME = "name";
    static final String VALUE = "value";


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


    }


    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
        mAccuracy.setText("Accuracy: " + accuracy);
    }


    public final void onSensorChanged(SensorEvent event) {
        mTimeStamp.setText("Timestamp: " + String.valueOf(event.timestamp));

        float[] mValues;
        mValues = event.values;
       
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        for (int i = 0; i < mValues.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put(VALUE, "Data[" + i + "] " + mValues[i]);
            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                android.R.layout.simple_list_item_2,
                new String[]{NAME,VALUE},
                new int[]{android.R.id.text1, android.R.id.text2}
        );

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);


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
