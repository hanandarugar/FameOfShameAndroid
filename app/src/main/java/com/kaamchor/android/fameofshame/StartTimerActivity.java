package com.kaamchor.android.fameofshame;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TextView;

/**
 * Created by hanandarugar on 26/12/17.
 */


public class StartTimerActivity extends CountDownActivity {

    private TextView Hour, Minute,Second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_timer);
        initTimer();
        countDownStart();
    }

    public void initTimer(){
        Hour = (TextView) findViewById(R.id.hourTimer);
        Minute = (TextView) findViewById(R.id.minsTimer);
        Second = (TextView) findViewById(R.id.secTimer);
    }

    public void countDownStart() {
        int hr = getHour();
        int mn = getMin();
        int diff = 0;
        if (hr == 0) diff = mn;
        else diff = hr;
        int hours = diff / (60 * 60 * 1000);
        diff -= hours * (60 * 60 * 1000);
        int minutes = diff / (60 * 1000);
        diff -= minutes * (60 * 1000);
        int seconds = diff / 1000;
        Hour.setText("" + String.format("%02d", hours));
        Minute.setText("" + String.format("%02d", minutes));
        Second.setText("" + String.format("%02d", seconds));

    }
}
