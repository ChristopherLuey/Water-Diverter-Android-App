package com.example.christopherluey.whyyyy;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ListActivity extends AppCompatActivity implements
        DeviceListFragment.OnFragmentInteractionListener, View.OnClickListener {

    Button MainScreen;
    Button onoff;
    TextView BTonoff;

    private DeviceListFragment mDeviceListFragment;
    public BluetoothAdapter btAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        MainScreen = findViewById(R.id.back);
        onoff = findViewById(R.id.onoff);
        BTonoff = findViewById(R.id.BTonoff);

        btAdapter = BluetoothAdapter.getDefaultAdapter();


        FragmentManager fragmentManager = getSupportFragmentManager();
        mDeviceListFragment = DeviceListFragment.newInstance(btAdapter);
        fragmentManager.beginTransaction().replace(R.id.container, mDeviceListFragment).commit();

        MainScreen.setOnClickListener(this);

    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back) {
            Intent switchMainScreen = new Intent(ListActivity.this, MainScreen.class);
            startActivity(switchMainScreen);

        }

        if (v.getId() == R.id.onoff) {
            enabledisableBT();
        }
    }


    public void enabledisableBT() {

        if (btAdapter == null) {
            BTonoff.setText("Your device is not compatible with bluetooth");
        }

        if (!btAdapter.isEnabled()) {
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBT);

            new CountDownTimer(30000, 1000) {

                public void onTick(long millisUntilFinished) {
                    BTonoff.setText("Bluetooth is now turning on");
                }

                public void onFinish() {
                    BTonoff.setText("Bluetooth is now on!");
                }
            }.start();

        }
        if (btAdapter.isEnabled()) {
            btAdapter.disable();

            new CountDownTimer(30000, 1000) {

                public void onTick(long millisUntilFinished) {
                    BTonoff.setText("Bluetooth is now turning off");
                }

                public void onFinish() {
                    BTonoff.setText("Bluetooth is now off");
                }
            }.start();

            }
        }
    }



