import Constants.Fonts;
import Constants.GameConstants;
import Constants.Maps;
import Constants.PlayerConstants;
import Map.MapCreator;
import Sprites.EnemySprite;
import Sprites.PlayerSprite;
import Sprites.SpriteResourceManager;
import Sprites.Tiles.Spawner;
import Sprites.Tiles.Tile;
import Sprites.Tiles.changeFloor;
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
    private int currentFrame;

    //#region Game Variables

    //Player
    private PlayerSprite player;

    //Weapons
    private WeaponSpriteBase currentWeapon;
    private WeaponSpriteBase fullAutoSniperRifle;
    private WeaponSpriteBase pistol;

    private ArrayList<BulletProjectile> bullets =  new ArrayList<>();

    //Enemy
    private ArrayList<EnemySprite> enemies = new ArrayList<>();

    //Maps
    private MapCreator floor1;
    private MapCreator floor0;

    private MapCreator currentMap;

    public GamePanel(int w, int h){
        
        //#region Enables window size, keys, frame limit
        setSize(w, h);

        keys = new boolean[256];

        setupKeys();
        setupMouse();
        //#endregion

        //#region Setting Player and Weapons

        player = new PlayerSprite(SpriteResourceManager.blueKnight, new Point(400, 400), 100);

        fullAutoSniperRifle = new WeaponSpriteBase(SpriteResourceManager.assaultRifle, new Point(player.playerHand().x ,player.playerHand().y), new Point(11,7), new Point(38,4), player, 5, 30, 40);
        pistol = new WeaponSpriteBase(SpriteResourceManager.pistolWithLongBarrel, new Point(player.playerHand().x ,player.playerHand().y), new Point(3,7), new Point(14,3), player, 7, 12, 20);
        currentWeapon = pistol;

        //#endregion

        //#region Test Setting for Enemies

        floor1 = new MapCreator(Maps.testMap, 2);
        floor0 = new MapCreator(Maps.startMap, 0);

        currentMap = floor0;

        Tile[][] CM = currentMap.getTileMap();
        for (Tile[] tiles : CM) {
            for (Tile t: tiles) {
                if(t instanceof Spawner && ((Spawner) t).getIsActive()) {
                    enemies.add(new EnemySprite(SpriteResourceManager.firstEnemy, new Point(t.getX(),t.getY()), 2, 100));
                }
            }
        }

        //#endregion

        //#region Set Variables

        currentFrame = 0;

        //#endregion

        timer = new Timer(1000/ GameConstants.frameLimiter, e -> update());
        timer.start();

    }

    //Update
    public void update(){

        //#region Movement
        if(keys[KeyEvent.VK_W] && !currentMap.checksIfPlayerCollidesWithWalls(player.top())) {
            player.move(0, -PlayerConstants.PLAYER_MOVEMENT_SPEED);
        }
        if(keys[KeyEvent.VK_S] && !currentMap.checksIfPlayerCollidesWithWalls(player.bottom())) {
            player.move(0, PlayerConstants.PLAYER_MOVEMENT_SPEED);
        }
        if(keys[KeyEvent.VK_A] && !currentMap.checksIfPlayerCollidesWithWalls(player.left())) {
            player.move(-PlayerConstants.PLAYER_MOVEMENT_SPEED, 0);
        }
        if(keys[KeyEvent.VK_D] && !currentMap.checksIfPlayerCollidesWithWalls(player.right())) {
            player.move(PlayerConstants.PLAYER_MOVEMENT_SPEED, 0);
        }
        //#endregion

        for (int i = 0; i < bullets.size(); i++) {

            bullets.get(i).firedMove();

            if(currentMap.checksIfPlayerCollidesWithWalls(bullets.get(i))){
                bullets.remove(i);
                i--;
            }

            else if(bullets.get(i).getX() < 0 || bullets.get(i).getX() > getWidth() || bullets.get(i).getY() < 0 || bullets.get(i).getY() > getWidth()) {
                bullets.remove(i);
                i --;
            }
        }

        //#region Enemy Stuff
        for (int i = 0; i < bullets.size(); i++) {
            for (EnemySprite enemySprite : enemies) {
                if(bullets.get(i).intersects(enemySprite)){
                    enemySprite.takingDamage(10); //change this to take damage from the players weapon
                    bullets.remove(i);
                    break;
                }
            }
        }

        if(currentFrame % 10 == 0) {
            for (EnemySprite enemySprite : enemies) {
                if(player.intersects(enemySprite)) {
                    player.takingDamage(10);
                }
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            if(enemies.get(i).getHealth() == 0) {
                enemies.remove(i);
                i--;
            }
            else {
                if(!player.intersects(enemies.get(i))) {
                    enemies.get(i).move( enemies.get(i).moveXYPoint(player).x , enemies.get(i).moveXYPoint(player).y);
                }
            }
        }



        //#endregion

        if(enemies.size() <= 0) {
            currentMap.setNumOfWaves();
            currentMap.setTileMap();

            if(currentMap.getNumOfWave() > 0) {
                Tile[][] CM = currentMap.getTileMap();
                for (Tile[] tiles : CM) {
                    for (Tile t: tiles) {
                        if(t instanceof Spawner && ((Spawner) t).getIsActive()) {
                            enemies.add(new EnemySprite(SpriteResourceManager.firstEnemy, new Point(t.getX(),t.getY()), 2, 100));
                        }
                    }
                }
            }
        }

        Tile[][] CM = currentMap.getTileMap();
        for (Tile[] tiles : CM) {
            for (Tile t: tiles) {
                if(t instanceof changeFloor) {
                    if(player.intersects(t)) {

                        if(((changeFloor) t).getFloorNum() == 0) {
                            currentMap = floor0;

                            player = new PlayerSprite(SpriteResourceManager.blueKnight, new Point(player.getX(), 50), 100);

                            fullAutoSniperRifle = new WeaponSpriteBase(SpriteResourceManager.assaultRifle, new Point(player.playerHand().x ,player.playerHand().y), new Point(11,7), new Point(38,4), player, 5, 30, 40);
                            pistol = new WeaponSpriteBase(SpriteResourceManager.pistolWithLongBarrel, new Point(player.playerHand().x ,player.playerHand().y), new Point(3,7), new Point(14,3), player, 7, 12, 20);
                            currentWeapon = pistol;

                        }

                        else if(((changeFloor) t).getFloorNum() == 1) {
                            currentMap = floor1;

                            player = new PlayerSprite(SpriteResourceManager.blueKnight, new Point(player.getX(), 700), 100);

                            fullAutoSniperRifle = new WeaponSpriteBase(SpriteResourceManager.assaultRifle, new Point(player.playerHand().x ,player.playerHand().y), new Point(11,7), new Point(38,4), player, 5, 30, 40);
                            pistol = new WeaponSpriteBase(SpriteResourceManager.pistolWithLongBarrel, new Point(player.playerHand().x ,player.playerHand().y), new Point(3,7), new Point(14,3), player, 7, 12, 20);
                            currentWeapon = pistol;

                        }
                    }
                }
            }
        }


        currentFrame ++;

        repaint();
    }

    //Draws
    @Override
    protected void paintComponent(Graphics g) { 

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        currentMap.drawMap(g2);

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

        for (EnemySprite enemySprite : enemies) {
            enemySprite.drawing(g2, player);
        }

        g2.setColor(Color.white);
        g2.setFont(Fonts.plainKa1);
        g2.drawString("Current Wave - " + currentMap.getNumOfWave(), 10,20);

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