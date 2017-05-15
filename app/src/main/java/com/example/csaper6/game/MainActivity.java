package com.example.csaper6.game;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.csaper6.game.GamePlay.Player;
import com.example.csaper6.game.Setup.WorldBuilder;

import java.util.Arrays;

import static android.text.Html.fromHtml;

public class MainActivity extends AppCompatActivity {

    private Location level[][]; //= new Location[120][24];
    private Button left, right, up, down, interact, start;
    private TextView textView0, logCurrent, logPrevious, logOverprevious;
    private int playerSpotX, playerSpotY, screenWidth = 26, screenHeight = 8; //screenWidth or height should be even
    private WorldBuilder WorldGen;
    private Player player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        player = new Player();
        player.getInventory().addWeapon("stick", 5, 0);
        player.getInventory().addFirstAid("old bandage", 1, 1);
        player.getInventory().addFood("apple", 10, 2);


        WorldGen = new WorldBuilder(64, 64, 4, 4, 1);//MUST be a multiple of 8
        level = WorldGen.getLevel();
        playerSpotX = WorldGen.getPlayerX();
        playerSpotY = WorldGen.getPlayerY();


        //wiring widgets


        start = (Button) findViewById(R.id.button_start);
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
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Inventory")
                        .setItems(player.getInventory().getInventoryArray(), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String s = player.getInventory().findAtPosition(which);
                                switch(s){
                                    case "apple":
                                        AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
                                        alert.setTitle("Do you want to use the apple?");
                                        alert.setButton(AlertDialog.BUTTON_POSITIVE, "Yes.",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        //use apple
                                                        player.getInventory().removeFood("apple", which);

                                                        //TODO: REMOVE ITEM, SET NEW BAR VALUES, ETC.
                                                    }
                                                });
                                        break;
                                    case "old bandage":
                                        AlertDialog alert2 = new AlertDialog.Builder(MainActivity.this).create();
                                        alert2.setTitle("Do you want to use the old bandage?");
                                        alert2.setButton(AlertDialog.BUTTON_POSITIVE, "Yes.",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        //use apple
                                                    }
                                                });
                                        //use
                                        player.getInventory().removeFirstAid("old Bandage", which);
                                        break;
                                    case "stick":
                                        AlertDialog alert3 = new AlertDialog.Builder(MainActivity.this).create();
                                        alert3.setTitle("Do you want to equip the stick?");
                                        alert3.setButton(AlertDialog.BUTTON_POSITIVE, "Yes.",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        //use apple
                                                    }
                                                });
                                        //use
                                        player.getInventory().removeWeapon("stick", which);
                                        break;
                                }

                            }
                        });
                builder.show();

                Log.d("TAG", "LOOK HERE:" + Arrays.toString(player.getInventory().getInventoryArray()));

            }
        });
        interact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interact();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level[playerSpotX - 1][playerSpotY].getTraversable()) {
                    moveTextHorizontal(false);
                    updateMap();
                }
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level[playerSpotX + 1][playerSpotY].getTraversable()) {
                    moveTextHorizontal(true);
                    updateMap();
                }

            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level[playerSpotX][playerSpotY - 1].getTraversable()) {
                    moveTextVertical(false);
                    updateMap();
                }

            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level[playerSpotX][playerSpotY + 1].getTraversable()) {
                    moveTextVertical(true);
                    updateMap();
                }


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

