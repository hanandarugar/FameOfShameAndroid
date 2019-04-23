package com.kaamchor.android.fameofshame;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);
        ((ConstraintLayout)findViewById(R.id.activity_main)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent countDownActivityIntent = new Intent(MainActivity.this,CountDownActivity.class);
                startActivity(countDownActivityIntent);
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        AnimationSet animset=new AnimationSet(true);
        Animation fadeIn = AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        TranslateAnimation moveUp = new TranslateAnimation(0, 0, 20, 0);
        moveUp.setDuration(1500l);
        fadeIn.setDuration(1500l);
        animset.addAnimation(fadeIn);
        animset.addAnimation(moveUp);
        ((TextView)findViewById(R.id.setTimer)).startAnimation(animset);
    }
}
