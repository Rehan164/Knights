package Sprites;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class SpriteResourceManager {
    // to add an image to the environment:
    // 1. put the file into the res folder.
    // 2. Declare a variable before the static block.
    // 3. Initialize the variable by copying and pasting and modifying the
    //    ImageIO line.


    public static BufferedImage pistolWithLongBarrel, smallBullet2, assaultRifle;


    static{
        try{

            pistolWithLongBarrel = ImageIO.read(new File("./res/Weapons/pistol2.png"));
            assaultRifle = ImageIO.read(new File("./res/Weapons/sniper2.png"));

            smallBullet2 = ImageIO.read(new File("./res/Weapons/small_bullet2.png"));

        }
        
        catch(Exception e){
            
            e.printStackTrace();
        }

    }
}