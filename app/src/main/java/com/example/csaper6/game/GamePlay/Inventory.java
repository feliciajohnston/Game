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
    private List<String> inventoryArray;

    public Inventory(){

        inventoryArray = new ArrayList<>();
        foodsList = new HashMap<>();
        firstAidList = new HashMap<>();
        weaponsList = new HashMap<>();
    }

    public void addFood(String name, int nutrient) {
        foodsList.put(name, nutrient);
        inventoryArray.add(name);
    }

    public void removeFood(String name) {
        foodsList.remove(name);
        inventoryArray.remove(name);
    }

    public int getNutrients(String name) {
        return foodsList.get(name);
    }

    public void addFirstAid(String name, int health){
        firstAidList.put(name, health);
        inventoryArray.add(name);
    }

    public void removeFirstAid(String name){
        inventoryArray.remove(name);
    }

    public int getHealth(String name){
        return firstAidList.get(name);
    }

    public void addWeapon(String name, int damage){
        weaponsList.put(name, damage);
        inventoryArray.add(name);
    }

    public void removeWeapon(String name){
        weaponsList.remove(name);
        inventoryArray.remove(name);
    }

    public int getDamage(String name){
        return weaponsList.get(name);
    }

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

    public List<String> getInventoryArrayList(){
        return inventoryArray;
    }

}


