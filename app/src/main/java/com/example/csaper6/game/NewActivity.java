package com.example.csaper6.game;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.csaper6.game.Setup.MenuActivity;

/**
 * Created by csaper6 on 5/1/17.
 */
public class NewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
    }

    @Override
    public void onBackPressed(){

        AlertDialog alertDialog = new AlertDialog.Builder(NewActivity.this).create();
        alertDialog.setTitle("WARNING: CHECK SAVE FILE");
        alertDialog.setMessage("Are you sure you want to exit the game?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes, I'm sure.",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent i = new Intent(NewActivity.this, MenuActivity.class);
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
}
