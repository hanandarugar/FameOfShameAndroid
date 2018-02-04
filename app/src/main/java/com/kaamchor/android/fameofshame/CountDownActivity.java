package com.kaamchor.android.fameofshame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.Toast;
import mehdi.sakout.fancybuttons.FancyButton;

public class CountDownActivity extends AppCompatActivity {

    private com.shawnlin.numberpicker.NumberPicker hour;
    private com.shawnlin.numberpicker.NumberPicker mins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_count_down);
        hour = (com.shawnlin.numberpicker.NumberPicker) findViewById(R.id.hour);
        hour.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/segoeuisl.ttf"));
        hour.setMinValue(0);
        hour.setMaxValue(24);
        hour.setValue(0);
        mins = (com.shawnlin.numberpicker.NumberPicker) findViewById(R.id.mins);
        mins.setMaxValue(59);
        mins.setMinValue(0);
        mins.setValue(0);
        mins.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/segoeuisl.ttf"));
        ((FancyButton)findViewById(R.id.startTimerButton)).setCustomTextFont("segoeuisl.tff");
    }

    public void onStartTimerClick(View v ) {
        if (hour.getValue() == 0 && mins.getValue() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "Enter a valid time.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            Intent countDownActivityIntent = new Intent(this, StartTimerActivity.class);
            countDownActivityIntent.putExtra("Hour", hour.getValue());
            countDownActivityIntent.putExtra("Mins", mins.getValue());
            startActivity(countDownActivityIntent);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

    }
}
