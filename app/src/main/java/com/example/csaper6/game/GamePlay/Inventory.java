package com.example.csaper6.game.GamePlay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by csaper6 on 5/5/17.
 */
public class Inventory {
    private Map<String, Integer> foodsList;
    private Map<String, Integer> firstAidList;
    private Map<String, Integer> weaponsList;
    private Map<Integer, String> inventoryKey;
    private List<String> inventoryArray;

    public Inventory(){

        inventoryKey = new HashMap<>();
        inventoryArray = new ArrayList<>();
        foodsList = new HashMap<>();
        firstAidList = new HashMap<>();
        weaponsList = new HashMap<>();
    }

    public void addFood(String name, int nutrient, int placement) {
        foodsList.put(name, nutrient);
        inventoryArray.add(name);
        inventoryKey.put(placement, name);
    }

    public void removeFood(String name, int placement) {
        foodsList.remove(name);
        inventoryArray.remove(name);
        inventoryKey.remove(placement);
    }

    public int getNutrients(String name) {
        int nutrient = foodsList.get(name);
        return nutrient;
    }

    public void addFirstAid(String name, int health, int placement){
        firstAidList.put(name, health);
        inventoryArray.add(name);
        inventoryKey.put(placement, name);
    }

    public void removeFirstAid(String name, int placement){
        firstAidList.remove(name);
        inventoryArray.remove(name);
        inventoryKey.remove(placement);
    }

    public int getHealth(String name){
        int health =  firstAidList.get(name);
        return health;
    }

    public void addWeapon(String name, int damage, int placement){
        weaponsList.put(name, damage);
        inventoryArray.add(name);
        inventoryKey.put(placement, name);
    }

    public void removeWeapon(String name, int placement){
        weaponsList.remove(name);
        inventoryArray.remove(name);
        inventoryKey.remove(placement);
    }

    public int getDamage(String name){
        int damage =  weaponsList.get(name);
        return damage;
    }

    //@Override
   // public String toString() {
    //    return "Food: " + foodsList.keySet().toString() + "First Aid: " + firstAidList.keySet().toString() + "Weapons: " + weaponsList.keySet().toString();
    //}

    public String[] getInventoryArray(){
      return inventoryArray.toArray(new String[checkArray(inventoryArray)]);
    }

    public int checkArray(List<String> list){
        int num = 0;
        for(int i = 0; i<list.size(); i++){
            if(!(list.get(i) == null)){
                num++;
            }
        }
        return num;
    }

    public String findAtPosition(int pos){
        return inventoryKey.get(pos);
    }
}


