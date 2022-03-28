package Sprites.Tiles;

import Sprites.SpriteResourceManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Spawner extends Tile{

    private int numOfWaves;
    public boolean isActive;

    public Spawner(BufferedImage image, Point location, int numOfWaves) {
        super(image, location);

        this.numOfWaves = numOfWaves;

        if(numOfWaves > 0) {
            isActive = true;
        }
    }

    @Override
    public void draw(Graphics2D g2) {

        if(numOfWaves > 0) {

            BufferedImage tileImage = SpriteResourceManager.floor;
            Tile floor = new Space(tileImage, new Point(getX(), getY()));
            floor.draw(g2);

            super.draw(g2);

        }
        else {
            BufferedImage tileImage = SpriteResourceManager.floor;
            Tile floor = new Space(tileImage, new Point(getX(), getY()));

            floor.draw(g2);
            isActive = false;
        }
    }

    public boolean getIsActive() {
        return isActive;
    }
}
