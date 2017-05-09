package com.example.csaper6.game.GamePlay.InventoryClasses;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by csaper6 on 5/5/17.
 */
public class Food {
    private Map<String, Integer> foodsList;

    public Food() {
        foodsList = new HashMap<>();
        foodsList.put("apple", 10);
        foodsList.put("banana", 10);
        foodsList.put("apple", 10);
        foodsList.put("apple", 10);
        foodsList.put("apple", 10);
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

    @Override
    public String toString() {
        return foodsList.keySet().toString();
    }
}
