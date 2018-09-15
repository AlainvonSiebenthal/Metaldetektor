package com.example.lvsa.metadetektor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.FloatMath;
import android.view.TextureView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor magneticsensor;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    progressBar = (ProgressBar) findViewById(R.id.progressBar);
    progressBar.setMax(1000);
    textView = (TextView) findViewById(R.id.textView);



    sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    magneticsensor = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).get(0);
    }

    @Override
    protected void onResume () {
        super.onResume();
        sensorManager.registerListener(this,magneticsensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] mag = event.values;
        double betrag = Math.sqrt(mag[0] * mag[0] + mag[1] * mag[1] + mag[2] * mag[2]);
        progressBar.setProgress((int) betrag);
        textView.setText("Magnetfeldstärke: "+(int) betrag);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}