package com.example.csaper6.game;

import android.util.Log;

/**
 * Created by csaper6 on 5/5/17.
 */
public class Food {
    private String[][] foodsList;

    public Food() {
        foodsList = new String[][]{
                {"Apple","5"},
                {"Water", "10"}
        };
    }

    //doesnt work yet
    public int getNutrients(String food){
        int nutrient = 0;
        for(int i = 0; i < foodsList.length; i++){
            if(food.equals(foodsList[i].toString())){
                nutrient =  Integer.parseInt(foodsList[i][1].toString());
            }

        }
        Log.d("TAG", "HERE: " + nutrient);
        return nutrient;
    }
}
