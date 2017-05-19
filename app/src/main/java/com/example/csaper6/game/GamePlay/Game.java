package com.example.csaper6.game.GamePlay;

/**
 * Created by csaper6 on 5/5/17.
 */
public class Game {
    private Player player;
    //saved data
    //"in game" Day counter
    //stats bars

    public Game (Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
