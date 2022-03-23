package Sprites;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SpriteResourceManager {
    // to add an image to the environment:
    // 1. put the file into the res folder.
    // 2. Declare a variable before the static block.
    // 3. Initialize the variable by copying and pasting and modifying the
    //    ImageIO line.


    public static BufferedImage pistolWithLongBarrel, smallBullet2, assaultRifle, blueKnight;


    static{
        try{

            //#region Guns
            pistolWithLongBarrel = ImageIO.read(new File("./res/Weapons/pistol2.png"));
            assaultRifle = ImageIO.read(new File("./res/Weapons/sniper2.png"));

            smallBullet2 = ImageIO.read(new File("./res/Weapons/small_bullet2.png"));
            //#endregion

            //#region Characters
            Image bKnight = ImageIO.read(new File("./res/Characters/Knight.png"));
            bKnight = bKnight.getScaledInstance(bKnight.getWidth(null) * 2, bKnight.getHeight(null) * 2, Image.SCALE_DEFAULT);
            blueKnight = toBufferedImage(bKnight);
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