package Sprites.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class changeFloor extends Tile{

    private int floorNum;

    public changeFloor(BufferedImage image, Point location, int floorNum) {
        super(image, location);

        this.floorNum = floorNum;
    }

    public int getFloorNum() {
        return floorNum;
    }
}
