package com.example.csaper6.game.Setup;

import java.util.Random;

/**
 * Created by csaper6 on 5/15/17.
 */
public class WorldBuilder {
    private Location level[][];
    private int pX, pY;
    private Random randNum;


    public WorldBuilder() {
        level = new Location[32][32];
        randNum = new Random();
        generateBaseMap(32, 32);

        generateCity(32, 32);

        generateRubbleEdge(32, 32);
        pX = 4;
        pY = 4;
        level[pX][pY].setPlayerLocation(true);
    }

    public WorldBuilder(int width, int height, long seed) {
        level = new Location[width][height];
        randNum = new Random(seed);
        generateBaseMap(width, height);

        generateCity(width, height);

        generateRubbleEdge(width, height);
        pX = 4;
        pY = 4;
        level[pX][pY].setPlayerLocation(true);
    }

    public WorldBuilder(int width, int height, int playerStartX, int playerStartY, long seed) {
        level = new Location[width][height];
        randNum = new Random(seed);
        generateBaseMap(width, height);

        generateCity(width, height);

        generateRubbleEdge(width, height);
        pX = playerStartX;
        pY = playerStartY;
        level[pX][pY].setPlayerLocation(true);
    }

    public void generateBaseMap(int width, int height) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (j % 7 == 0 && i % 7 == 0) {
                    level[j][i] = new Location(2, false);

                } else if (j % 7 == 0 && i % 7 != 0) {
                    level[j][i] = new Location(2, false, 1);

                } else if (j % 7 != 0 && i % 7 == 0) {
                    level[j][i] = new Location(2, false, 2);

                } else {
                    level[j][i] = new Location(1, false, randNum.nextInt(3));

                }


            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                generateCashe(i, j);
            }
        }
    }

    public void generateRubbleEdge(int width, int height) {
        for (int i = 0; i < width; i++) {
            level[i][0].setVariation(randNum.nextInt(6));
            level[i][0].setType(3);
            level[i][width - 1].setVariation(randNum.nextInt(3));
            level[i][width - 1].setType(3);
            if (randNum.nextInt(10) > 3) {
                level[i][1].setVariation(randNum.nextInt(6));
                level[i][1].setType(3);
            }
            if (randNum.nextInt(10) > 3) {
                level[i][width - 2].setVariation(randNum.nextInt(6));
                level[i][width - 2].setType(3);
            }
            if (randNum.nextInt(10) > 6) {
                level[i][2].setVariation(randNum.nextInt(6));
                level[i][2].setType(3);
            }
            if (randNum.nextInt(10) > 6) {
                level[i][width - 3].setVariation(randNum.nextInt(6));
                level[i][width - 3].setType(3);
            }
        }
        for (int i = 0; i < height; i++) {
            level[0][i].setVariation(randNum.nextInt(6));
            level[0][i].setType(3);
            level[height - 1][i].setVariation(randNum.nextInt(3));
            level[height - 1][i].setType(3);
            if (randNum.nextInt(10) > 3) {
                level[1][i].setVariation(randNum.nextInt(6));
                level[1][i].setType(3);
            }
            if (randNum.nextInt(10) > 3) {
                level[height - 2][i].setVariation(randNum.nextInt(6));
                level[height - 2][i].setType(3);
            }
            if (randNum.nextInt(10) > 6) {
                level[2][i].setVariation(randNum.nextInt(6));
                level[2][i].setType(3);
            }
            if (randNum.nextInt(10) > 6) {
                level[height - 3][i].setVariation(randNum.nextInt(6));
                level[height - 3][i].setType(3);
            }
        }

    }

    public void generateHousingBlock(int startX, int startY) {
        for (int i = startX; i < startX + 6; i++) {
            for (int j = startY; j < startY + 6; j++) {
                if ((i == startX && j % (randNum.nextInt(3) + 1) == 0) || (i == startX + 5 && j % (randNum.nextInt(3) + 1) == 0) || (j == startY && i % (randNum.nextInt(3) + 1) == 0) || (j == startY + 5 && i % (randNum.nextInt(3) + 1) == 0)) {
                    level[i][j].setVariation(randNum.nextInt(4));
                    level[i][j].setType(101);
                }
            }
        }
        if (randNum.nextInt(10) < 7) {
            generateLake(startX + randNum.nextInt(2) + 1, startY + randNum.nextInt(2) + 1, randNum.nextInt(3) + 1, randNum.nextInt(3) + 1);
        }
    }

    public void generateIndustrialBlock(int startX, int startY) {
        int width = 0;
        int height = 0;

        width = randNum.nextInt(3);
        height = randNum.nextInt(3);
        generateFactory(startX, startY, width, height, randNum.nextInt(2));

        width = randNum.nextInt(4);
        height = randNum.nextInt(3);
        generateFactory(startX + 6 - width, startY, width, height, randNum.nextInt(2));

        width = randNum.nextInt(4);
        height = randNum.nextInt(3);
        generateFactory(startX, startY + 6 - height, width, height, randNum.nextInt(2));

        width = randNum.nextInt(4);
        height = randNum.nextInt(4);
        generateFactory(startX + 6 - width, startY + 6 - height, width, height, randNum.nextInt(2));


    }

    public void generateFactory(int startX, int startY, int width, int height, int type) {
        for (int i = startX; i < startX + width; i++) {
            for (int j = startY; j < startY + height; j++) {
                level[i][j].setType(type + 301);
            }
        }
    }

    public void generateLake(int startX, int startY, int width, int height) {
        for (int i = startX; i < startX + width; i++) {
            for (int j = startY; j < startY + height; j++) {
                level[i][j].setType(4);
            }
        }
    }

    public void generateCity(int width, int height) {
        int blockType = 0;
        for (int i = 1; i < height; i += 7) {
            for (int j = 1; j < width; j += 7) {
                blockType = randNum.nextInt(2);
                switch (blockType) {
                    case 1:
                        generateIndustrialBlock(i, j);
                        break;
                    default:
                        generateHousingBlock(i, j);
                        break;
                }

            }
        }

    }

    public Location[][] getLevel() {
        return level;
    }

    public int getPlayerX() {
        return pX;
    }

    public int getPlayerY() {
        return pY;
    }

    public void generateCashe(int x, int y) {
        int a = randNum.nextInt(3);
        switch(a){
            case 1:
                level[x][y].setCashe(1, 2, "water");
                break;
            case 2:
                level[x][y].setCashe(1, 0, "moldy slice of bread");
                break;
            default:
                level[x][y].setCashe(1, 20, "can of beans");
                break;

        }



    }


}
