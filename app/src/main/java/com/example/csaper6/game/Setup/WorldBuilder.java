package com.example.csaper6.game.Setup;

import com.example.csaper6.game.Location;

import java.util.Random;

/**
 * Created by csaper6 on 5/15/17.
 */
public class WorldBuilder {
    private Location level[][];
    private int pX, pY;
    private Random randNum;


    public WorldBuilder(){
        level = new Location[8][8];
        randNum = new Random();
        generateBaseMap(8, 8);
        rubbleEdge(8,8);
        pX = 4;
        pY = 4;
        level[pX][pY].setPlayerLocation(true);
    }

    public WorldBuilder(int width, int height, long seed){
        level = new Location[width][height];
        randNum = new Random(seed);
        generateBaseMap(width, height);
        rubbleEdge(width,height);
        pX = 4;
        pY = 4;
        level[pX][pY].setPlayerLocation(true);
    }

    public WorldBuilder(int width, int height, int playerStartX, int playerStartY, long seed){
        level = new Location[width][height];
        randNum = new Random(seed);
        generateBaseMap(width, height);
        rubbleEdge(width,height);
        pX = playerStartX;
        pY = playerStartY;
        level[pX][pY].setPlayerLocation(true);
    }

    public void generateBaseMap(int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j % 7 == 0 && i % 7 == 0) {
                    level[j][i] = new Location(2, false);

                }else if (j % 7 == 0 && i % 7 != 0) {
                    level[j][i] = new Location(2, false, 1);

                }else if (j % 7 != 0 && i % 7 == 0) {
                    level[j][i] = new Location(2, false, 2);

                }else {
                    level[j][i] = new Location(1,false,randNum.nextInt(3));

                }

            }
        }
    }

    public void rubbleEdge(int width,int height){
        for(int i = 0; i < width; i++){
            level[i][0].setVariation(randNum.nextInt(6));
            level[i][0].setType(3);
            level[i][width-1].setVariation(randNum.nextInt(3));
            level[i][width-1].setType(3);
            if(randNum.nextInt(10) > 3){
                level[i][1].setVariation(randNum.nextInt(6));
                level[i][1].setType(3);
            }
            if(randNum.nextInt(10) > 3){
                level[i][width-2].setVariation(randNum.nextInt(6));
                level[i][width-2].setType(3);
            }
            if(randNum.nextInt(10) > 6){
                level[i][2].setVariation(randNum.nextInt(6));
                level[i][2].setType(3);
            }
            if(randNum.nextInt(10) > 6){
                level[i][width-3].setVariation(randNum.nextInt(6));
                level[i][width-3].setType(3);
            }
        }
        for(int i = 0; i < height; i++){
            level[0][i].setVariation(randNum.nextInt(6));
            level[0][i].setType(3);
            level[height-1][i].setVariation(randNum.nextInt(3));
            level[height-1][i].setType(3);
            if(randNum.nextInt(10) > 3){
                level[1][i].setVariation(randNum.nextInt(6));
                level[1][i].setType(3);
            }
            if(randNum.nextInt(10) > 3){
                level[height-2][i].setVariation(randNum.nextInt(6));
                level[height-2][i].setType(3);
            }
            if(randNum.nextInt(10) > 6){
                level[2][i].setVariation(randNum.nextInt(6));
                level[2][i].setType(3);
            }
            if(randNum.nextInt(10) > 6){
                level[height-3][i].setVariation(randNum.nextInt(6));
                level[height-3][i].setType(3);
            }
        }

    }

    public void generate6by6Building(int startX, int startY){

    }

    public Location[][] getLevel() {
        return level;
    }

    public int getPlayerX(){
        return pX;
    }

    public int getPlayerY(){
        return pY;
    }


}



