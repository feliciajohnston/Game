package com.example.csaper6.game.Setup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.csaper6.game.R;

/**
 * Created by csaper6 on 5/1/17.
 */
public class LeaderboardsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(LeaderboardsActivity.this, MenuActivity.class);
        startActivity(i);
    }
}
