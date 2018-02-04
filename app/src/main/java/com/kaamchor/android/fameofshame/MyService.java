package com.kaamchor.android.fameofshame;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    MediaPlayer myPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service Created", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        while (((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getCallState() != TelephonyManager.CALL_STATE_IDLE) {
            //do nothing
        }
        Intent startTimerIntent = new Intent(this, StartTimerActivity.class);
        long currentHour = intent.getLongExtra("currentHour", 0);
        long currentMin = intent.getLongExtra("currentMin", 0);
        long currentSec = intent.getLongExtra("currentSec", 0);
        startTimerIntent.putExtra("currentHour", currentHour);
        startTimerIntent.putExtra("currentMin", currentMin);
        startTimerIntent.putExtra("currentSec", currentSec);
        getApplicationContext().startActivity(startTimerIntent);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
    }
}