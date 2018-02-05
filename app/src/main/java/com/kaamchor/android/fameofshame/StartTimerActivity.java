package com.kaamchor.android.fameofshame;

import android.app.Activity;
import android.app.NotificationChannel;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.os.PowerManager;


/**
 * Created by hanandarugar on 26/12/17.
 */


public class StartTimerActivity extends CountDownActivity {

    private TextView Hour, Minute,Second;
    private TextView tvEventStart;
    private int hr, mn;
    private boolean isTimerRunning;
    private CountDownTimer cdTimer;
    private long hours;
    private long minutes;
    private long seconds;
    private long currentHour;
    private long currentMin;
    private long currentSec;
    private long timerResume;
    private boolean phoneCall = false;
    private boolean isReset = false;
    private long currentStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_timer);
        Intent intent = getIntent();
        initTimer();
        if(intent.getExtras().containsKey("Hour") || intent.getExtras().containsKey("Mins")) {
            hr = getIntent().getExtras().getInt("Hour");
            mn = getIntent().getExtras().getInt("Mins");
            countDownStart();
        }
    }

    @Override
    protected void onStart() {
        Log.wtf("StartTimeActivity,","onStart called");
        super.onStart();
    }

    @Override
    protected void onStop() {
//        Log.wtf("StartTimeActivity,", "value of timeInms onSTop called" + timeInMs);
//        if (timeInMs > 0) {
//            countDownStart();
//        } else {
            Log.wtf("StartTimeActivity,", "onSTop called");
            super.onStop();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent.getExtras().containsKey("currentHour") || intent.getExtras().containsKey("currentMin") || intent.getExtras().containsKey("currentSec") ){
            currentHour = intent.getLongExtra("currentHour",0);
            currentMin = intent.getLongExtra("currentMin", 0);
            currentSec = intent.getLongExtra("currentSec", 0);
            timerResume = 1;
            countDownStart();
        }
    }

    public void initTimer(){
        Hour = (TextView) findViewById(R.id.hourTimer);
        Minute = (TextView) findViewById(R.id.minsTimer);
        Second = (TextView) findViewById(R.id.secTimer);
        tvEventStart = (TextView) findViewById(R.id.tveventStart);
    }

    public void countDownStart() {
        long diff;
        if(timerResume == 1){
            diff = (((currentHour * 60) + currentMin) * 60 + currentSec) *1000;
        }else {
            diff = ((hr * 60) + mn) * 60 * 1000;
        }
        cdTimer = new CountDownTimer(diff, 1000){
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "Wake Lock");

            public void onTick(long millisUntilFinished) {
                wl.acquire();
                hours = millisUntilFinished / (1000 * 60 *60);
                millisUntilFinished -= hours * (60 * 60 * 1000);
                minutes = millisUntilFinished / (1000 * 60);
                millisUntilFinished -= minutes * (60 * 1000);
                seconds = millisUntilFinished / 1000;
                currentHour = hours;
                currentMin = minutes;
                currentSec = seconds;
                isTimerRunning = true;
                Hour.setText("" + String.format("%02d", hours));
                Minute.setText("" + String.format("%02d", minutes));
                Second.setText("" + String.format("%02d", seconds));

            }

            public void onFinish() {

                Intent finishActivityIntent = new Intent(StartTimerActivity.this,FinishActivity.class);
                isTimerRunning = false;
                startActivity(finishActivityIntent);
                wl.release();
            }
        };
        cdTimer.start();
    }

    public void sendNotification() {

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        ShameNotification shameNotification = new ShameNotification();

        String channelId = "some_channel_id";
        CharSequence channelName = "Some Channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_stat_access_alarms)
                        .setContentTitle("My notification")
                        .setContentText(shameNotification.getRemarks());
                notificationManager.notify(001, mBuilder.build());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.wtf("StartTimer","onPause called");
        if (((TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE)).getCallState() == TelephonyManager.CALL_STATE_RINGING) {
            Log.wtf("StartTimer","call state ringing branch");
            Intent myServiceIntent = new Intent(StartTimerActivity.this,MyService.class);
            myServiceIntent.putExtra("currentHour",currentHour);
            myServiceIntent.putExtra("currentMin",currentMin);
            myServiceIntent.putExtra("currentSec",currentSec);
            phoneCall = true;
            cdTimer.cancel();
            finish();
            startService(myServiceIntent);

        }
        else if (((TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE)).getCallState() == TelephonyManager.CALL_STATE_IDLE && phoneCall == true) {
            Log.wtf("StartTimer","call state idle branch");
            long diff;
            diff = (((currentHour * 60) + currentMin) * 60 + currentSec) *1000;
            phoneCall = false;
            cdTimer.onTick(diff);
        }
        else if (isTimerRunning == true){
            if (isReset == false) {
                Log.wtf("StartTimer", "start new activity branch");

                if (cdTimer != null) {
                    cdTimer.cancel();
                }
                Log.wtf("sound", "about to play shame");
                Intent mainActivityIntent = new Intent(this, MainActivity.class);
                MediaPlayer shame = MediaPlayer.create(StartTimerActivity.this, R.raw.shamebell);
                shame.start();
                sendNotification();
                startActivity(mainActivityIntent);
                finish();
            }
        }

    }

    public void onResetClick(View v){
        isReset = true;
        Intent countDownActivityIntent = new Intent(this,CountDownActivity.class);
        startActivity(countDownActivityIntent);
    }

    public void onStopClick(View v){
        sendNotification();
        Intent mainActivityIntent = new Intent(this,MainActivity.class);
        startActivity(mainActivityIntent);
    }

}
