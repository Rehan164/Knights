import Constants.GameConstants;
import Constants.PlayerConstants;
import Sprites.PlayerSprite;
import Sprites.SpriteResourceManager;
import Sprites.Weapons.BulletProjectile;
import Sprites.Weapons.WeaponSpriteBase;

import javax.swing.*;
import java.awt.*;

// Key Listeners and Mouse Listeners
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class GamePanel extends JPanel {
    private Timer timer;
    private final boolean[] keys;

    private int currentMouseX;
    private int currentMouseY;

    //#region Game Variables

    //Player
    private PlayerSprite player;

    //Weapons
    private WeaponSpriteBase currentWeapon;
    private WeaponSpriteBase fullAutoSniperRifle;
    private WeaponSpriteBase pistol;

    private ArrayList<BulletProjectile> bullets =  new ArrayList<>();

    //endregion

    public GamePanel(int w, int h){
        
        //#region Enables window size, keys, frame limit
        setSize(w, h);

        keys = new boolean[256];
        timer = new Timer(1000/ GameConstants.frameLimiter, e -> update());
        timer.start();

        setupKeys();
        setupMouse();
        //#endregion

        //#region Setting Player and Weapons

        player = new PlayerSprite(SpriteResourceManager.blueKnight, new Point(400, 400), 100);
        fullAutoSniperRifle = new WeaponSpriteBase(SpriteResourceManager.assaultRifle, new Point(PlayerSprite.playerHand().x ,PlayerSprite.playerHand().y), new Point(11,7), new Point(38,4), player, 5, 30, 40);
        pistol = new WeaponSpriteBase(SpriteResourceManager.assaultRifle, new Point(PlayerSprite.playerHand().x ,PlayerSprite.playerHand().y), new Point(11,7), new Point(38,4), player, 5, 30, 40);

        currentWeapon = pistol;

        //#endregion

        //#region Set Variables



        //#endregion

    }

    //Update
    public void update(){

        //#region Movement
        if(keys[KeyEvent.VK_W]) {
            player.move(0, -PlayerConstants.PLAYER_MOVEMENT_SPEED);
        }
        if(keys[KeyEvent.VK_S]) {
            player.move(0, PlayerConstants.PLAYER_MOVEMENT_SPEED);
        }
        if(keys[KeyEvent.VK_A]) {
            player.move(-PlayerConstants.PLAYER_MOVEMENT_SPEED, 0);
        }
        if(keys[KeyEvent.VK_D]) {
            player.move(PlayerConstants.PLAYER_MOVEMENT_SPEED, 0);
        }
        //#endregion

        for (int i = 0; i < bullets.size(); i++) {

            bullets.get(i).firedMove();

            if(bullets.get(i).getX() < 0 || bullets.get(i).getX() > getWidth() || bullets.get(i).getY() < 0 || bullets.get(i).getY() > getWidth()) {
                bullets.remove(i);
                i --;
            }
        }

//        for (int i = 0; i < bullets.size(); i++) {
//            for (EnemySprite enemySprite : enemies) {
//                if(bullets.get(i).intersects(enemySprite)){
//                    enemySprite.takingDamage(10); //change this to take damage from the players weapon
//                    bullets.remove(i);
//                    break;
//                }
//            }
//        }


        repaint();
    }

    //Draws
    @Override
    protected void paintComponent(Graphics g) { 

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if(player.getAngle() > 90 && player.getAngle() < 270) {
            player.flipHorz(g2, currentMouseX, currentMouseY);
        }
        else {
            player.draw(g2, currentMouseX, currentMouseY);
        }

        currentWeapon.draw(g2, currentMouseX, currentMouseY);

        for (BulletProjectile bulletProjectile : bullets) {
            bulletProjectile.draw(g2);
        }
    }

    //#region Inputs
    public void setupKeys(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                keys[e.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keys[e.getKeyCode()] = false;
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }
        });
    }

    public void setupMouse() {
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentMouseX = e.getX();
                currentMouseY = e.getY();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                currentMouseX = e.getX();
                currentMouseY = e.getY();
            }
        });
        
        addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {

                bullets.add(new BulletProjectile(SpriteResourceManager.smallBullet2, new Point(currentWeapon.barrelExitLocationX(), currentWeapon.barrelExitLocationY()), currentWeapon.currentAngle(), 15));
                currentWeapon.setMagSize(currentWeapon.getMagSize() - 1);

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {
    
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}


        });

    }

    //#endregion

}