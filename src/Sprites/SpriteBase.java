package Sprites;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteBase {

    private BufferedImage image;
    private Point location;

    public SpriteBase(BufferedImage image, Point location) {
        this.image = image;
        this.location = location;

    }

    public void draw(Graphics2D g2){
        g2.drawImage(image, location.x, location.y, null);
    }

    public boolean intersects(SpriteBase other){
        Rectangle hitBox = new Rectangle(location.x, location.y, image.getWidth(), image.getHeight());
        Rectangle otherHitBox = new Rectangle(other.location.x, other.location.y, other.image.getWidth(), other.image.getHeight());
        return hitBox.intersects(otherHitBox);
    }

    public void move(int dx, int dy){
        location.translate(dx, dy);
    }

    public int getX(){return location.x;}
    public int getY(){return location.y;}
    public int getWidth() {return image.getWidth();}
    public int getHeight() {return image.getHeight();}
    public Point getPoint() {return location;}
    public void setLocation(int x, int y) {location = new Point(x, y);}

    public void flipVert(Graphics2D g2) {
        g2.drawImage(image, location.x, location.y + image.getHeight() + 3, image.getWidth(), -image.getHeight(), null);
    }

}