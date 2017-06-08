package com.example.csaper6.game.GamePlay;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csaper6.game.R;
import com.example.csaper6.game.Setup.Location;
import com.example.csaper6.game.Setup.MenuActivity;
import com.example.csaper6.game.Setup.WorldBuilder;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static android.text.Html.fromHtml;

public class MainActivity extends AppCompatActivity {

    private Location level[][]; //= new Location[120][24];
    private Button left, right, up, down, interact, start, save;
    private TextView textView0, logCurrent, logPrevious, logOverprevious, healthBar, nutrientsBar, weaponEquipped;
    private int  playerSpotX, playerSpotY,screenWidth = 26, screenHeight = 8; //screenWidth or height should be even
    private WorldBuilder WorldGen;
    private Player player;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = new Player();
        WorldGen = new WorldBuilder(64, 64, 4, 4, 1);//MUST be a multiple of 8
        level = WorldGen.getLevel();
        playerSpotX = WorldGen.getPlayerX();
        playerSpotY = WorldGen.getPlayerY();


        //wiring widgets

        save = (Button) findViewById(R.id.button_save);
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
        healthBar = (TextView) findViewById(R.id.textView_healthBar);
        nutrientsBar = (TextView) findViewById(R.id.textView_nutrientsBar);
        weaponEquipped = (TextView) findViewById(R.id.textView_weapon);

        gson = new Gson();
        
        Intent i = getIntent();
        if(i.getIntExtra("gameType", 1) == 0){
            // load game
            try {
                String filePath = getFilesDir().getPath().toString() + "playersaved.json";
                File file = new File(filePath);
                FileReader reader = new FileReader(file);
                player = gson.fromJson(reader, Player.class);
                setBars();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "NO SAVE FILE FOUND", Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(i2);
            }
        }
        else{
            //new player
            player.getInventory().addWeapon("stick", 5);
            player.getInventory().addFirstAid("old bandage", 1);
            player.getInventory().addFood("apple", 10);
        }


        updateMap();


