package com.example.christopherluey.whyyyy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {

    //Scroll
    SeekBar tempAdjust;

    //text Temperature Is...
    TextView TemperatureIs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //text Temperature Is ...
        TemperatureIs = (TextView) findViewById(R.id.temperatureIsText);

        //Scroll set parameters
        tempAdjust = (SeekBar) findViewById(R.id.TempAdjust);
        //tempAdjust.setMin(15);
        tempAdjust.setMax(18);
        tempAdjust.setProgress(1);

        TemperatureIs.setText("Set Temperature");

        //Scroll movement tracker
        tempAdjust.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int temp = 15;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                temp = progress + 15;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                TemperatureIs.setText("Temperature is: " + temp);

            }
        });

    }
}
