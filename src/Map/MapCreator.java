package Map;

import java.awt.*;
import java.awt.image.BufferedImage;

import Sprites.SpriteBase;
import Sprites.SpriteResourceManager;
import Sprites.Tiles.*;


public class MapCreator {
    
    private String[] map;
    private Tile[][] tileMap;
    private int numOfWaves;

    public MapCreator(String[] map, int numOfWaves) {
        
        this.map = map;
        this.numOfWaves = numOfWaves;

        tileMap = new Tile[map.length][map[0].length()];
        setTileMap();

    }

    public void drawMap(Graphics2D g2) {

        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                tileMap[i][j].draw(g2);
            }
        }

    }

    public void setTileMap() {
        for (int j = 0; j < map.length; j ++) {
            for (int i = 1; i <= map[j].length(); i++) {

                if(map[j].charAt(i - 1) == 'X') {
                    BufferedImage tileImage = SpriteResourceManager.wall;
                    tileMap[j][i-1] = new Wall(tileImage, new Point((i-1) * 16 * SpriteResourceManager.SF, j * 16 * SpriteResourceManager.SF));

                }

                else if (map[j].charAt(i - 1) == 'E') {
                    BufferedImage tileImage = SpriteResourceManager.empty;
                    tileMap[j][i-1] = new Tile(tileImage, new Point((i-1) * 16 * SpriteResourceManager.SF, j * 16 * SpriteResourceManager.SF));

                }

                else if (map[j].charAt(i - 1) == 'D') {
                    BufferedImage tileImage = SpriteResourceManager.floor;
                    tileMap[j][i-1] = new Door(tileImage, new Point((i-1) * 16 * SpriteResourceManager.SF, j * 16 * SpriteResourceManager.SF), numOfWaves);
                }

                else if (map[j].charAt(i - 1) == 'S') {
                    BufferedImage tileImage = SpriteResourceManager.skull;
                    tileMap[j][i-1] = new Spawner(tileImage, new Point((i-1) * 16 * SpriteResourceManager.SF, j * 16 * SpriteResourceManager.SF), numOfWaves);
                }

                else {
                    BufferedImage tileImage;
                    if(j >= 1 && map[j - 1].charAt(i - 1) == 'X') {
                        tileImage = SpriteResourceManager.floorWShadow;
                    }
                    else {
                        tileImage = SpriteResourceManager.floor;
                    }
                    tileMap[j][i-1] = new Space(tileImage, new Point((i-1) * 16 * SpriteResourceManager.SF, j * 16 * SpriteResourceManager.SF));
                }


            }
        }
    }

    public Tile[][] getTileMap() {
        return tileMap;
    }

    public boolean checksIfPlayerCollidesWithWalls(SpriteBase collider) {

        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {

                if((tileMap[i][j] instanceof Wall) && tileMap[i][j].intersects(collider)) {
                    return true;
                }

                if((tileMap[i][j] instanceof Door) && tileMap[i][j].intersects(collider) && ((Door) tileMap[i][j]).getIsActive()) {
                     return true;
                }

            }
        }

        return false;

    }

    public int getNumOfWave() {return numOfWaves;}

    public void setNumOfWaves() {

        if(numOfWaves > 0) {

            numOfWaves --;

        }
    }
}