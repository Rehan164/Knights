package Sprites.Weapons;

import java.awt.*;
import java.awt.image.BufferedImage;

import Sprites.SpriteBase;

public class BulletProjectile extends SpriteBase{

    private Point location;
    private double theta;
    private BufferedImage image;

    private int speed;

    public BulletProjectile(BufferedImage image, Point location, double theta, int speed) {
        super(image, location);
        this.theta = theta;
        this.location = location;
        this.image = image;
        this.speed = speed;

    }

    public void draw(Graphics2D g2) {

        g2.translate(location.x, location.y);
        g2.rotate(-Math.toRadians(theta - 90));

        g2.drawImage(image, 0, 0, null);

        g2.rotate(Math.toRadians(theta - 90));
        g2.translate(-location.x, -location.y);

    }

    public void firedMove() {

        int dx =(int) (speed * Math.cos(Math.toRadians(theta)));
        int dy = -(int) (speed * Math.sin(Math.toRadians(theta)));

        super.move(dx, dy);

    }
    
}