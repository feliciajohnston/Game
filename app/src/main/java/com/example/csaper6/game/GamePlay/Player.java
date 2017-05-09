package com.example.csaper6.game.GamePlay;

/**
 * Created by csaper6 on 5/9/17.
 */
public class Player {
    private Inventory inventory;
    private int health, food;


    public Player (){
        inventory = new Inventory();
        health = 100;
        food = 100;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public int getHealth(){
        return health;
    }

    public int getFood(){
        return food;
    }


}
