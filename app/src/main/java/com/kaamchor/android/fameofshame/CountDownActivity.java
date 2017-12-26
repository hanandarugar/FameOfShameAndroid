package com.kaamchor.android.fameofshame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.NumberPicker;

public class CountDownActivity extends AppCompatActivity {

    int hr = 0;
    int min = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        NumberPicker hour = (NumberPicker) findViewById(R.id.hour);
        hour.setMinValue(0);
        hour.setMaxValue(24);
        NumberPicker mins = (NumberPicker) findViewById(R.id.mins);
        mins.setMaxValue(59);
        mins.setMinValue(0);
        hr = hour.getValue();
        min = mins.getValue();

    }
     public int getHour(){
        return hr;
     }
     public int getMin(){
         return min;
     }
    public void onStartTimerClick(View v){
        Intent countDownActivityIntent = new Intent(this,StartTimerActivity.class);
        startActivity(countDownActivityIntent);
    }

}
