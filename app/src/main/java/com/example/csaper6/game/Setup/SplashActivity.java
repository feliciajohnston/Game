package com.example.csaper6.game.Setup;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.csaper6.game.LoadActivity;
import com.example.csaper6.game.NewActivity;
import com.example.csaper6.game.R;

/**
 * Created by csaper6 on 4/27/17.
 */
public class SplashActivity extends AppCompatActivity {
    private int loadToScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.SecondaryDark),
                android.graphics.PorterDuff.Mode.SRC_IN);

        Intent i = getIntent();
        loadToScreen = i.getIntExtra("loading screen", 1);

        if(loadToScreen == 1){
            CountDownTimer timer = new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    Intent i = new Intent(SplashActivity.this, MenuActivity.class);
                    startActivity(i);
                }
            };
            timer.start();

        }
        else if (loadToScreen == 2){
            CountDownTimer timer = new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    Intent i = new Intent(SplashActivity.this, LoadActivity.class);
                    startActivity(i);
                }
            };
            timer.start();

        }
        else if (loadToScreen == 3){
            CountDownTimer timer = new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    Intent i = new Intent(SplashActivity.this, NewActivity.class);
                    startActivity(i);
                }
            };
            timer.start();

        }
        else if (loadToScreen == 4){
            CountDownTimer timer = new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    Intent i = new Intent(SplashActivity.this, OptionsActivity.class);
                    startActivity(i);
                }
            };
            timer.start();

        }
        else if (loadToScreen == 5){
            CountDownTimer timer = new CountDownTimer(3000, 1000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    Intent i = new Intent(SplashActivity.this, LeaderboardsActivity.class);
                    startActivity(i);
                }
            };
            timer.start();

        }
        else{
            Log.e("TAG", "COULD NOT COMPLETE LOAD: FATAL ERROR");
        }


    }
}

