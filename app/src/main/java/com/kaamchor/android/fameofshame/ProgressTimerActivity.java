package com.kaamchor.android.fameofshame;

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
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.os.PowerManager;

import static java.lang.Math.*;


/**
 * Created by hanandarugar on 26/12/17.
 */


public class ProgressTimerActivity extends CountDownActivity {

    private boolean isTimerRunning;
    private CountDownTimer cdTimer;
    private long currentTimeInMillisec;
    private long hr,mn;
    private int timerResume = 0;
    private boolean phoneCall = false;
    private boolean isReset = false;
    private String stringTime;
    private long timeInPercentage;
    private com.github.lzyzsd.circleprogress.DonutProgress progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_timer);
        Intent intent = getIntent();
        initTimer();
        Log.wtf("progress on create", "on creayte");
        if(intent.getExtras().containsKey("Hour") || intent.getExtras().containsKey("Mins")) {
            hr = getIntent().getExtras().getInt("Hour");
            mn = getIntent().getExtras().getInt("Mins");
            countDownStart();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent.getExtras().containsKey("CurrentTimeInMillisec")){
            currentTimeInMillisec = intent.getLongExtra("CurrentTimeInMillisec",0);
            timerResume = 1;
            countDownStart();
        }
    }

    public void initTimer(){
        progressBar = findViewById(R.id.progressBar);
    }

    public void countDownStart() {
        final long diff;
        if(timerResume == 1){
            diff = currentTimeInMillisec;
        }else {
            diff = ((hr * 60) + mn) * 60 * 1000;
        }
        cdTimer = new CountDownTimer(diff, 1000){
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "Wake Lock");

            public void onTick(long millisUntilFinished) {
                wl.acquire();
                isTimerRunning = true;
                Log.wtf("millisec", String.valueOf(millisUntilFinished));
                currentTimeInMillisec = millisUntilFinished;
                Log.wtf("diff", String.valueOf(diff));
                timeInPercentage = ((millisUntilFinished * 100) / diff);
                Log.wtf("timeInPercentage", String.valueOf(timeInPercentage));
                stringTime = String.valueOf(timeInPercentage);
                Log.wtf("percentage", stringTime);
                progressBar.setDonut_progress(stringTime);
                progressBar.setText(stringTime + "%");
                progressBar.invalidate();
            }

            public void onFinish() {

                Intent finishActivityIntent = new Intent(ProgressTimerActivity.this,FinishActivity.class);
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
        if (((TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE)).getCallState() == TelephonyManager.CALL_STATE_RINGING) {
            Intent myServiceIntent = new Intent(ProgressTimerActivity.this,MyService.class);
            myServiceIntent.putExtra("CurrentTimeInMillisec",currentTimeInMillisec);
            phoneCall = true;
            cdTimer.cancel();
            finish();
            startService(myServiceIntent);

        }
        else if (((TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE)).getCallState() == TelephonyManager.CALL_STATE_IDLE && phoneCall == true) {
            long diff;
            diff = currentTimeInMillisec;
            phoneCall = false;
            cdTimer.onTick(diff);
        }
        else if (isTimerRunning == true){
            if (isReset == false) {
                if (cdTimer != null) {
                    cdTimer.cancel();
                }
                Intent mainActivityIntent = new Intent(this, MainActivity.class);
                MediaPlayer shame = MediaPlayer.create(ProgressTimerActivity.this, R.raw.shamebell);
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

