package com.sensorapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Vladimir Salguero on 11/05/2016.
 */
public class DetailsActivity extends Activity {
    static final String TYPE = "type";

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.details_activity);

        int type = getIntent().getIntExtra(MainActivity.TYPE, 0);

        SensorManager sensor_manager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);

        Sensor sensor = sensor_manager.getDefaultSensor(type);

        ArrayList<String> list = new ArrayList<>();

        ListView listView = (ListView) findViewById(R.id.details_view);


        list.add("Resolution: " + sensor.getResolution());
        list.add("Max range: " + sensor.getMaximumRange());
        list.add("Min delay: " + sensor.getMinDelay());
        list.add("Power: " + sensor.getPower());
        list.add("Vendor: " + sensor.getVendor());
        list.add("Version: " + sensor.getVersion());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, list
        );

        listView.setAdapter(adapter);


    }

    public void trySensor(View view) {
        Intent intent = new Intent(this, TrySensorActivity.class);
        int type = getIntent().getIntExtra(MainActivity.TYPE, 0);
        intent.putExtra(TYPE, type);
        startActivity(intent);
    }

}
