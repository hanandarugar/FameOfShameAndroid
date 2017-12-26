package com.kaamchor.android.fameofshame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSetTimerClick(View v){
        Intent countDownActivityIntent = new Intent(this,CountDownActivity.class);
        startActivity(countDownActivityIntent);
    }

}
