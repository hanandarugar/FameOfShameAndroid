package com.kaamchor.android.fameofshame;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.NumberPicker;

public class CountDownActivity extends AppCompatActivity {


    private NumberPicker hour;
    private NumberPicker mins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        hour = (NumberPicker) findViewById(R.id.hour);
        hour.setMinValue(0);
        hour.setMaxValue(24);
        mins = (NumberPicker) findViewById(R.id.mins);
        mins.setMaxValue(59);
        mins.setMinValue(0);
    }

    public void onStartTimerClick(View v ){
        Intent countDownActivityIntent = new Intent(this,StartTimerActivity.class);
        countDownActivityIntent.putExtra("Hour",  hour.getValue());
        countDownActivityIntent.putExtra("Mins", mins.getValue());
        startActivity(countDownActivityIntent);
    }

}
