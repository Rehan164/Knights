package Sprites.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Door extends Tile{

    private int numOfWaves;
    private boolean isActive;

    public Door(BufferedImage image, Point location, int numOfWaves) {
        super(image, location);

        this.numOfWaves = numOfWaves;

        if(numOfWaves > 0) {
            isActive = true;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

        if(numOfWaves > 0) {

            g2.setColor(new Color(255,0,0, 77));
            g2.fillRect(getX(), getY(), getWidth(), getHeight());
            g2.setColor(Color.black);

        }

        else {
            isActive = false;
        }
    }

    public boolean getIsActive() {
        return isActive;
    }
}