        //set the actions of the buttons
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String filePath = getFilesDir().getPath().toString() + "playersaved.json";
                File file = new File(filePath);
                try (FileWriter writer = new FileWriter(file)) {
                    gson.toJson(player, writer);
                    Toast.makeText(MainActivity.this, "FILE SAVED", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Inventory")
                        .setItems(player.getInventory().getInventoryArray(), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                String s = player.getInventory().getInventoryArrayList().get(which);
                                //Log.d("MENU TESTING", "YOU CLICKED " + s);
                                switch(s){
                                    case "apple":
                                        AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
                                        alert.setTitle("Do you want to use the apple?");
                                        alert.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        //use apple
                                                        //Log.d("TAG", "1 LOOK HERE: " + player.getNutrientLevel());
                                                        player.addNutrients(player.getInventory().getNutrients("apple"));
                                                        player.getInventory().removeFood("apple");
                                                        //Log.d("TAG", "2 LOOK HERE: " + player.getNutrientLevel());
                                                        setBars();
                                                    }
                                                });
                                        alert.setButton(AlertDialog.BUTTON_NEGATIVE, "No",  new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {}});
                                        alert.show();
                                        break;
                                    case "old bandage":
                                        AlertDialog alert2 = new AlertDialog.Builder(MainActivity.this).create();
                                        alert2.setTitle("Do you want to use the old bandage?");
                                        alert2.setButton(AlertDialog.BUTTON_POSITIVE, "Yes.",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        //use
                                                       // Log.d("TAG", "1 LOOK HERE: " + player.getHealthLevel());
                                                        player.addHealth(player.getInventory().getHealth("old bandage"));
                                                        player.getInventory().removeFirstAid("old bandage");
                                                       // Log.d("TAG", "2 LOOK HERE: " + player.getHealthLevel());
                                                        setBars();
                                                    }
                                                });
                                        alert2.setButton(AlertDialog.BUTTON_NEGATIVE, "No",  new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {}});
                                        alert2.show();
                                        break;
                                    case "stick":
                                        AlertDialog alert3 = new AlertDialog.Builder(MainActivity.this).create();
                                        alert3.setTitle("Do you want to equip the stick?");
                                        alert3.setButton(AlertDialog.BUTTON_POSITIVE, "Yes.",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        //use
                                                        //Log.d("TAG", "1 LOOK HERE: " + player.getEquippedWeaponName());
                                                        player.setEquippedWeapon("stick", player.getInventory().getDamage("stick"));
                                                        player.getInventory().removeWeapon("stick");
                                                        //Log.d("TAG", "2 LOOK HERE: " + player.getEquippedWeaponName());
                                                        setBars();
                                                    }
                                                });
                                        alert3.setButton(AlertDialog.BUTTON_NEGATIVE, "No",  new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {}});
                                        alert3.show();
                                        break;
                                    case "water":
                                        AlertDialog alert4 = new AlertDialog.Builder(MainActivity.this).create();
                                        alert4.setTitle("Do you want to use the water?");
                                        alert4.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        //use apple
                                                        //Log.d("TAG", "1 LOOK HERE: " + player.getNutrientLevel());
                                                        player.addNutrients(player.getInventory().getNutrients("water"));
                                                        player.getInventory().removeFood("water");
                                                        //Log.d("TAG", "2 LOOK HERE: " + player.getNutrientLevel());
                                                        setBars();
                                                    }
                                                });
                                        alert4.setButton(AlertDialog.BUTTON_NEGATIVE, "No",  new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {}});
                                        alert4.show();
                                        break;
                                    case "can of beans":
                                        AlertDialog alert5 = new AlertDialog.Builder(MainActivity.this).create();
                                        alert5.setTitle("Do you want to use the beans?");
                                        alert5.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        //use apple
                                                        //Log.d("TAG", "1 LOOK HERE: " + player.getNutrientLevel());
                                                        player.addNutrients(player.getInventory().getNutrients("can of beans"));
                                                        player.getInventory().removeFood("can of beans");
                                                        //Log.d("TAG", "2 LOOK HERE: " + player.getNutrientLevel());
                                                        setBars();
                                                    }
                                                });
                                        alert5.setButton(AlertDialog.BUTTON_NEGATIVE, "No",  new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {}});
                                        alert5.show();
                                        break;
                                    case "moldy slice of bread":
                                        AlertDialog alert6 = new AlertDialog.Builder(MainActivity.this).create();
                                        alert6.setTitle("Do you want to use the moldy bread?");
                                        alert6.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        //use apple
                                                        //Log.d("TAG", "1 LOOK HERE: " + player.getNutrientLevel());
                                                        player.addNutrients(player.getInventory().getNutrients("moldy slice of bread"));
                                                        player.getInventory().removeFood("moldy slice of bread");
                                                        //Log.d("TAG", "2 LOOK HERE: " + player.getNutrientLevel());
                                                        setBars();
                                                    }
                                                });
                                        alert6.setButton(AlertDialog.BUTTON_NEGATIVE, "No",  new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {}});
                                        alert6.show();
                                        break;
                                }
                            }
                        });
                builder.show();

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
                    checkMoves();
                }
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level[playerSpotX + 1][playerSpotY].getTraversable()) {
                    moveTextHorizontal(true);
                    updateMap();
                    checkMoves();
                }

            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level[playerSpotX][playerSpotY - 1].getTraversable()) {
                    moveTextVertical(false);
                    updateMap();
                    checkMoves();
                }

            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level[playerSpotX][playerSpotY + 1].getTraversable()) {
                    moveTextVertical(true);
                    updateMap();
                    checkMoves();
                }


            }
        });
    }

    private void checkMoves() {
        if(player.getNumMoves() >= 5){
            player.setNumMoves(0);
            player.removeNutrients(5);
            setBars();
        }
        else if(player.getNutrientLevel() < 5)
        {
            killPlayer();
        }
        else{
            player.setNumMoves(player.getNumMoves() + 1);
        }
    }

    private void killPlayer() {
        Toast.makeText(MainActivity.this, "YOU HAVE DIED OF [STARVATION]", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(i);

    }

    private void setBars() {
        nutrientsBar.setText("Nutrients: " + player.getNutrientLevel() + "/100");
        weaponEquipped.setText("Equipped: " + player.getEquippedWeaponName() + " +" +player.getWeaponDamage());
        healthBar.setText("Health: " + player.getHealthLevel() + "/100");
    }

    //interact with whatever the player is currently above
    void interact() {
        updateLog(level[playerSpotX][playerSpotY].getFlavorText());
        boolean hasItem = false;
        for(int i =0; i < player.getInventory().getInventoryArray().length; i++) {
            if (player.getInventory().getInventoryArray()[i].equals(level[playerSpotX][playerSpotY].getCasheName()))
                hasItem = true;
        }
        if(!hasItem) {
            updateLog("You found a " + level[playerSpotX][playerSpotY].getCasheName());
            switch (level[playerSpotX][playerSpotY].getCasheType()) {
                case 1:
                    player.getInventory().addFood(level[playerSpotX][playerSpotY].getCasheName(), level[playerSpotX][playerSpotY].getCasheValue());
                    break;
                case 2:
                    player.getInventory().addFirstAid(level[playerSpotX][playerSpotY].getCasheName(), level[playerSpotX][playerSpotY].getCasheValue());
                    break;
                default:
                    player.getInventory().addWeapon(level[playerSpotX][playerSpotY].getCasheName(), level[playerSpotX][playerSpotY].getCasheValue());
                    break;
            }
        }

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
    @Override
    public void onBackPressed(){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("WARNING: CHECK SAVE FILE");
        alertDialog.setMessage("Are you sure you want to exit the game?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes, I'm sure.",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent i = new Intent(MainActivity.this, MenuActivity.class);
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


//NON GAME LIST
//TODO: implement reset button
//TODO: icon
//TODO: SFX and music capabilities

//GAME LIST

//EXTRA? STRETCH GOAL LIST
//TODO: possibly setup leaderboards?
//TODO: possibly setup connect to social media to set leaderboards?

