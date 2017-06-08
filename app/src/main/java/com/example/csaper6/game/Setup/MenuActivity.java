package com.example.csaper6.game.Setup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.csaper6.game.R;

/**
 * Created by csaper6 on 4/27/17.
 */
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //start music service
        Intent svc = new Intent(MenuActivity.this, BackgroundSoundService.class);
        startService(svc);




        Button loadButt = (Button) findViewById(R.id.button_load);
        Button newButt = (Button) findViewById(R.id.button_new);
        Button optionsButt = (Button) findViewById(R.id.button_options);
        Button leaderboardsButt = (Button) findViewById(R.id.button_leaderboards);

        loadButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, SplashActivity.class);
                i.putExtra("loading screen", 2);
                startActivity(i);

            }
        });

        newButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(MenuActivity.this).create();
                alertDialog.setTitle("WARNING: OVERWRITE SAVE FILE");
                alertDialog.setMessage("Are you sure you want to possibly overwrite your save file?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes, I'm sure.",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent i = new Intent(MenuActivity.this, SplashActivity.class);
                                i.putExtra("loading screen", 3);
                                startActivity(i);
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No, wait go back.",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });

        optionsButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, SplashActivity.class);
                i.putExtra("loading screen", 4);
                startActivity(i);
            }
        });

        leaderboardsButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, SplashActivity.class);
                i.putExtra("loading screen", 5);
                startActivity(i);
            }
        });

    }

    @Override
    public void onDestroy(){
        stopService(new Intent(this, BackgroundSoundService.class));
        super.onDestroy();
    }
}

