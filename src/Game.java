import javax.swing.JFrame;
import javax.swing.JPanel;

//import java.awt.*;
//import java.awt.image.BufferedImage;

public class Game {

    public static void main(String[] args) {


        //Cursor blankCursor;


        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        int width = 600; // 1440
        int height = 600; // 800
        //window.setBounds(0, 0, width, height + 22); //(x, y, w, h) 22 due to title bar.

        JPanel panel = new GamePanel(width, height);

        // Transparent 16 x 16 pixel cursor image.
        //BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor.
        //blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");

        //window.setCursor(blankCursor);
        //panel.setCursor(blankCursor);


        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false); 


        window.setBounds(0, 0, width, height + 22);

        //#region For Full SCREEN
        //window.setUndecorated(true);
        //GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //GraphicsDevice device = graphics.getDefaultScreenDevice();
        //device.setFullScreenWindow(window);   
        //#endregion
            
    }

}