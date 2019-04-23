package com.kaamchor.android.fameofshame;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CheersNotification cheersNotification = new CheersNotification();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        MediaPlayer cheer= MediaPlayer.create(FinishActivity.this,R.raw.cheer);
        TextView tvEventStart = (TextView) findViewById(R.id.tveventStart) ;
        tvEventStart.setText(cheersNotification.getRemarks());
        cheer.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
