package com.kaamchor.android.fameofshame;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.NumberPicker;
import android.widget.Toast;

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

    public void onStartTimerClick(View v ) {
        if (hour.getValue() == 0 && mins.getValue() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Enter a valid time.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
            toast.show();
            Log.wtf("countDownActivity", "why you no working? :( ");
        } else {
            Intent countDownActivityIntent = new Intent(this, StartTimerActivity.class);
            countDownActivityIntent.putExtra("Hour", hour.getValue());
            countDownActivityIntent.putExtra("Mins", mins.getValue());
            startActivity(countDownActivityIntent);
        }
    }
}
