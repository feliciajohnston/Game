package com.example.csaper6.game.GamePlay;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by csaper6 on 5/9/17.
 */
public class Player implements Parcelable {
    private Inventory inventory;
    private int health;
    private int nutrients;
    int spotX, spotY;
    private String equippedWeapon;
    private int weaponDamage;
    private int numMoves;


    public Player (){
        inventory = new Inventory();
        equippedWeapon = "";
        health = 100;
        nutrients = 100;
        weaponDamage = 0;
    }

    public int getNumMoves() {
        return numMoves;
    }

    public void setNumMoves(int numMoves) {
        this.numMoves = numMoves;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public int getHealthLevel(){
        return health;
    }

    public int getNutrientLevel(){
        return nutrients;
    }

    public int getSpotY() {
        return spotY;
    }

    public void setSpotY(int spotY) {
        this.spotY = spotY;
    }

    public int getSpotX() {
        return spotX;
    }

    public void setSpotX(int spotX) {
        this.spotX = spotX;
    }


    public void addHealth(int i){
        if (health < 100 - i) {
            health += i;
        }
        else if(health < 100){
            health = 100;
        }
    }

    public void removeNutrients(int i){
        nutrients = nutrients - i;
    }
    public void addNutrients(int i){
        if (nutrients < 100 - i) {
            nutrients += i;
        }
        else if(nutrients < 100){
            nutrients = 100;
        }
    }

    public void setEquippedWeapon(String s, int i){
        equippedWeapon = s;
        weaponDamage = i;
    }

    public void unequipWeapon(){
        equippedWeapon = "";
        weaponDamage = 0;
    }

    public String getEquippedWeaponName(){
        return equippedWeapon;
    }

    public int getWeaponDamage(){
        return weaponDamage;
    }


    protected Player(Parcel in) {
        inventory = (Inventory) in.readValue(Inventory.class.getClassLoader());
        health = in.readInt();
        nutrients = in.readInt();
        equippedWeapon = in.readString();
        weaponDamage = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(inventory);
        dest.writeInt(health);
        dest.writeInt(nutrients);
        dest.writeString(equippedWeapon);
        dest.writeInt(weaponDamage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

}