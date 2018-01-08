package com.kaamchor.android.fameofshame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

/**
 * Created by hanandarugar on 26/12/17.
 */


public class StartTimerActivity extends CountDownActivity {

    private TextView Hour, Minute,Second;
    private TextView tvEventStart;
    private int hr, mn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_timer);
        Intent intent = getIntent();
        hr = getIntent().getExtras().getInt("Hour");
        mn = getIntent().getExtras().getInt("Mins");
        initTimer();
        countDownStart();
    }

    public void initTimer(){
        Hour = (TextView) findViewById(R.id.hourTimer);
        Minute = (TextView) findViewById(R.id.minsTimer);
        Second = (TextView) findViewById(R.id.secTimer);
        tvEventStart = (TextView) findViewById(R.id.tveventStart);
    }

    public void countDownStart() {
        long diff = ((hr*60) + mn)*60 * 1000;
        new CountDownTimer(diff, 1000) {

            public void onTick(long millisUntilFinished) {
                long hours = millisUntilFinished / (1000 * 60 *60);
                millisUntilFinished -= hours * (60 * 60 * 1000);
                long minutes = millisUntilFinished / (1000 * 60);
                millisUntilFinished -= minutes * (60 * 1000);
                long seconds = millisUntilFinished / 1000;
                Hour.setText("" + String.format("%02d", hours));
                Minute.setText("" + String.format("%02d", minutes));
                Second.setText("" + String.format("%02d", seconds));
            }

            public void onFinish() {
                Intent finishActivityIntent = new Intent(StartTimerActivity.this,FinishActivity.class);
                startActivity(finishActivityIntent);
            }
        }.start();
    }

    public void onResetClick(View v){
        Intent countDownActivityIntent = new Intent(this,CountDownActivity.class);
        startActivity(countDownActivityIntent);
    }

    public void onStopClick(View v){
        Intent mainActivityIntent = new Intent(this,MainActivity.class);
        startActivity(mainActivityIntent);
    }
}
