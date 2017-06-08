package com.example.csaper6.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.csaper6.game.GamePlay.MainActivity;

/**
 * Created by csaper6 on 5/1/17.
 */
public class LoadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        Intent i = new Intent(LoadActivity.this, MainActivity.class);
        i.putExtra("gameType", 0); //0 = load game
        startActivity(i);


    }

}
