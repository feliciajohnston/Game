package com.example.csaper6.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.csaper6.game.GamePlay.MainActivity;

/**
 * Created by csaper6 on 5/1/17.
 */
public class NewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


        Intent i = new Intent(NewActivity.this, MainActivity.class);
        i.putExtra("gameType", 1); // 1 = new game
        startActivity(i);
    }

}
