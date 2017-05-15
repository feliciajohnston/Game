package com.example.csaper6.game;

/**
 * Created by csaper6 on 5/5/17.
 */
public class Location {
    private String type, flavorText,color = "#000000";;
    private int typeID, variation;
    private char appearance, playerAppearance = '@';
    private boolean holdsPlayer, traversable;

    public Location(){
        typeID = 1;
        setType(typeID);
        holdsPlayer = false;

    }
    public Location(int ID, boolean holdsPlayer){
        this.holdsPlayer = holdsPlayer;
        setType(ID);
        setPlayerLocation(holdsPlayer);
    }

    public Location(int ID, boolean holdsPlayer, int variation){
        this.holdsPlayer = holdsPlayer;
        this.variation = variation;
        setType(ID);
        setPlayerLocation(holdsPlayer);
    }


    /* NUMBERING SYSTEM FOR TILES
    000 == TERRAIN / FLORA / INFRASTRUCTURE
    100 == HOUSES
    200 == COMMERCIAL BUILDINGS
    300 ==

     */
    public void setType(int ID){
        typeID = ID;
        switch (ID){
            case 1:
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
            case 2:
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
            case 3:
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
            case 204:
                type = "house pink";
                flavorText = "This house is pink";
                switch (variation){
                    default:
                        appearance = 'H';
                        break;
                }
                color = "#A874AF";
                traversable = false;
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

}





