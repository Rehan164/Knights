import Constants.GameConstants;
import Constants.PlayerConstants;
import Sprites.PlayerSprite;
import Sprites.SpriteResourceManager;

import javax.swing.*;
import java.awt.*;

// Key Listeners and Mouse Listeners
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class GamePanel extends JPanel {
    private Timer timer;
    private final boolean[] keys;

    private int currentMouseX;
    private int currentMouseY;

    //#region Game Variables

    private PlayerSprite player;
    private int previousDir;

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

        //#endregion

        //#region Set Variables

        //1 for right -1 for left
        previousDir = 1;

        //#endregion

    }

    //Update
    public void update(){

        //#region Movment
        if(keys[KeyEvent.VK_W]) {
            player.move(0, -PlayerConstants.PLAYER_MOVEMENT_SPEED);
        }
        if(keys[KeyEvent.VK_S]) {
            player.move(0, PlayerConstants.PLAYER_MOVEMENT_SPEED);
        }
        if(keys[KeyEvent.VK_A]) {
            previousDir = -1;
            player.move(-PlayerConstants.PLAYER_MOVEMENT_SPEED, 0);
        }
        if(keys[KeyEvent.VK_D]) {
            previousDir = 1;
            player.move(PlayerConstants.PLAYER_MOVEMENT_SPEED, 0);
        }
        //#endregion

        repaint();
    }

    //Draws
    @Override
    protected void paintComponent(Graphics g) { 

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if(previousDir == -1) {
            player.flipHorz(g2, currentMouseX, currentMouseY);
        }
        else {
            player.draw(g2, currentMouseX, currentMouseY);
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