package com.example.csaper6.game.GamePlay.InventoryClasses;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by csaper6 on 5/9/17.
 */
public class FirstAid {
    private Map<String, Integer> firstAidList;

    public FirstAid() {
        firstAidList = new HashMap<>();
        firstAidList.put("old bandage", 1);
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

    @Override
    public String toString() {
        return firstAidList.keySet().toString();
    }
}
