package Sprites;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemySprite extends SpriteBase {

    private Point location;
    private BufferedImage image;
    private int enemySpeed;
    private int health;
    private int maxHealth;
    private int movementX;

    public EnemySprite(BufferedImage image, Point location, int eSpeed, int health) {
        super(image, location);

        this.location = location;
        this.image = image;
        this.health = health;
        maxHealth = health;

        enemySpeed = eSpeed;
    }


    //Find Player location and move to it
    public Point moveXYPoint(PlayerSprite player) {

        double theta = Math.toDegrees(Math.atan(((location.y + (double)image.getHeight()/2) - (player.getY() + (double)player.getHeight()/2)) / ((location.x + (double)image.getWidth()/2) - (player.getX() + (double)player.getWidth()/2)) ));

        movementX = -(int)(enemySpeed * Math.cos(Math.toRadians(theta)));
        int movementY = (int)(enemySpeed * Math.sin(Math.toRadians(theta)));
        
        if(((player.getY() > location.y || player.getY() < location.y) && player.getX() == location.x) || player.getX() < location.x) {
            movementX = -movementX;
            movementY = -movementY;
        }

        return new Point(-movementX, movementY);
    }


    //Health
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

    public void drawing(Graphics2D g2, PlayerSprite player){

        if(getX() > player.getX()) {
            super.draw(g2);
        }
        else{
            flipHorz(g2);
        }
        //#region Drawing health bar
        g2.setColor(Color.GRAY);
        g2.fillRect(location.x - 4, location.y - 5, image.getWidth() + 8, 2);

        g2.setColor(Color.red);

        double widthOfHealth = (double)(image.getWidth() + 8) / maxHealth;

        widthOfHealth = widthOfHealth * health;
        g2.fillRect(location.x - 4, location.y - 5, (int)widthOfHealth, 2);

        g2.setColor(Color.BLACK);

        //#endregion
    }
    
}
