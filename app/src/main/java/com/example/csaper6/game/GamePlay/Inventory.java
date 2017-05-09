package com.example.csaper6.game.GamePlay;

import com.example.csaper6.game.GamePlay.InventoryClasses.FirstAid;
import com.example.csaper6.game.GamePlay.InventoryClasses.Food;
import com.example.csaper6.game.GamePlay.InventoryClasses.Weapons;

/**
 * Created by csaper6 on 5/5/17.
 */
public class Inventory {
    private Food f;
    private Weapons w;
    private FirstAid fa;

    public Inventory(){
        f = new Food();
        w = new Weapons();
        fa = new FirstAid();
    }

    public String getFoodList(){
        return f.toString();
    }

    public String getWeaponsList(){
        return w.toString();
    }

    public String getFirstAidList(){
        return fa.toString();
    }

    public Food getFood(){
        return f;
    }

    public Weapons getWeapon(){
        return w;
    }

    public FirstAid getFirstAid(){
        return fa;
    }


}
