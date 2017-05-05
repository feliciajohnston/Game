package com.example.csaper6.game;

/**
 * Created by csaper6 on 5/5/17.
 */
public class Location {
    private String type, flavorText;
    private int typeID;



    private char appearance, playerAppearance = '@';
    private boolean holdsPlayer;

    public Location(){
        typeID = 1;
        setType(typeID);
        holdsPlayer = false;
    }
    public Location(int ID, boolean holdsPlayer){
        this.holdsPlayer = holdsPlayer;
        typeID = ID;
        setType(typeID);
    }

    void setType(int ID){

        if(ID == 1){
            type = "field";
            flavorText = "You are in an empty field";
            appearance = '#';
        }else if(ID == 2){
            type = "road";
            flavorText = "You are on a deserted road";
            appearance = '+';
        }else{
            type = "null";
            flavorText = "This location should not exist";
            appearance = ' ';
        }
    }

    void setPlayerLocation(boolean playerIsHere){
        if(playerIsHere){
            holdsPlayer = true;
            appearance = playerAppearance;
        }else{
            holdsPlayer = false;
            setType(typeID);
        }
    }

    boolean getPlayerLocation(){
        return holdsPlayer;
    }

    char getAppearance(){
        return appearance;
    }

    String getType(){
        return type;
    }

    String getFlavorText() {
        return flavorText;
    }

}

