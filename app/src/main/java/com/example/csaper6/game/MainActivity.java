package com.example.csaper6.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.text.Html.fromHtml;

public class MainActivity extends AppCompatActivity {

    private Location level[][] = new Location[120][24];
    private Button left, right, up, down, interact;
    private TextView textView0, logCurrent, logPrevious, logOverprevious;
    private int playerSpotX = 0, playerSpotY = 0, screenWidth = 26, screenHeight = 8; //screenWidth or height should be even


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Setting up the array (map)
        for (int i = 0; i < level[0].length; i++) {
            for (int j = 0; j < level.length; j++) {
                if (j % 8 == 0 || i % 6 == 0) {
                    level[j][i] = new Location(2, false);

                } else {
                    level[j][i] = new Location();

                }

            }
        }

        level[0][0].setPlayerLocation(true);

        //wiring widgets


        left = (Button) findViewById(R.id.button_left);
        right = (Button) findViewById(R.id.button_right);
        up = (Button) findViewById(R.id.button_up);
        down = (Button) findViewById(R.id.button_down);
        interact = (Button) findViewById(R.id.button_interact);
        textView0 = (TextView) findViewById(R.id.text_view_map);
        logCurrent = (TextView) findViewById(R.id.text_view_log_current);
        logPrevious = (TextView) findViewById(R.id.text_view_log_previous);
        logOverprevious = (TextView) findViewById(R.id.text_view_log_overprevious);

        updateMap();


        //set the actions of the buttons
        interact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interact();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTextHorizontal(false);
                updateMap();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTextHorizontal(true);
                updateMap();

            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTextVertical(false);
                updateMap();

            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTextVertical(true);
                updateMap();


            }
        });


    }

    //interact with whatever the player is currently above
    void interact() {
        updateLog(level[playerSpotX][playerSpotY].getFlavorText());
    }

    //move the player left or right a column
    void moveTextHorizontal(boolean right) {
        if (right && playerSpotX < level.length - 1) {
            level[playerSpotX][playerSpotY].setPlayerLocation(false);
            level[playerSpotX + 1][playerSpotY].setPlayerLocation(true);
            playerSpotX++;
        } else if (playerSpotX > 0 && !right) {
            level[playerSpotX][playerSpotY].setPlayerLocation(false);
            level[playerSpotX - 1][playerSpotY].setPlayerLocation(true);
            playerSpotX--;
        }

    }

    //move the player up or down a row
    void moveTextVertical(boolean down) {
        if (down && playerSpotY < level[playerSpotX].length - 1) {
            level[playerSpotX][playerSpotY].setPlayerLocation(false);
            level[playerSpotX][playerSpotY + 1].setPlayerLocation(true);
            playerSpotY++;
        } else if (!down && playerSpotY > 0) {
            level[playerSpotX][playerSpotY].setPlayerLocation(false);
            level[playerSpotX][playerSpotY - 1].setPlayerLocation(true);
            playerSpotY--;
        }

    }

    //Set the text thereby making the player's position visible
    void updateMap() {
        String temp = "";
        int widthRemainingRight = screenWidth / 2, widthRemainingLeft = screenWidth / 2, heightRemainingBot = screenHeight / 2, heightRemainingTop = screenHeight / 2;

        if (playerSpotX < screenWidth / 2) {
            widthRemainingRight = screenWidth - playerSpotX;
        }
        if (playerSpotX > level.length - screenWidth / 2) {
            widthRemainingLeft = screenWidth - (level.length - playerSpotX);
        }
        if (playerSpotY < screenHeight / 2) {
            heightRemainingBot = screenHeight - playerSpotY;
        }
        if (playerSpotY > level[0].length - screenHeight / 2) {
            heightRemainingTop = screenHeight - (level[0].length - playerSpotY);
        }

        for (int i = (playerSpotY > heightRemainingTop ? playerSpotY - heightRemainingTop : 0); i < (playerSpotY < level[0].length - heightRemainingBot ? playerSpotY + heightRemainingBot : level[0].length); i++) {
            for (int j = (playerSpotX > widthRemainingLeft ? playerSpotX - widthRemainingLeft : 0); j < (playerSpotX < level.length - widthRemainingRight ? playerSpotX + widthRemainingRight : level.length); j++) {
                temp += level[j][i].getAppearance();
            }
            temp += "<br>";
        }
        //textView0.setText(temp);
        textView0.setText(fromHtml(temp), TextView.BufferType.SPANNABLE);
    }

    void updateLog(String message) {
        logOverprevious.setText(logPrevious.getText());
        logPrevious.setText(logCurrent.getText());
        logCurrent.setText(message);
    }

//NON GAME LIST
    //TODO: start new game
    //TODO: save file
    //TODO: load saved game
    //TODO: implement reset button
    //TODO: icon
    //TODO: credits
    //TODO: SFX and music capabilities

    //GAME LIST

    //EXTRA? STRETCH GOAL LIST
    //TODO: possibly setup leaderboards?
    //TODO: possibly setup connect to social media to set leaderboards?
}
