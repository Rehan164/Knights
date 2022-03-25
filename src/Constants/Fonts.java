package Constants;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fonts {

    public static Font ka1, boldKa1, plainKa1;

    static {
        try {

            ka1 = Font.createFont(Font.TRUETYPE_FONT,
                    new File("./res/karmatic-arcade/ka1.ttf"));
            boldKa1 = ka1.deriveFont(Font.BOLD, 12);
            plainKa1 = ka1.deriveFont(Font.PLAIN, 12);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

    }
}
