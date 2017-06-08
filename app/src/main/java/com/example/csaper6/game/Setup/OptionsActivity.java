package com.example.csaper6.game.Setup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.csaper6.game.R;

/**
 * Created by csaper6 on 4/27/17.
 */
public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);


        Switch musicSwitch = (Switch) findViewById(R.id.switch_music);
        Button resetButt = (Button) findViewById(R.id.button_reset);
        Button creditsButt = (Button) findViewById(R.id.button_credits);

        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    stopService(new Intent(OptionsActivity.this, BackgroundSoundService.class));
                }
                else{
                    Intent svc = new Intent(OptionsActivity.this, BackgroundSoundService.class);
                    startService(svc);
                }

            }
        });
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

    }
    @Override
    public void onBackPressed(){
        Intent i = new Intent(OptionsActivity.this, MenuActivity.class);
        startActivity(i);
    }
}
