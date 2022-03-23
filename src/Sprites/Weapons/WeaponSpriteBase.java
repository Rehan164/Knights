package Sprites.Weapons;

import Sprites.PlayerSprite;
import Sprites.SpriteBase;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WeaponSpriteBase extends SpriteBase{

    private Point hand;

    private final PlayerSprite player;

    private int xPos, yPos;
    private int magSize;

    private final int magFullSize;
    private final int reloadingTime;
    private final int fireRatePerFrame;
    private final double barrelOffsetAngle;
    private final double z;

    private final Point handle;

    private double theta;

    public WeaponSpriteBase(BufferedImage image, Point location, Point handle, Point barrelExit, PlayerSprite player, int fireRatePerFrame, int magSize, int reloadingTime) {

        super(image, location);
        this.handle = handle;
        this.player = player;
        this.fireRatePerFrame = fireRatePerFrame;
        this.magSize = magSize;
        this.reloadingTime = reloadingTime;
        magFullSize = magSize;

        double x = (player.getWidth() / 2.0) + barrelExit.x - handle.x;
        double y = handle.y - barrelExit.y;
        z = Math.sqrt((x * x) + (y * y));

        barrelOffsetAngle = Math.toDegrees(Math.atan(y / x));

        //Sets the hand location getter
        hand = PlayerSprite.playerHand();

    }

    public void draw(Graphics2D g2, int scaleW, int scaleH, int currentMouseX, int currentMouseY) {

        theta = PlayerSprite.degreeFinder(currentMouseX, currentMouseY);

        //Refreshes the hands location getter
        hand = PlayerSprite.playerHand();

        setLocation(hand.x - handle.x, hand.y - handle.y);

        g2.translate(hand.x, hand.y);
        g2.rotate(-Math.toRadians(theta));
 
        setLocation(-handle.x, - handle.y);

        if(theta > 90 && theta < 270) {
            flipVert(g2);

            xPos = (int)(z * Math.cos(Math.toRadians(theta - barrelOffsetAngle)));
            yPos = - (int)(z * Math.sin(Math.toRadians(theta - barrelOffsetAngle)));
        }

        else {
            super.draw(g2);

            xPos = (int)(z * Math.cos(Math.toRadians(theta + barrelOffsetAngle)));
            yPos = - (int)(z * Math.sin(Math.toRadians(theta + barrelOffsetAngle)));
        }

        g2.rotate(Math.toRadians(theta));
        g2.translate(-hand.x, -hand.y);

        g2.setColor(Color.RED);
        g2.drawRect(hand.x - 1, hand.y - 1, 2, 2);

        g2.setColor(Color.GREEN);

        g2.drawRect(player.getX() + (player.getWidth()/2) + xPos - 1, player.getY() + (player.getHeight()/2) + yPos - 1, 2, 2);

        g2.setColor(Color.black);
        //Want to get a certain point on the image and draw it here (with barrelExit.x , barrelExit.y) without rotating it
        
    }


    public int barrelExitLocationX() {
        return player.getX() + (player.getWidth()/2) + xPos;
    }

    public int barrelExitLocationY() {
        return player.getY() + (player.getHeight()/2) + yPos;
    }

    public double currentAngle() {
        return theta;
    }

    public int getfireRatePerFrame() {
        return fireRatePerFrame;
    }

    public int getMagSize() {
        return magSize;
    }

    public void setMagSize(int currentSize) {
        magSize = currentSize;
    }

    public void reloading() {
        magSize = magFullSize;
    }

    public int getReloadingTime() {
        return reloadingTime;
    }

}