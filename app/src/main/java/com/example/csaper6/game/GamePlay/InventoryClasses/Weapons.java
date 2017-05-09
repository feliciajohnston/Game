package com.example.csaper6.game.GamePlay.InventoryClasses;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by csaper6 on 5/5/17.
 */
public class Weapons {
    private Map<String, Integer> weaponsList;

    public Weapons() {
        weaponsList = new HashMap<>();
        weaponsList.put("stick", 1);
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
        return weaponsList.keySet().toString();
    }
}
