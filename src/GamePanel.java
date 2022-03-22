import Constants.GameConstants;

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

    private int currentMouseX;
    private int currentMouseY;

    private final boolean[] keys;

    public GamePanel(int w, int h){
        
        //#region Enables window size, keys, frame limit
        setSize(w, h);

        keys = new boolean[256];
        timer = new Timer(1000/ GameConstants.frameLimiter, e -> update());
        timer.start();

        setupKeys();
        setupMouse();
        //#endregion

    }

    //Update
    public void update(){
        repaint();
    }

    //Draws
    @Override
    protected void paintComponent(Graphics g) { 

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

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