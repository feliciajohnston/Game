package com.example.csaper6.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by csaper6 on 4/27/17.
 */
public class OptionsActivity extends AppCompatActivity {
    private SeekBar sfxSeek, musicSeek;
    private Button resetButt, creditsButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        resetButt = (Button) findViewById(R.id.button_reset);
        creditsButt = (Button) findViewById(R.id.button_credits);
        sfxSeek = (SeekBar) findViewById(R.id.seekBar_sfx);
        musicSeek = (SeekBar) findViewById(R.id.seekBar_music);

        resetButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(OptionsActivity.this).create();
                alertDialog.setTitle("WARNING: RESET ALL SAVED INFO?");
                alertDialog.setMessage("Are you sure you want to erase all saved files and leaderboards?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes, I'm sure.",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Toast.makeText(OptionsActivity.this, "NO", Toast.LENGTH_SHORT).show();
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
        creditsButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OptionsActivity.this, CreditsActivity.class);
                startActivity(i);
            }
        });

        sfxSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //set sfx volume
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
        });

        musicSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //set music volume
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
        });


    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(OptionsActivity.this, MenuActivity.class);
        startActivity(i);
    }
}
