package com.example.csaper6.game.GamePlay;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by csaper6 on 5/5/17.
 */
public class Inventory {
    private Map<String, Integer> foodsList;
    private Map<String, Integer> firstAidList;
    private Map<String, Integer> weaponsList;

    public Inventory(){

        foodsList = new HashMap<>();
        foodsList.put("apple", 10);
        foodsList.put("banana", 10);
        foodsList.put("apple", 10);
        foodsList.put("apple", 10);
        foodsList.put("apple", 10);

        firstAidList = new HashMap<>();
        firstAidList.put("old bandage", 1);

        weaponsList = new HashMap<>();
        weaponsList.put("stick", 1);

    }

    public void addFood(String name, int nutrient) {
        foodsList.put(name, nutrient);
    }

    public void removeFood(String name) {
        foodsList.remove(name);
    }

    public int getNutrients(String name) {
        int nutrient = foodsList.get(name);
        return nutrient;
    }

    public void addFirstAid(String name, int health){
        firstAidList.put(name, health);
    }

    public void removeFirstAid(String name){
        firstAidList.remove(name);
    }

    public int getHealth(String name){
        int health =  firstAidList.get(name);
        return health;
    }

    public void addWeapon(String name, int damage){
        weaponsList.put(name, damage);
    }

    public void removeWeapon(String name){
        weaponsList.remove(name);
    }

    public int getDamage(String name){
        int damage =  weaponsList.get(name);
        return damage;
    }

    @Override
    public String toString() {
        return "Food: " + foodsList.keySet().toString() + "First Aid: " + firstAidList.keySet().toString() + "Weapons: " + weaponsList.keySet().toString();
    }

    public String[] getInventoryArray(){
        String s1 = toString();
        String s2 = "";

        for(int i = 0; i< s1.length(); i++){
            //check character if space or however it divides and add to array
        }


        String a[] = new String[10];
        return a;
    }
}


