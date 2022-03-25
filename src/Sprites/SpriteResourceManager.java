package Sprites;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;

public class SpriteResourceManager {
    // to add an image to the environment:
    // 1. put the file into the res folder.
    // 2. Declare a variable before the static block.
    // 3. Initialize the variable by copying and pasting and modifying the
    //    ImageIO line.

    public static int SF = 2;

    public static BufferedImage pistolWithLongBarrel, smallBullet2, assaultRifle, blueKnight, knightHand;

    public static BufferedImage floor, floorWShadow, wall, empty;

    public static BufferedImage firstEnemy;


    static{
        try{

            //#region Guns
            pistolWithLongBarrel = ImageIO.read(new File("./res/Weapons/pistol2.png"));
            assaultRifle = ImageIO.read(new File("./res/Weapons/sniper2.png"));

            smallBullet2 = ImageIO.read(new File("./res/Weapons/small_bullet2.png"));
            //#endregion

            //#region Characters
            Image bKnight = ImageIO.read(new File("./res/Characters/Knight.png"));
            bKnight = bKnight.getScaledInstance(bKnight.getWidth(null) * SF, bKnight.getHeight(null) * SF, Image.SCALE_DEFAULT);
            blueKnight = toBufferedImage(bKnight);

            knightHand = ImageIO.read(new File("./res/Characters/KnightHand.png"));
            //#endregion

            //#region Walls
            Image f = ImageIO.read(new File("./res/Map/Walls/tile029.png"));
            f = f.getScaledInstance(f.getWidth(null) * SF, f.getHeight(null) * SF, Image.SCALE_DEFAULT);
            floor = toBufferedImage(f);

            Image fWS = ImageIO.read(new File("./res/Map/Walls/tile018.png"));
            fWS = fWS.getScaledInstance(fWS.getWidth(null) * SF, fWS.getHeight(null) * SF, Image.SCALE_DEFAULT);
            floorWShadow = toBufferedImage(fWS);

            Image w = ImageIO.read(new File("./res/Map/Walls/tile009.png"));
            w = w.getScaledInstance(w.getWidth(null) * SF, w.getHeight(null) * SF, Image.SCALE_DEFAULT);
            wall = toBufferedImage(w);

            Image e = ImageIO.read(new File("./res/Map/Walls/tile058.png"));
            e = e.getScaledInstance(e.getWidth(null) * SF, e.getHeight(null) * SF, Image.SCALE_DEFAULT);
            empty = toBufferedImage(e);

            //#endregion

            //#region Enemies

            Image enemy1 = ImageIO.read(new File("./res/Enemies/Enemy1.png"));
            enemy1 = enemy1.getScaledInstance(enemy1.getWidth(null) * SF, enemy1.getHeight(null) * SF, Image.SCALE_DEFAULT);
            firstEnemy = toBufferedImage(enemy1);

            //#endregion

        }
        
        catch(Exception e){
            
            e.printStackTrace();
        }

    }

    //Helps Resizes Images
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}