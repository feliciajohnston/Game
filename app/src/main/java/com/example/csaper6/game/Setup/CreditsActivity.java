package com.example.csaper6.game.Setup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.csaper6.game.R;

/**
 * Created by csaper6 on 5/3/17.
 */
public class CreditsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);


        TextView creditsText = (TextView) findViewById(R.id.textView_credits);
        String credits = "\n Developers: \n Riley Johnston \n Case Willems \n " +
                "Art: \n opengameart.org \n @jkjkke \n";
        creditsText.setText(credits);
    }
}
