package com.example.csaper6.game.GamePlay;

/**
 * Created by csaper6 on 5/9/17.
 */
public class Player {
    private Inventory inventory;
    private int health, nutrients;
    private String equippedWeapon;
    private int weaponDamage;


    public Player (){
        inventory = new Inventory();
        equippedWeapon = "";
        health = 100;
        nutrients = 100;
        weaponDamage = 0;
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

    public void addHealth(int i){
        health += i;
    }

    public void addNutrients(int i){
        nutrients += i;
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


}
