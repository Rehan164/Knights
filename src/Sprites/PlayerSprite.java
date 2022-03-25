package Sprites;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerSprite extends SpriteBase {

    private static Point location;
    private BufferedImage image;
    private static Point hand;
    private static BufferedImage currentImage;
    private double angle;

    private int health;
    private final int maxHealth;

    public PlayerSprite(BufferedImage image, Point point, int health) {
        super(image, point);

        this.health = health;
        this.image = image;

        location = point;
        currentImage = image;
        maxHealth = health;

        hand = new Point(hand(0, 0));
    }

    public void draw(Graphics2D g2, int currentMouseX, int currentMouseY){
        super.draw(g2);

        hand = new Point(hand(currentMouseX, currentMouseY));

        g2.drawImage(SpriteResourceManager.knightHand, hand.x - SpriteResourceManager.knightHand.getWidth() / 2, hand.y - SpriteResourceManager.knightHand.getHeight(), SpriteResourceManager.knightHand.getWidth(), SpriteResourceManager.knightHand.getHeight(), null);


        //#region Drawing Health bar
        g2.setColor(Color.GRAY);
        g2.fillRect(location.x - 4, location.y - 5, image.getWidth() + 8, 2);

        g2.setColor(Color.red);

        double widthOfHealth = (double)(image.getWidth() + 8) / maxHealth;

        widthOfHealth = widthOfHealth * health;
        g2.fillRect(location.x - 4, location.y - 5, (int)widthOfHealth, 2);

        g2.setColor(Color.BLACK);
        //#endregion
        
    }

    //This is where you can get the degree based of the player
    public static double degreeFinder(int mouseX, int mouseY) {

        double x = location.x + (currentImage.getWidth() / 2.0);
        double y = location.y + (currentImage.getHeight() / 2.0);

        double theta = Math.toDegrees(Math.atan(-(mouseX - x) / (mouseY - y)));
        theta = 180 - (theta + 90);

        if(mouseY > y) {
            theta += 180;
        }
        if(theta == 0) {
            theta = 180.0;
        }
        else if(theta == 180) {
            theta = 0.0;
        }

        return theta;
    }

    //Player Hand
    public Point hand(int mouseX, int mouseY) {
        angle = degreeFinder(mouseX, mouseY);

        int z = currentImage.getWidth() / 2;

        int xC = (int)(z * Math.cos(Math.toRadians(angle)));
        int yC = -(int)(z * Math.sin(Math.toRadians(angle)));

        return new Point((location.x + (currentImage.getWidth()/2) + xC), (location.y + (currentImage.getHeight()/2) + yC));

    }

    public static Point playerHand() {
        return hand;
    }

    public int takingDamage(int damage) {

        health -= damage;

        if(health <= 0) {
            health = 0;
        }

        return health;
    }

    public int getHealth() {
        return health;
    }

    //#region Collision Borders
    public SpriteBase top() {
        return new SpriteBase(new BufferedImage(image.getWidth() - 2, 1, BufferedImage.TYPE_3BYTE_BGR), new Point(location.x + 1, location.y-1));
    }

    public SpriteBase bottom() {
        return new SpriteBase(new BufferedImage(image.getWidth() - 2, 1, BufferedImage.TYPE_3BYTE_BGR), new Point(location.x + 1, location.y + image.getHeight()));
    }

    public SpriteBase right() {
        return new SpriteBase(new BufferedImage(1, image.getHeight() - 2, BufferedImage.TYPE_3BYTE_BGR), new Point(location.x + 2 + image.getWidth(), location.y + 1));
    }

    public SpriteBase left() {
        return new SpriteBase(new BufferedImage(1, image.getHeight() - 2, BufferedImage.TYPE_3BYTE_BGR), new Point(location.x-1, location.y + 1));
    }
    //#endregion

    public void flipHorz(Graphics2D g2, int currentMouseX, int currentMouseY) {
        super.flipHorz(g2);

        hand = new Point(hand(currentMouseX, currentMouseY));

        //Under Hand

        //g2.drawImage(SpriteResourceManager.knightHand, hand.x, hand.y, SpriteResourceManager.knightHand.getWidth(), SpriteResourceManager.knightHand.getHeight(), null);

        //#region Drawing Health bar
        g2.setColor(Color.GRAY);
        g2.fillRect(location.x - 4, location.y - 5, image.getWidth() + 8, 2);

        g2.setColor(Color.red);

        double widthOfHealth = (double)(image.getWidth() + 8) / maxHealth;

        widthOfHealth = widthOfHealth * health;
        g2.fillRect(location.x - 4, location.y - 5, (int)widthOfHealth, 2);

        g2.setColor(Color.BLACK);
        //#endregion
    }

    public double getAngle() {return angle;}
}