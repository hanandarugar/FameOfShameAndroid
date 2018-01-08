package com.kaamchor.android.fameofshame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        TextView tvEventStart = (TextView) findViewById(R.id.tveventStart) ;
        tvEventStart.setText("Well done!");
    }
}
