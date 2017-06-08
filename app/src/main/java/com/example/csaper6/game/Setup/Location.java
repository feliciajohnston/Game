package com.example.csaper6.game.Setup;

import com.example.csaper6.game.GamePlay.Inventory;

/**
 * Created by csaper6 on 5/5/17.
 */
public class Location {
    private String type, flavorText,color = "#000000";
    private int typeID, variation, casheType;
    private char appearance, playerAppearance = '@';
    private boolean holdsPlayer, traversable;
    private Inventory Cashe;

    public Location(){
        typeID = 1;
        setType(typeID);
        holdsPlayer = false;
        Cashe = new Inventory();

    }
    public Location(int ID, boolean holdsPlayer){
        this.holdsPlayer = holdsPlayer;
        setType(ID);
        setPlayerLocation(holdsPlayer);
        Cashe = new Inventory();
    }

    public Location(int ID, boolean holdsPlayer, int variation){
        this.holdsPlayer = holdsPlayer;
        this.variation = variation;
        setType(ID);
        setPlayerLocation(holdsPlayer);
        Cashe = new Inventory();
    }


    /* NUMBERING SYSTEM FOR TILES
    000 == TERRAIN / FLORA / INFRASTRUCTURE
    100 == HOUSES
    200 == COMMERCIAL BUILDINGS
    300 == INDUSTRIAL BUILDINGS
        */
    public void setType(int ID){
        typeID = ID;
        switch (ID){
            case 001:
                type = "field";
                flavorText = "You are in an empty field";
                switch (variation){
                    case 1:
                        appearance = ':';
                        break;
                    case 2:
                        appearance = ',';
                        break;
                    default:
                        appearance = ';';
                        break;
                }
                color = "#93AA52";
                traversable = true;
                break;
            case 002:
                type = "road";
                flavorText = "You are on a deserted road";
                switch (variation){
                    case 1:
                        appearance = '¦';
                        break;
                    case 2:
                        appearance = '-';
                        break;
                    default:
                        appearance = '+';
                        break;
                }
                color = "#555555";
                traversable = true;
                break;
            case 003:
                type = "rubble";
                flavorText = "This once was a building";
                switch (variation){
                    case 1:
                        appearance = 'x';
                        break;
                    case 2:
                        appearance = '*';
                        break;
                    case 3:
                        appearance = '}';
                        break;
                    case 4:
                        appearance = '&';
                        break;
                    case 5:
                        appearance = '>';
                        break;
                    default:
                        appearance = 's';
                        break;
                }
                color = "#777F80";
                traversable = false;
                break;
            case 004:
                type = "lake";
                flavorText = "You are in shallow water";
                appearance = 'ω';
                color = "#080baf";
                traversable = true;
                break;
            case 101:
                switch (variation){
                    case 1:
                        type = "house blue";
                        flavorText = "You are in a blue house";
                        color = "#C4C3D8";
                        traversable = true;
                        appearance = 'H';
                        break;
                    case 2:
                        type = "house yellow";
                        flavorText = "You are in a yellow house";
                        color = "#D8D7C3";
                        traversable = true;
                        appearance = 'H';
                        break;
                    case 3:
                        type = "house brick";
                        flavorText = "You are in a brick house";
                        color = "#9E6D60";
                        traversable = true;
                        appearance = 'H';
                        break;

                    default:
                        type = "house pink";
                        flavorText = "You are in a pink house";
                        color = "#D1C5D6";
                        traversable = true;
                        appearance = 'H';
                        break;
                }
                break;
            case 301:
                switch (variation) {
                    default:
                        type = "factory brick";
                        flavorText = "You are in an abandoned factory";
                        color = "#9E6D60";
                        traversable = true;
                        appearance = 'F';
                        break;
                }
                break;
            case 302:
                switch (variation) {
                    default:
                        type = "warehouse brick";
                        flavorText = "You are in an abandoned warehouse";
                        color = "#9E6D60";
                        traversable = true;
                        appearance = 'W';
                        break;
                }
                break;
            default:
                type = "null";
                flavorText = "This location should not exist";
                appearance = ' ';
                color = "#000000";
                traversable = true;
                break;

        }
    }

    public void setPlayerLocation(boolean playerIsHere){
        if(playerIsHere){
            holdsPlayer = true;
            appearance = playerAppearance;
        }else{
            holdsPlayer = false;
            setType(typeID);
        }
    }

    public boolean getPlayerLocation(){
        return holdsPlayer;
    }

    public void setVariation(int variation){
        this.variation = variation;
    }

    public String getAppearance(){
        //return appearance;
        if(holdsPlayer){
            return "<font color='#dddddd'>" + appearance + "</font>";
        }else {
            return "<font color='" + color + "'>" + appearance + "</font>";
        }
    }

    public String getType(){
        return type;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public boolean getTraversable(){
        return traversable;
    }

    public void setCashe(int type, int value, String name){
        casheType = type;
        switch(type){
            case 1:
                Cashe.addFood(name, value);
                break;
            case 2:
                Cashe.addFirstAid(name, value);
                break;
            default:
                Cashe.addWeapon(name, value);
                break;
        }
    }

    public String getCasheName(){
        return Cashe.getInventoryArrayList().get(0);
    }

    public int getCasheType(){
        return casheType;
    }

    public int getCasheValue(){
        switch(casheType){
            case 1:
                return Cashe.getNutrients(Cashe.getInventoryArrayList().get(0));

            case 2:
                return Cashe.getHealth(Cashe.getInventoryArrayList().get(0));

            default:
                return Cashe.getDamage(Cashe.getInventoryArrayList().get(0));

        }
    }
}

